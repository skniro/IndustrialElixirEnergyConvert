package com.skniro.industrial_elixir_energy_convert.datagen;

import com.skniro.industrial_elixir_energy_convert.block.EnergyConverterBlocks;
import com.skniro.industrial_elixir.block.GeneralBlocks;
import com.skniro.industrial_elixir.item.GrowableOresItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.concurrent.CompletableFuture;

public class EnergyConvertMachineRecipeGenerator extends FabricRecipeProvider {
    public EnergyConvertMachineRecipeGenerator(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }


    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                shaped(RecipeCategory.MISC, EnergyConverterBlocks.Energy_Convert_Block)
                        .define('G', GeneralBlocks.Machine).define('E', GrowableOresItems.RE_BATTERY).define('S', GrowableOresItems.IRON_PLATE)
                        .pattern("SES").pattern("SSS").pattern("SGS")
                        .unlockedBy("has_item", this.has(GrowableOresItems.RE_BATTERY)).save(this.output);
            }
        };
    }

    @Override
    public String getName() {
        return "ConverterMachine";
    }
}
