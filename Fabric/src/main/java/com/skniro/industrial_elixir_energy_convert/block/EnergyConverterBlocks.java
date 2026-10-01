package com.skniro.industrial_elixir_energy_convert.block;

import com.skniro.industrial_elixir_energy_convert.GrowableOresExtension;
import com.skniro.industrial_elixir.api.energytier.EnergyTier;

import java.util.function.Function;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class EnergyConverterBlocks {
    public static final Block Energy_Convert_Block = registerBlock("energy_convert",
            properties -> new EnergyConvertBlock(properties, EnergyTier.TIER5, 64_000L),
            (BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.0F, 3.0F)));


    private static Block registerBlockWithoutItem(String name, Block block) {
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(GrowableOresExtension.MOD_ID, name), block);
    }

    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties settings) {
        Block block = (Block)factory.apply(settings.setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(GrowableOresExtension.MOD_ID, name))));
        registerBlockItem(name, block);
        return Registry.register(BuiltInRegistries.BLOCK, ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(GrowableOresExtension.MOD_ID, name)), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(BuiltInRegistries.ITEM, ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(GrowableOresExtension.MOD_ID, name)),
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(GrowableOresExtension.MOD_ID, name)))));
    }

    public static void registerGrowableOresBlocks() {
        GrowableOresExtension.LOGGER.info("Registering GrowableOres Blocks for " + GrowableOresExtension.MOD_ID);
    }
}
