package com.skniro.industrial_elixir_energy_convert.item;

import com.skniro.industrial_elixir_energy_convert.GrowableOresExtension;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.DeferredRegister;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, GrowableOresExtension.MOD_ID);





    public static void register(BusGroup eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
