package cn.zbx1425.mtrsteamloco.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.mtr.mapping.render.object.VertexArray;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Environment(EnvType.CLIENT)
@Mixin(VertexArray.class)
public interface VertexArrayAccessor {
    @Accessor("id") int getId();
}
