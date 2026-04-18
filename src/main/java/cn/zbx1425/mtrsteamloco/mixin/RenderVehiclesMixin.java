package cn.zbx1425.mtrsteamloco.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.mtr.mod.render.RenderVehicles;
import org.spongepowered.asm.mixin.Mixin;

@Environment(EnvType.CLIENT)
@Mixin(RenderVehicles.class)
public class RenderVehiclesMixin {
}
