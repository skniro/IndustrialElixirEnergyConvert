package com.skniro.industrial_elixir_energy_convert.datagen;

import com.skniro.industrial_elixir_energy_convert.block.EnergyConverterBlocks;
import com.skniro.industrial_elixir.api.data.recipe.ModRecipeGenerator;
import com.skniro.industrial_elixir.block.GeneralBlocks;
import com.skniro.industrial_elixir.item.GrowableOresItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.crafting.Recipe;

import java.util.concurrent.CompletableFuture;

public class MachineRecipeGenerator extends FabricRecipeProvider {
    public MachineRecipeGenerator(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }


    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, BootstrapContext<Recipe<?>> recipes, BootstrapContext<Advancement> advancements) {
        return new RecipeProvider(recipes, advancements) {
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
