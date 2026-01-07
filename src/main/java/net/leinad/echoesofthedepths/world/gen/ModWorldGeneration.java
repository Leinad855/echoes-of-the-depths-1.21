package net.leinad.echoesofthedepths.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.leinad.echoesofthedepths.world.Biomes.ModBiomesKeys;
import net.leinad.echoesofthedepths.world.ModOrePlacement;
import net.leinad.echoesofthedepths.world.ModPlacedFeatures;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class ModWorldGeneration {
    public static void generateModWorldGen(){
        ModOreGeneration.generateOres();

        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(ModBiomesKeys.RESONITE_CAVERNS),
                GenerationStep.Feature.UNDERGROUND_DECORATION,
                ModPlacedFeatures.LARGE_CRYSTAL_PLACED_KEY
        );

        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(ModBiomesKeys.DEEP_FIELDS),
                GenerationStep.Feature.UNDERGROUND_DECORATION,
                ModPlacedFeatures.HUGE_SCULK_FUNGUS_PLACED_KEY
        );
    }
}
