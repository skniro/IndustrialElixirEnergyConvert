package com.skniro.industrial_elixir_energy_convert;


import com.skniro.industrial_elixir_energy_convert.block.EnergyConverterBlocks;
import com.skniro.industrial_elixir_energy_convert.block.entity.AlchemyBlockEntityType;
import com.skniro.industrial_elixir_energy_convert.item.GrowableOresItems;
import com.skniro.industrial_elixir.item.ModCreativeTab;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;


public class ModContent {


    public static void registerItem(){
        GrowableOresItems.shield_item();
    }
    public static void registerBlock(){
        EnergyConverterBlocks.registerGrowableOresBlocks();
        AlchemyBlockEntityType.registerMapleBlockEntityType();
        AlchemyBlockEntityType.registerEnergyConvert();
    }
    public static void CreativeTab() {
        CreativeModeTabEvents.modifyOutputEvent(ModCreativeTab.Machine).register(content -> {
            content.accept(EnergyConverterBlocks.Energy_Convert_Block);

        });
    }
}
