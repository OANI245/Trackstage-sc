package cn.zbx1425.mtrsteamloco.util;

import cn.zbx1425.sowcerext.model.ModelCluster;
import cn.zbx1425.sowcerext.model.RawModel;
import net.minecraft.world.phys.Vec3;
import org.mtr.libraries.com.logisticscraft.occlusionculling.util.Vec3d;
import org.mtr.mapping.mapper.OptimizedModel;

public interface JonathanMapperUtil {
    static Vec3d vec3ToVec3d(Vec3 original) {
        return new Vec3d(original.x, original.y, original.z);
    }

    static Vec3 vec3dToVec3(Vec3d mapped) {
        return new Vec3(mapped.x, mapped.y, mapped.z);
    }
}
