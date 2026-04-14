package cn.zbx1425.mtrsteamloco.mixin;

import cn.zbx1425.mtrsteamloco.Main;
import com.mojang.blaze3d.platform.Window;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Environment(EnvType.CLIENT)
@Mixin(Window.class)
public class MinecraftWindowMixin {
    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lorg/lwjgl/glfw/GLFW;glfwWindowHint(II)V", remap = false))
    private void hintOverride(int hint, int value) {
        // check if macOS
        boolean isMacOS = System.getProperty("os.name").toLowerCase().contains("mac");
        if (hint == GLFW.GLFW_CONTEXT_VERSION_MAJOR) {
            value = 4;
        } else if (hint == GLFW.GLFW_CONTEXT_VERSION_MINOR) {
            if (isMacOS) Main.LOGGER.info("It looks like you're on macOS, which ditched OpenGL in 2013 in favour of Metal. The maximum supported version is OpenGL 4.1.");
            value = isMacOS ? 1 : 6;
        }
        GLFW.glfwWindowHint(hint, value);
    }
}
