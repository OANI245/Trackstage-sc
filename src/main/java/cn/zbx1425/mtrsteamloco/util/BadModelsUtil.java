package cn.zbx1425.mtrsteamloco.util;

import cn.zbx1425.sowcerext.model.ModelCluster;
import org.jetbrains.annotations.Nullable;
import org.mtr.mapping.mapper.OptimizedModel;

public interface BadModelsUtil {
    @Nullable
    static ModelCluster getSowcerModeModelFromBadModel(OptimizedModel base) {
        try {
            var v = base.getClass().getField("sowcerModeModel").get(base);
            return v instanceof ModelCluster ? (ModelCluster) v : null;
        } catch (Throwable e) {
            return null;
        }
    }
}
