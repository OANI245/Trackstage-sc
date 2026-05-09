package cn.zbx1425.mtrsteamloco.mixin;

import cn.zbx1425.mtrsteamloco.Config;
import cn.zbx1425.mtrsteamloco.client.MainClient;
import cn.zbx1425.sowcer.object.VertArray;
import cn.zbx1425.sowcerext.model.ModelCluster;
import cn.zbx1425.sowcerext.model.RawModel;
import cn.zbx1425.sowcerext.reuse.ModelManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.mtr.mapping.mapper.OptimizedModel;
import org.mtr.mapping.render.object.VertexArray;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.*;

@Environment(EnvType.CLIENT)
@Mixin(value = OptimizedModel.class, remap = false)
public abstract class BadModelMixin {
    @Unique public ModelCluster sowcerModeModel = null;
    @Unique private boolean objFlag;

    @Unique private static final Map<Long, List<VertArray>> VERT_ARRAYS = new HashMap<>();

    @Inject(
            method = "lambda$fromObjModels$2",
            at = @At("HEAD"),
            cancellable = true)
    private static void lambda$fromObjModels$2$head(List<VertexArray> uploadedParts, OptimizedModel.ObjModel objModel, CallbackInfo ci) {
        if ((boolean) Config.getInstance().trackstageObjModelRender) {
            var tid = Thread.currentThread().threadId();
            if (!VERT_ARRAYS.containsKey(tid)) {
                return;
            }
            ci.cancel();
        }
    }

    @Inject(
            method = "fromObjModels",
            at = @At("HEAD")
    )
    private static void fromObjModels$head(Collection<OptimizedModel.ObjModel> objModels, CallbackInfoReturnable<OptimizedModel> cir) {
        VERT_ARRAYS.put(Thread.currentThread().threadId(), new ArrayList<>());
    }

    @Inject(
            method = "fromObjModels",
            at = @At("RETURN")
    )
    private static void fromObjModels$return(Collection<OptimizedModel.ObjModel> objModels, CallbackInfoReturnable<OptimizedModel> cir) {
        try {
            var t = cir.getReturnValue();
            if (t == null) {
                return;
            }
            ((BadModelMixin) ((Object) t)).objFlag = true;
            //((BadModelMixin) ((Object) t)).sowcerModeModel = new ModelCluster(t);
        } catch (Exception ignored) {} finally {
            VERT_ARRAYS.remove(Thread.currentThread().threadId());
        }
    }
}
