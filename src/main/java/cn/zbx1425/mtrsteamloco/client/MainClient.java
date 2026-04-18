package cn.zbx1425.mtrsteamloco.client;

import cn.zbx1425.mtrsteamloco.commands.Commands;
import cn.zbx1425.sowcer.util.DrawContext;
import cn.zbx1425.sowcerext.reuse.AtlasManager;
import cn.zbx1425.sowcerext.reuse.DrawScheduler;
import cn.zbx1425.sowcerext.reuse.ModelManager;
import mtr.ApiPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainClient {
    public static final Logger LOGGER = LoggerFactory.getLogger("Trackstage-Client");

    public static final DrawScheduler drawScheduler = new DrawScheduler();
    public static ModelManager modelManager = new ModelManager();
    public static AtlasManager atlasManager = new AtlasManager();

    public static DrawContext drawContext = new DrawContext();

    public static void init() {
        ApiPort.initApiClient();
        Commands.clientRegister();
    }
}
