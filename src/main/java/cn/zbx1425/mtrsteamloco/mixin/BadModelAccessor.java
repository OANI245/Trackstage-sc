package cn.zbx1425.mtrsteamloco.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.mtr.mapping.mapper.OptimizedModel;
import org.mtr.mapping.render.object.VertexArray;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Environment(EnvType.CLIENT)
@Mixin(value = OptimizedModel.class, remap = false)
public interface BadModelAccessor {
    @Accessor("uploadedParts") List<VertexArray> getUploadedParts();
}
