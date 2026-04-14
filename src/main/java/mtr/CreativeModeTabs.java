package mtr;

import net.minecraft.world.item.CreativeModeTab;
import org.mtr.mapping.registry.CreativeModeTabHolder;

@SuppressWarnings("unused")
public interface CreativeModeTabs {
    Wrapper CORE = new Wrapper(org.mtr.mod.CreativeModeTabs.CORE);
    Wrapper RAILWAY_FACILITIES = new Wrapper(org.mtr.mod.CreativeModeTabs.RAILWAY_FACILITIES);
    Wrapper STATION_BUILDING_BLOCKS = new Wrapper(org.mtr.mod.CreativeModeTabs.STATION_BUILDING_BLOCKS);
    Wrapper ESCALATORS_LIFTS = new Wrapper(org.mtr.mod.CreativeModeTabs.ESCALATORS_LIFTS);

    class Wrapper {
        public final CreativeModeTabHolder holder;

        public Wrapper(CreativeModeTabHolder holder) {
            this.holder = holder;
        }

        public CreativeModeTab get() {
            return holder.creativeModeTab;
        }
    }
}
