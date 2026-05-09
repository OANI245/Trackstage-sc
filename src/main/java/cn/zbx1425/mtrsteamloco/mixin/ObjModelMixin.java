package cn.zbx1425.mtrsteamloco.mixin;

import cn.zbx1425.mtrsteamloco.Config;
import cn.zbx1425.sowcer.math.Vector3f;
import cn.zbx1425.sowcerext.model.RawMesh;
import cn.zbx1425.sowcerext.model.RawModel;
import org.mtr.mapping.mapper.OptimizedModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(OptimizedModel.ObjModel.class)
public class ObjModelMixin {
    @Unique
    public RawModel sowcerModeRawModel;

    @Invoker("<init>")
    public static OptimizedModel.ObjModel create(
            List<org.mtr.mapping.render.model.RawMesh> rawMeshes,
            boolean flipTextureV,
            float minX,
            float minY,
            float minZ,
            float maxX,
            float maxY,
            float maxZ
    ) { throw new AssertionError("Failed to create Mixin injection"); }

    @Inject(
            method = "addTransformation",
            at = @At("TAIL")
    )
    public void addTransformation$tail(
            OptimizedModel.ShaderType shaderType,
            double x,
            double y,
            double z,
            boolean flipped,
            CallbackInfo ci
    ) {
        if (canInject()) {
            sowcerModeRawModel.meshList.forEach((p, m) -> {
                RawMesh nm = m.copy();
                nm.applyTranslation((float) x, (float) y, (float) z);
                if (flipped) { nm.applyRotation(new Vector3f(0.0F, 1.0F, 0.0F), 180.0F); }
                sowcerModeRawModel.meshList.put(p, nm);
            });
        }
    }

    @Inject(
            method = "applyTranslation",
            at = @At("TAIL")
    )
    public void applyTranslation$tail(double x, double y, double z, CallbackInfo ci) {
        if (canInject()) {
            sowcerModeRawModel.applyTranslation((float) x, (float) y, (float) z);
        }
    }

    @Inject(
            method = "applyRotation",
            at = @At("TAIL")
    )
    public void applyRotation$tail(double x, double y, double z, CallbackInfo ci) {
        if (canInject()) {
            sowcerModeRawModel.applyRotation(new Vector3f(1.0F, 0.0F, 0.0F), (float) x);
            sowcerModeRawModel.applyRotation(new Vector3f(0.0F, 1.0F, 0.0F), (float) y);
            sowcerModeRawModel.applyRotation(new Vector3f(0.0F, 0.0F, 1.0F), (float) z);
        }
    }

    @Inject(
            method = "applyScale",
            at = @At("TAIL")
    )
    public void applyScale$tail(double x, double y, double z, CallbackInfo ci) {
        if (canInject()) {
            sowcerModeRawModel.applyScale((float) x, (float) y, (float) z);
        }
    }

    @Inject(
            method = "applyMirror",
            at = @At("TAIL")
    )
    public void applyMirror$tail(boolean x, boolean y, boolean z, CallbackInfo ci) {
        if (canInject()) {
            sowcerModeRawModel.applyMirror(x, y, z, x, y, z);
        }
    }

    @Unique
    private boolean canInject() {
        return Config.getInstance().trackstageObjModelRender && sowcerModeRawModel != null;
    }
}
