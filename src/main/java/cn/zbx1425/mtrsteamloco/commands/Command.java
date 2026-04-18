package cn.zbx1425.mtrsteamloco.commands;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
    CommandEnvType value() default CommandEnvType.COMMON;

    enum CommandEnvType {
        CLIENT, COMMON
    }
}
