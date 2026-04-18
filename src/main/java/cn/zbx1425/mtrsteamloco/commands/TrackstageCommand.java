package cn.zbx1425.mtrsteamloco.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;

@Command(Command.CommandEnvType.CLIENT)
public class TrackstageCommand extends AbstractCommand {
    @Override
    void register(CommandDispatcher<? extends SharedSuggestionProvider> dispatcher, CommandBuildContext registryAccess, Commands.CommandSelection environment) {
        ((CommandDispatcher<FabricClientCommandSource>) dispatcher).register(ClientCommandManager.literal("trackstage")
                .then(ClientCommandManager.literal("settings")
                        .executes((csk) -> {
                            //TODO 2026/4/18 Settings Screen
                            return 0;
                        }))
                .then(ClientCommandManager.literal("stats")
                        .executes((csk) -> {
                            //TODO 2026/4/18 Stats
                            return 0;
                        })));
    }
}
