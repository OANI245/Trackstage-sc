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
    @Unique public List<ModelCluster> sowcerModeModels = null;
    @Unique private boolean objFlag;

    @Unique private static final Map<Long, List<ModelCluster>> UPLOADED_MODELS = new HashMap<>();

    @Inject(
            method = "lambda$fromObjModels$2",
            at = @At("HEAD")
    )
    private static void lambda$fromObjModels$2$head(List<VertexArray> uploadedParts, OptimizedModel.ObjModel objModel, CallbackInfo ci) {
        long l = Thread.currentThread().threadId();
        if (Config.getInstance().trackstageObjModelRender) {
            try {
                RawModel t = (RawModel) objModel.getClass().getField("sowcerModeRawModel").get(objModel);
                t.generateNormals();
                t.distinct();
                UPLOADED_MODELS.get(l).add(MainClient.modelManager.uploadVertArrays(t));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Inject(
            method = "fromObjModels",
            at = @At("HEAD")
    )
    private static void fromObjModels$head(Collection<OptimizedModel.ObjModel> objModels, CallbackInfoReturnable<OptimizedModel> cir) {
        UPLOADED_MODELS.put(Thread.currentThread().threadId(), new ArrayList<>());
    }

    @Inject(
            method = "fromObjModels",
            at = @At("RETURN"),
            cancellable = true
    )
    private static void fromObjModels$return(Collection<OptimizedModel.ObjModel> objModels, CallbackInfoReturnable<OptimizedModel> cir) {
        long l = Thread.currentThread().threadId();
        try {
            var t = cir.getReturnValue();
            if (t == null) {
                return;
            }
            ((BadModelMixin) ((Object) t)).objFlag = true;
            ((BadModelMixin) ((Object) t)).sowcerModeModels = UPLOADED_MODELS.get(l);
            UPLOADED_MODELS.remove(l);
            cir.setReturnValue(t);
        } catch (Exception ignored) {}
    }
}
