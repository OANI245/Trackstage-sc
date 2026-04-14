package cn.zbx1425.mtrsteamloco.ml;

import cn.zbx1425.mtrsteamloco.Main;
import cn.zbx1425.mtrsteamloco.block.Blocks;
import cn.zbx1425.mtrsteamloco.commands.Commands;
import cn.zbx1425.mtrsteamloco.item.Items;
import cn.zbx1425.mtrsteamloco.network.Packets;
import net.fabricmc.api.ModInitializer;

public class MLMain implements ModInitializer {
    @Override
    public void onInitialize() {
        Main.init();
    }
}
