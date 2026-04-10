package berries.mods.ts.mvapi;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;

public interface MVTextDrawer {
    static void drawText(MVScreen.GuiGraphicsData guiGraphics, Font font, Component text, Alignment alignment, int x, int y, int color) {
        GuiComponent.drawString(guiGraphics.get(), font, text, alignment.calculateX(x, font.width(text.getVisualOrderText())), y, color);
    }

    static void drawText(MVScreen.GuiGraphicsData guiGraphics, Font font, FormattedCharSequence text, Alignment alignment, int x, int y, int color) {
        GuiComponent.drawString(guiGraphics.get(), font, text, alignment.calculateX(x, font.width(text)), y, color);
    }

    static void drawText(MVScreen.GuiGraphicsData guiGraphics, Font font, Component text, Alignment alignment, int x, int y, int color, boolean shadow) {
        GuiComponent.drawString(guiGraphics.get(), font, text, alignment.calculateX(x, font.width(text.getVisualOrderText())), y, color);
    }

    static void drawText(MVScreen.GuiGraphicsData guiGraphics, Font font, FormattedCharSequence text, Alignment alignment, int x, int y, int color, boolean shadow) {
        GuiComponent.drawString(guiGraphics.get(), font, text, alignment.calculateX(x, font.width(text)), y, color);
    }

    enum Alignment {
        LEFT(0), CENTER(1), RIGHT(2);

        private final int mode;

        private Alignment(int mode) {
            this.mode = mode;
        }

        public int calculateX(int x, int length) {
            switch (mode) {
                case 1 -> {
                    return x - (length / 2);
                }
                case 2 -> {
                    return x - length;
                }
                default -> {
                    return x;
                }
            }
        }
    }
}
