package cn.zbx1425.mtrsteamloco.mixin;

import berries.mods.ts.mvapi.MVIdentifier;
import cn.zbx1425.mtrsteamloco.Config;
import cn.zbx1425.mtrsteamloco.data.ObjModelData;
import com.llamalad7.mixinextras.sugar.Local;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.mtr.core.serializer.ReaderBase;
import org.mtr.libraries.it.unimi.dsi.fastutil.objects.Object2ObjectAVLTreeMap;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mod.client.CustomResourceLoader;
import org.mtr.mod.generated.resource.VehicleModelSchema;
import org.mtr.mod.render.DynamicVehicleModel;
import org.mtr.mod.resource.ModelProperties;
import org.mtr.mod.resource.PositionDefinitions;
import org.mtr.mod.resource.ResourceProvider;
import org.mtr.mod.resource.VehicleModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(VehicleModel.class)
public class VehicleModelMixin extends VehicleModelSchema {
    protected VehicleModelMixin(String modelResource, String textureResource, String modelPropertiesResource, String positionDefinitionsResource, boolean flipTextureV, ResourceProvider resourceProvider) {
        super(modelResource, textureResource, modelPropertiesResource, positionDefinitionsResource, flipTextureV, resourceProvider);
    }

    protected VehicleModelMixin(ReaderBase readerBase, ResourceProvider resourceProvider) {
        super(readerBase, resourceProvider);
    }

    @Inject(
            method = "createModel",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/mtr/mod/resource/OptimizedRendererWrapper;beginReload()V",
                    ordinal = 1,
                    shift = At.Shift.AFTER
            ),
            cancellable = true)
    public void createModel$invoke$beginReload$objMode(ModelProperties modelProperties, PositionDefinitions positionDefinitions, String id, CallbackInfoReturnable<DynamicVehicleModel> cir,
                                                       @Local(name = "textureId") Identifier textureId) {
        if (Config.getInstance().trackstageObjModelRender) {
            DynamicVehicleModel dvm = new DynamicVehicleModel(new Object2ObjectAVLTreeMap<>(ObjModelData.loadModelForDynamicVehicleModel(MVIdentifier.get(modelResource), flipTextureV)), textureId, modelProperties, positionDefinitions, id);
            CustomResourceLoader.OPTIMIZED_RENDERER_WRAPPER.finishReload();
            cir.setReturnValue(dvm);
        }
    }
}
