package mtr.mappings;

import berries.mods.ts.mvapi.MVComponent;
import net.minecraft.network.chat.MutableComponent;

public interface Text {
    static MutableComponent literal(String v) { return MVComponent.text(v).copy(); }
    static MutableComponent translatable(String d, Object... s) { return MVComponent.translatable(d, s).copy(); }
}
