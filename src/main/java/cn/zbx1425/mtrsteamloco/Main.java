package cn.zbx1425.mtrsteamloco;

import cn.zbx1425.mtrsteamloco.block.Blocks;
import cn.zbx1425.mtrsteamloco.commands.Commands;
import cn.zbx1425.mtrsteamloco.item.Items;
import cn.zbx1425.mtrsteamloco.network.Packets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static final Logger LOGGER = LoggerFactory.getLogger("Trackstage");

    public static void init() {
        LOGGER.info("{} {}", UFEInfo.MOD_NAME, UFEInfo.VERSION_NAME);
        LOGGER.info("Created By Zbx1425, Aphrodite281, CodeCat");
        LOGGER.info("Starting...");

        Blocks.initClass();
        Items.initClass();
        Commands.commonRegister();
        Packets.commonRegister();
    }
}
