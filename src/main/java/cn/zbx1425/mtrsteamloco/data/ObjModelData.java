package cn.zbx1425.mtrsteamloco.data;

import cn.zbx1425.mtrsteamloco.client.MainClient;
import cn.zbx1425.mtrsteamloco.mixin.ObjModelMixin;
import cn.zbx1425.sowcer.math.Vector3f;
import cn.zbx1425.sowcer.model.Model;
import cn.zbx1425.sowcerext.model.RawModel;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import org.mtr.libraries.it.unimi.dsi.fastutil.objects.Object2ObjectAVLTreeMap;
import org.mtr.mapping.mapper.OptimizedModel;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjModelData {
    public final RawModel m;

    public ObjModelData(RawModel m) {
        this.m = m;
    }

    public static Map<String, OptimizedModel.ObjModel> loadModelForDynamicVehicleModel(ResourceLocation location, boolean flipTextureV) {
        Map<String, OptimizedModel.ObjModel> objModels = new HashMap<>();
        try {
            MainClient.modelManager.loadPartedRawModel(Minecraft.getInstance().getResourceManager(), location, MainClient.atlasManager)
                    .forEach((k, v) -> {
                        float[] bounds = new float[]{Float.MAX_VALUE, Float.MAX_VALUE, Float.MAX_VALUE, -Float.MAX_VALUE, -Float.MAX_VALUE, -Float.MAX_VALUE};
                        v.meshList.forEach((p, m) -> {
                            m.applyRotation(new Vector3f(1.0F, 0.0F, 0.0F), 180.0F);
                            m.vertices.forEach((w) -> {
                                float x = w.position.x();
                                float y = w.position.y();
                                float z = w.position.z();
                                bounds[0] = Math.min(bounds[0], x);
                                bounds[1] = Math.min(bounds[1], y);
                                bounds[2] = Math.min(bounds[2], z);
                                bounds[3] = Math.max(bounds[3], x);
                                bounds[4] = Math.max(bounds[4], y);
                                bounds[5] = Math.max(bounds[5], z);
                            });
                        });
                        var t = ObjModelMixin.create(List.of(), flipTextureV, bounds[0], bounds[1], bounds[2], bounds[3], bounds[4], bounds[5]);
                        try {
                            t.getClass().getField("sowcerModeRawModel").set(t, v);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        objModels.put(k, t);
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objModels;
    }
}
