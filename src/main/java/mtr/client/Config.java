package mtr.client;

import org.mtr.mod.config.Client;

public class Config {
    public static boolean useMTRFont() {
        return getMTR4ClientConfig().getUseMTRFont();
    }

    public static boolean showAnnouncementMessages() {
        return getMTR4ClientConfig().getChatAnnouncements();
    }

    public static boolean useTTSAnnouncements() {
        return getMTR4ClientConfig().getTextToSpeechAnnouncements();
    }

    public static boolean hideTranslucentParts() {
        return getMTR4ClientConfig().getHideTranslucentParts();
    }

    public static boolean shiftToToggleSitting() {
        return false;
    }

    public static int languageOptions() {
        return getMTR4ClientConfig().getLanguageDisplay().ordinal();
    }

    public static boolean hideSpecialRailColors() {
        return true;
    }

    public static boolean useDynamicFPS() {
        return true;
    }

    public static int trackTextureOffset() {
        return 2;
    }

    public static int dynamicTextureResolution() {
        return getMTR4ClientConfig().getDynamicTextureResolution();
    }

    public static int trainRenderDistanceRatio() {
        return 15;
    }

    private static Client getMTR4ClientConfig() {
        return org.mtr.mod.config.Config.getClient();
    }
}
