package cn.zbx1425.mtrsteamloco.ml;

import cn.zbx1425.mtrsteamloco.client.MainClient;
import net.fabricmc.api.ClientModInitializer;

public class MLClientMain implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MainClient.init();
    }
}
