package cn.zbx1425.mtrsteamloco;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.CrashReport;
import net.minecraft.client.Minecraft;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Client Configs
 * <br/>Read file by Jackson ObjectMapper
 */
@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
public class Config {
    private static Config instance = new Config();

    public static final String CONFIG_PATH =
            FabricLoader.getInstance()
                    .getConfigDir().toAbsolutePath() + File.separator + "berries_trackstage_client.json";

    @JsonProperty("trackstage_obj_model_render")        public boolean trackstageObjModelRender     = true;
    @JsonProperty("hide_trains")                        public boolean hideTrains                   = false;
    @JsonProperty("hide_taking_train")                  public boolean hideTakingTrain              = false;
    @JsonProperty("hide_rails")                         public boolean hideRails                    = false;

    private Config() {}

    public static void read() {
        read(getDefaultObjectMapper());
    }

    public static void read(ObjectMapper r) {
        File f = new File(CONFIG_PATH);
        if (!f.exists()) {
            Main.LOGGER.info("Config file is not exists, creating a new file...");
            instance = new Config();
            instance.save(r);
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            instance = r.readValue(br, Config.class);
        } catch (Exception e) {
            Main.LOGGER.error("Failed to read config file", e);
        }
    }

    public void save() {
        save(getDefaultObjectMapper());
    }

    public void save(ObjectMapper s) {
        File f = new File(CONFIG_PATH);
        try {
            if (!f.exists() && !f.createNewFile()) {
                Main.LOGGER.error("Failed to create config file");
                return;
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
                s.writeValue(bw, this);
            }
        } catch (IOException e) {
            Main.LOGGER.error("Failed to create config file", e);
        }
    }

    public Config copy() {
        try {
            var t = new Config();
            var fields = Config.class.getFields();
            for (Field field : fields) {
                field.set(t, field.get(this));
            }
            return t;
        } catch (Exception e) {
            return null;
        }
    }

    public static void restoreToDefault() {
        instance = new Config();
    }

    @NotNull
    public static Config getInstance() {
        if (instance == null) { read(); }
        return Objects.requireNonNull(instance);
    }

    private static ObjectMapper getDefaultObjectMapper() {
        return new ObjectMapper().setDefaultPrettyPrinter(new DefaultPrettyPrinter());
    }
}
