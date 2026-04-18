package cn.zbx1425.mtrsteamloco.mixin;

import cn.zbx1425.mtrsteamloco.client.MainClient;
import cn.zbx1425.mtrsteamloco.data.ObjModelData;
import cn.zbx1425.sowcer.model.Model;
import cn.zbx1425.sowcerext.model.RawModel;
import cn.zbx1425.sowcerext.model.loader.ObjModelLoader;
import cn.zbx1425.sowcerext.reuse.ModelManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import org.apache.commons.lang3.StringUtils;
import org.mtr.core.serializer.JsonReader;
import org.mtr.core.tool.Utilities;
import org.mtr.libraries.it.unimi.dsi.fastutil.objects.*;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.mapper.OptimizedModel;
import org.mtr.mod.client.CustomResourceLoader;
import org.mtr.mod.render.DynamicVehicleModel;
import org.mtr.mod.resource.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.IOException;

@Environment(EnvType.CLIENT)
@Mixin(StoredModelResourceBase.class)
public interface StoredModelResourceBaseMixin {
    @Inject(
            method = "load",
            at = @At(value = "INVOKE", target = "Lorg/mtr/mod/resource/CustomResourceTools;formatIdentifierWithDefault(Ljava/lang/String;Ljava/lang/String;)Lorg/mtr/mapping/holder/Identifier;",
            ordinal = 0, shift = At.Shift.AFTER)
    )
    default void load(String modelResource, String textureResource, boolean flipTextureV, double modelYOffset, ResourceProvider resourceProvider, CallbackInfoReturnable<ObjectObjectImmutablePair<OptimizedModelWrapper, DynamicVehicleModel>> cir) throws IOException {
        ObjectObjectImmutablePair<OptimizedModelWrapper, DynamicVehicleModel> models;
        boolean isObj = modelResource.endsWith(".obj");

        if (isObj) {
            ModelManager manager = new ModelManager();
            Object2ObjectOpenHashMap<PartCondition, ObjectArrayList<ObjModelData>> objModels = new Object2ObjectOpenHashMap<>();
            Object2ObjectAVLTreeMap<String, RawModel> rawModels = new Object2ObjectAVLTreeMap<>(
                    manager.loadPartedRawModel(
                            Minecraft.getInstance().getResourceManager(),
                            CustomResourceTools.formatIdentifierWithDefault(modelResource, "obj").data,
                            MainClient.atlasManager
                    )
            );
        }
    }
}
