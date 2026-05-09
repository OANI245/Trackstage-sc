package cn.zbx1425.mtrsteamloco.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.mtr.libraries.it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.mtr.libraries.it.unimi.dsi.fastutil.objects.ObjectIntImmutablePair;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mod.data.VehicleExtension;
import org.mtr.mod.resource.ModelPropertiesPart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(value = ModelPropertiesPart.class, remap = false)
public class ModelPropertiesPartMixin {
    @Inject(
            method = "lambda$renderNormal$25",
            at = @At(value = "INVOKE", target = "Lorg/mtr/mapping/mapper/GraphicsHolder;push()V", shift = At.Shift.BEFORE),
            cancellable = true
    )
    public void lambda$renderNormal$25$invoke0(
            boolean flashOn,
            VehicleExtension vehicle,
            ObjectArrayList openDoorways,
            GraphicsHolder graphicsHolder,
            int light,
            ObjectIntImmutablePair renderProperties,
            ModelPropertiesPart.PartDetails partDetails,
            CallbackInfo ci,
            @Local(ordinal = 0) float x,
            @Local(ordinal = 1) float y,
            @Local(ordinal = 2) float z
    ) {
        if (x >= Integer.MAX_VALUE || y >= Integer.MAX_VALUE || z >= Integer.MAX_VALUE) {
            ci.cancel();
        }
    }
}
