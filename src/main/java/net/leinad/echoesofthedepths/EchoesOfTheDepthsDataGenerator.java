package net.leinad.echoesofthedepths;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.leinad.echoesofthedepths.datagen.ModBlockLootTableProvider;
import net.leinad.echoesofthedepths.datagen.ModBlocksTagProvider;
import net.leinad.echoesofthedepths.datagen.ModModelProvider;
import net.leinad.echoesofthedepths.datagen.ModRecipeProvider;

public class EchoesOfTheDepthsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModBlockLootTableProvider::new);
		pack.addProvider(ModBlocksTagProvider::new);
		pack.addProvider(ModRecipeProvider::new);
	}
}
