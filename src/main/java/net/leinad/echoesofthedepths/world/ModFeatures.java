package net.leinad.echoesofthedepths.world;

import net.leinad.echoesofthedepths.EchoesOfTheDepths;
import net.leinad.echoesofthedepths.world.modFeatures.HugeSculkFungusFeature;
import net.leinad.echoesofthedepths.world.modFeatures.LargeResoniteCrystalFeature;
import net.leinad.echoesofthedepths.world.modFeatures.LargeResoniteCrystalFeatureConfig;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.HugeMushroomFeature;
import net.minecraft.world.gen.feature.HugeMushroomFeatureConfig;

public class ModFeatures {

    public static final Feature<LargeResoniteCrystalFeatureConfig> LARGE_CRYSTAL =
            Registry.register(
                    Registries.FEATURE,
                    Identifier.of(EchoesOfTheDepths.MOD_ID, "large_crystal"),
                    new LargeResoniteCrystalFeature(LargeResoniteCrystalFeatureConfig.CODEC)
            );

    public static final Feature<HugeMushroomFeatureConfig> HUGE_SCULK_FUNGUS =
            Registry.register(
                    Registries.FEATURE,
                    Identifier.of(EchoesOfTheDepths.MOD_ID, "huge_sculk_fungus"),
                    new HugeSculkFungusFeature(HugeMushroomFeatureConfig.CODEC)
            );

    public static void register() {

    }

}
