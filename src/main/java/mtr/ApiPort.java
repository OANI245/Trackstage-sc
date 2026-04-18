package mtr;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

public interface ApiPort {
    boolean IS_PORTED_API = true;
    String API_VERSION = "a3.2.2+MTR4.0.3";
    Class<?> LEGACY_MODINFO_CLASS = Keys.class;

    Logger LOGGER = LoggerFactory.getLogger("MTR3-Api-Port");
    @Environment(EnvType.CLIENT) Logger LOGGER_CLIENT = LoggerFactory.getLogger("MTR3-Api-Port-Client");

    static void initApiCommon() {
        LOGGER.info("Initializing API...");
    }

    @Environment(EnvType.CLIENT)
    static void initApiClient() {
        LOGGER_CLIENT.info("Initializing API Client Side...");
        ClientEntityEvents.ENTITY_LOAD.register((entity, clientWorld) -> {
            if (entity == Minecraft.getInstance().player) {
                mtr.MTRClient.isReplayMod = entity.getClass().toGenericString().toLowerCase(Locale.ENGLISH).contains("replaymod");
            }
        });
    }
}