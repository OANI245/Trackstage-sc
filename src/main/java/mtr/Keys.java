package mtr;

@SuppressWarnings("unused")
public interface Keys {
    String PATREON_API_KEY = "";
    String MOD_VERSION = getMTRModVersion();
    boolean LIFTS_ONLY = false;
    boolean TEST_SERVER = false;

    static String getMTRModVersion() {
        try {
            var f = Class.forName("org.mtr.mod.Keys").getField("MOD_VERSION");
            return f.get(null).toString();
        } catch (Throwable e) {
            return "0.0.0";
        }
    }
}
