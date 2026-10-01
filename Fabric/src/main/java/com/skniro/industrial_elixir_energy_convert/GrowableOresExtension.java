package com.skniro.industrial_elixir_energy_convert;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class GrowableOresExtension implements ModInitializer {
    public static final String MOD_ID = "industrial_elixir_energy_convert";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModContent.registerItem();
        ModContent.registerBlock();
        ModContent.CreativeTab();
    }
}
