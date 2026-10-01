package com.skniro.industrial_elixir_energy_convert.item;

import com.skniro.industrial_elixir_energy_convert.GrowableOresExtension;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

public class GrowableOresItems {
    private static Item registerItem(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(GrowableOresExtension.MOD_ID, name), item);
    }

    public static void shield_item(){
      GrowableOresExtension.LOGGER.debug("register shield item.");
    }
}
