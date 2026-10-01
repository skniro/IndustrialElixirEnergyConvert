package com.skniro.industrial_elixir_energy_convert.block.entity;

import com.skniro.industrial_elixir_energy_convert.init.FurnitureStrings;
import com.skniro.industrial_elixir.block.entity.machine.AbstractMachineEntity;
import com.skniro.industrial_elixir.block.init.machine.AbstractMachineblock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.EnergyStorageUtil;
import team.reborn.energy.api.base.SimpleEnergyStorage;

public class EnergyConvertBlockEntity extends AbstractMachineEntity {
    public static final long CONVERSION_RATIO = 2;
    private static final String CONVERTED_ENERGY_KEY = "energy_convert.converted_energy";
    private final SimpleEnergyStorage convertedEnergy;

    public EnergyConvertBlockEntity(BlockPos pos, BlockState state) {
        this(AlchemyBlockEntityType.ENERGY_CONVERT_BLOCK_ENTITY, pos, state);
    }

    public EnergyConvertBlockEntity(BlockEntityType entityType, BlockPos pos, BlockState state) {
        super(entityType, pos, state);
        this.convertedEnergy = new SimpleEnergyStorage(getConvertedCapacity(), 0, getConversionRate()) {
            @Override
            protected void onFinalCommit() {
                setChanged();
            }
        };
    }

    public EnergyStorage getConvertedEnergyStorage() {
        return convertedEnergy;
    }

    private long getConversionRate() {
        return Math.max(1, getEffectiveTier().getMaxOutput() / CONVERSION_RATIO);
    }

    private long getConvertedCapacity() {
        return Math.max(1, getMachineCapacity() / CONVERSION_RATIO);
    }

    @Override
    public long getMachineCapacity() {
        if (getBlockState().getBlock() instanceof AbstractMachineblock machine) {
            return machine.getMaxCapacity();
        }
        return 512;
    }

    @Override
    public void tick(Level world, BlockPos pos, BlockState state) {
        if (world.isClientSide()) {
            return;
        }

        boolean changed = convertEnergy();
        changed |= pushConvertedEnergy();

        if (changed) {
            setChanged();
        }

        boolean active = energyContainer.amount > 0 || convertedEnergy.amount > 0;
        if (state.getValue(AbstractMachineblock.LIT) != active) {
            world.setBlock(pos, state.setValue(AbstractMachineblock.LIT, active), 3);
        }
    }

    private boolean convertEnergy() {
        long free = convertedEnergy.capacity - convertedEnergy.amount;
        if (free <= 0) {
            return false;
        }

        long maxInput = getConversionRate() * CONVERSION_RATIO;
        long available = Math.min(energyContainer.amount, maxInput);
        long converted = Math.min(available / CONVERSION_RATIO, free);
        if (converted <= 0) {
            return false;
        }

        energyContainer.amount -= converted * CONVERSION_RATIO;
        convertedEnergy.amount += converted;
        return true;
    }

    private boolean pushConvertedEnergy() {
        if (convertedEnergy.amount <= 0) {
            return false;
        }

        boolean moved = false;
        long rate = getConversionRate();
        for (Direction direction : Direction.values()) {
            if (convertedEnergy.amount <= 0) {
                break;
            }
            EnergyStorage target = EnergyStorage.SIDED.find(level, worldPosition.relative(direction), direction.getOpposite());
            if (target == null) {
                continue;
            }
            if (EnergyStorageUtil.move(convertedEnergy, target, rate, null) > 0) {
                moved = true;
            }
        }
        return moved;
    }

    /** The converter has no slots of its own, nothing may be piped in or out. */
    @Override
    public int[] getSlotsForFace(Direction direction) {
        return new int[0];
    }

    @Override
    protected void saveAdditional(ValueOutput nbt) {
        super.saveAdditional(nbt);
        nbt.putLong(CONVERTED_ENERGY_KEY, convertedEnergy.amount);
    }

    @Override
    protected void loadAdditional(ValueInput nbt) {
        super.loadAdditional(nbt);
        convertedEnergy.amount = nbt.getLongOr(CONVERTED_ENERGY_KEY, 0);
    }

    @Override
    public RecipeType<?> getCurrentRecipeType() {
        return null;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable(FurnitureStrings.Energy_Convert);
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return null;
    }
}
