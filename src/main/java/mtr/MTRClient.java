package mtr;

import net.minecraft.client.Minecraft;

/**
 * MTR v3.2.2 API Port, Not Initializable Entry
 */
public class MTRClient {
    static boolean isReplayMod;

    public static boolean isReplayMod() {
        return isReplayMod;
    }

    public static float getLastFrameDuration() {
        if (Minecraft.getInstance().isPaused()) return 0;
        return isReplayMod ? 20F / 60 :
                //? >= 1.20.5 {
                /*Minecraft.getInstance().getTimer().getGameTimeDeltaTicks()*///? } else {
                Minecraft.getInstance().getFrameTime()
                //? }
        ;
    }
}
