package com.skniro.industrial_elixir_energy_convert.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class EnergyConvertDataGeneration implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        fabricDataGenerator.createPack().addProvider(EnergyConvertModelProvider::new);
        fabricDataGenerator.createPack().addProvider(EnergyConvertLootTableGenerator::new);
        fabricDataGenerator.createPack().addProvider(EnergyConvertEnglishLanguageProvider::new);
        fabricDataGenerator.createPack().addProvider(EnergyConvertSimplifiedChineseLanguageProvider::new);
        fabricDataGenerator.createPack().addProvider(EnergyConvertTagGenerator::new);
        fabricDataGenerator.createPack().addProvider(MachineRecipeGenerator::new);
    }
}
