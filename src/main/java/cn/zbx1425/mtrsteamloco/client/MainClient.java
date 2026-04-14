package cn.zbx1425.mtrsteamloco.client;

import cn.zbx1425.mtrsteamloco.commands.Commands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainClient {
    public static final Logger LOGGER = LoggerFactory.getLogger("Trackstage-Client");

    public static void init() {
        Commands.clientRegister();
    }
}
