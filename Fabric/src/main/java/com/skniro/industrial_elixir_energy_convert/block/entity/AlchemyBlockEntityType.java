package com.skniro.industrial_elixir_energy_convert.block.entity;

import com.mojang.datafixers.types.Type;
import com.skniro.industrial_elixir_energy_convert.GrowableOresExtension;
import com.skniro.industrial_elixir_energy_convert.block.EnergyConverterBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;


public class AlchemyBlockEntityType {
    public static final BlockEntityType<EnergyConvertBlockEntity> ENERGY_CONVERT_BLOCK_ENTITY;

    static {
        ENERGY_CONVERT_BLOCK_ENTITY = create("energy_convert", FabricBlockEntityTypeBuilder.create(EnergyConvertBlockEntity::new, EnergyConverterBlocks.Energy_Convert_Block));
    }

    private static <T extends BlockEntity> BlockEntityType create(String id, FabricBlockEntityTypeBuilder<T> builder) {
        Type<?> type = Util.fetchChoiceType(References.BLOCK_ENTITY, id);
        return (BlockEntityType) Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Identifier.fromNamespaceAndPath(GrowableOresExtension.MOD_ID,id), builder.build(null));
    }

    public static void registerMapleBlockEntityType() {
        GrowableOresExtension.LOGGER.debug("Registering MapleBlockEntityType for " + GrowableOresExtension.MOD_ID);
    }

    public static void registerEnergyConvert() {
        com.skniro.industrial_elixir.energy.api.EnergyStorage.SIDED.registerForBlockEntity(
                (blockEntity, direction) -> blockEntity.energyContainer.getSideStorage(direction), ENERGY_CONVERT_BLOCK_ENTITY);
        team.reborn.energy.api.EnergyStorage.SIDED.registerForBlockEntity(
                (blockEntity, direction) -> blockEntity.getConvertedEnergyStorage(), ENERGY_CONVERT_BLOCK_ENTITY);
    }

}
