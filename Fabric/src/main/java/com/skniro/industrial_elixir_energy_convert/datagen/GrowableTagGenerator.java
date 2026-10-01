package com.skniro.industrial_elixir_energy_convert.datagen;

import com.skniro.industrial_elixir_energy_convert.block.EnergyConverterBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import java.util.concurrent.CompletableFuture;

import static net.minecraft.tags.BlockTags.MINEABLE_WITH_PICKAXE;

public class GrowableTagGenerator extends FabricTagsProvider.BlockTagsProvider {
   public GrowableTagGenerator(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
   }
   @Override
   protected void addTags(HolderLookup.Provider arg) {
      builder(MINEABLE_WITH_PICKAXE)
              .add(EnergyConverterBlocks.Energy_Convert_Block.builtInRegistryHolder().key());

   }
}