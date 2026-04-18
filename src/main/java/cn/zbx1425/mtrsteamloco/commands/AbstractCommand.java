package cn.zbx1425.mtrsteamloco.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;

public abstract class AbstractCommand {
    AbstractCommand() {}

    abstract void register(CommandDispatcher<? extends SharedSuggestionProvider> dispatcher, CommandBuildContext registryAccess, Commands.CommandSelection environment);
}
