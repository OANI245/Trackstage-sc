package cn.zbx1425.mtrsteamloco.commands;

import cn.zbx1425.mtrsteamloco.Main;
import cn.zbx1425.mtrsteamloco.client.MainClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

public abstract class Commands {
    static Set<AbstractCommand> COMMANDS = new HashSet<>();

    static {
        COMMANDS.add(new TrackstageCommand());
    }

    public static void commonRegister() {
        COMMANDS.forEach((v) -> {
            try {
                var c = v.getClass();
                var a = c.getAnnotations();
                for (Annotation annotation : a) {
                    if (annotation instanceof Command) {
                        var envVal = ((Command) annotation).value();
                        if (!envVal.equals(Command.CommandEnvType.COMMON)) {
                            return;
                        }
                        CommandRegistrationCallback.EVENT.register(v::register);
                        break;
                    }
                }
            } catch (Throwable t) {
                Main.LOGGER.error("Unable register command {}:", v.getClass().getName());
                t.printStackTrace();
            }
        });
    }

    @Environment(EnvType.CLIENT)
    public static void clientRegister() {
        COMMANDS.forEach((v) -> {
            try {
                var c = v.getClass();
                var a = c.getAnnotations();
                for (Annotation annotation : a) {
                    if (annotation instanceof Command) {
                        var envVal = ((Command) annotation).value();
                        if (!envVal.equals(Command.CommandEnvType.CLIENT)) {
                            return;
                        }
                        ClientCommandRegistrationCallback.EVENT.register((z, b) ->
                                v.register(z, b, null));
                        break;
                    }
                }
            } catch (Throwable t) {
                MainClient.LOGGER.error("Unable register client command {}:", v.getClass().getName());
                t.printStackTrace();
            }
        });
    }
}
