package cn.zbx1425.mtrsteamloco.mixin;

import org.mtr.mod.render.RenderRails;
import org.mtr.mod.render.StoredMatrixTransformations;
import org.mtr.mod.resource.RailResource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RenderRails.class)
public abstract class RenderRailsMixin {
    @Redirect(
            method = "lambda$renderRailStandard$16",
            at = @At(value = "INVOKE", target = "Lorg/mtr/mod/resource/RailResource;render(Lorg/mtr/mod/render/StoredMatrixTransformations;I)V")
    )
    private static void railResourceRender0(RailResource instance, StoredMatrixTransformations storedMatrixTransformations, int i) {
    }
}
