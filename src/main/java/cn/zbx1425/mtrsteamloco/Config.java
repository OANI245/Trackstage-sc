package cn.zbx1425.mtrsteamloco;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.loader.api.FabricLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class Config {
    private static Config instance = null;
    private static final AttributeMap PROPERTIES = new AttributeMap();
    private static final Map<Attribute, Object> DEFAULT_VALUES = new HashMap<>();

    static {
        //general
        reg("trackstage_obj_model_render", AttributeType.BOOL, true);
        reg("enable_script", AttributeType.BOOL, true);

        //advanced
        reg("developer_mode", AttributeType.BOOL, false);
    }

    @SuppressWarnings("all")
    private static void reg(String id, AttributeType type) {
        PROPERTIES.put(id, type);
    }

    @SuppressWarnings("all")
    private static void reg(String id, AttributeType type, Object defaultValue) {
        var attr = new Attribute(id, type);
        PROPERTIES.put(id, attr);
        if (type.defaultValue.getClass().isInstance(defaultValue)) {
            DEFAULT_VALUES.put(attr, defaultValue);
        } else {
            DEFAULT_VALUES.put(attr, type.defaultValue);
        }
    }

    public static final String CONFIG_PATH = FabricLoader.getInstance().getConfigDir().toAbsolutePath().toString() + File.separator + "berries_trackstage.json";

    private Map<Attribute, Object> values;

    private Config() {
        this.values = DEFAULT_VALUES;
    }

    public static void read() {
        File f = new File(CONFIG_PATH);
        if (!f.exists()) {
            instance = new Config();
            instance.save();
            return;
        }

        try (Reader r = new BufferedReader(new FileReader(f))) {
            JsonObject root = castOrDefault(JsonParser.parseReader(r), (JsonObject) null);
            if (root == null) {
                Main.LOGGER.error("Cannot load settings file: JSON File is empty");
                onReadFailed();
                return;
            }
        } catch (Exception e) {
            Main.LOGGER.warn("Cannot load settings file: {}", e.getMessage());
            onReadFailed();
        }
    }

    public void save() {
    }

    private static void onReadFailed() {
        instance = new Config();
    }

    public Object getValue(String id) {
        return values.getOrDefault(PROPERTIES.getOrDefault(id, null), null);
    }

    public static Config getInstance() {
        if (instance == null) {
            read();
        }

        return instance;
    }

    private static <T, R> R castOrDefault(T in, R def) {
        try {
            return (R) in;
        } catch (Throwable ignored) {
            return def;
        }
    }

    public record Attribute(String id, AttributeType type) {
        public String getId() {
            return id;
        }

        public AttributeType getType() {
            return type;
        }

        @Override
        public int hashCode() {
            return (int) (((long) id.hashCode() * (long) type.hashCode()) / 2);
        }
    }

    public enum AttributeType {
        STRING(""), INT(0), BOOL(false), FLOAT(0.0f);

        private final Object defaultValue;

        private AttributeType(Object defaultValue) {
            this.defaultValue = defaultValue;
        }

        public Object getDefaultValue() {
            return defaultValue;
        }
    }

    public static class AttributeMap extends HashMap<String, Attribute> {
        public Attribute put(String key, AttributeType type) {
            return super.put(key, new Attribute(key, type));
        }

        public Attribute put(Attribute attr) {
            return super.put(attr.id, attr);
        }
    }
}
