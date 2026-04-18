package cn.zbx1425.mtrsteamloco.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.mtr.mod.client.CustomResourceLoader;
import org.spongepowered.asm.mixin.Mixin;

@Environment(EnvType.CLIENT)
@Mixin(CustomResourceLoader.class)
public class CustomResourceLoaderMixin {
}
