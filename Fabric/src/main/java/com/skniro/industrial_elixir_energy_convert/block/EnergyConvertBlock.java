package com.skniro.industrial_elixir_energy_convert.block;

import com.mojang.serialization.MapCodec;
import com.skniro.industrial_elixir.block.init.machine.CompressorBlock;
import com.skniro.industrial_elixir_energy_convert.block.entity.AlchemyBlockEntityType;
import com.skniro.industrial_elixir_energy_convert.block.entity.EnergyConvertBlockEntity;
import com.skniro.industrial_elixir.api.energytier.EnergyTier;
import com.skniro.industrial_elixir.block.init.machine.AbstractMachineblock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.Nullable;

public class EnergyConvertBlock extends AbstractMachineblock {
    public EnergyConvertBlock(Properties settings, EnergyTier energyTier, long capacity) {
        super(settings, energyTier, capacity);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new EnergyConvertBlockEntity(AlchemyBlockEntityType.ENERGY_CONVERT_BLOCK_ENTITY, worldPosition, blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, AlchemyBlockEntityType.ENERGY_CONVERT_BLOCK_ENTITY,
                (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        return InteractionResult.SUCCESS;
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        throw new IllegalStateException("Block does not support getCodec!");
    }
}
