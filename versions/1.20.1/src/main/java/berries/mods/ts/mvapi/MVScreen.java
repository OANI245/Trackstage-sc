package berries.mods.ts.mvapi;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public abstract class MVScreen extends Screen {
    protected final MVScreenHelper screenHelper;

    {
        this.screenHelper = new MVScreenHelper(() -> minecraft);
    }

    public MVScreen(Component title) {
        super(title);
    }

    public MVScreen() {
        this(MVComponent.EMPTY);
    }

    public abstract void initScreen();

    public void renderScreen(GuiGraphicsData graphics, int mouseX, int mouseY, float f) {
        super.render(graphics.g, mouseX, mouseY, f);
    }

    @Override
    protected final void init() {
        initScreen();
    }

    @Override
    public final void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        renderScreen(new GuiGraphicsData(guiGraphics, this), mouseX, mouseY, partialTick);
    }

    public record GuiGraphicsData(GuiGraphics g, Screen screen) {
        public GuiGraphics get() {
            return g;
        }
        public Screen getScreen() {
            return screen;
        }
    }
}
