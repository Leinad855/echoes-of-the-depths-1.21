package net.leinad.echoesofthedepths.world;

import net.leinad.echoesofthedepths.EchoesOfTheDepths;
import net.leinad.echoesofthedepths.block.ModBlocks;
import net.leinad.echoesofthedepths.world.modFeatures.LargeResoniteCrystalFeatureConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> RESONITE_ORE_KEY = registerKey("resonite_ore");

    public static final RegistryKey<ConfiguredFeature<?, ?>> LARGE_CRYSTAL_KEY = registerKey("large_crystal");

    public static final RegistryKey<ConfiguredFeature<?, ?>> HUGE_SCULK_FUNGUS_KEY = registerKey("huge_sculk_fungus");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest deepslateReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreFeatureConfig.Target> overworldResoniteOres =
                List.of(OreFeatureConfig.createTarget(deepslateReplaceables, ModBlocks.RESONITE_ORE.getDefaultState()));


        register(context, RESONITE_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldResoniteOres, 2, 0.5F));

        register(context, LARGE_CRYSTAL_KEY, ModFeatures.LARGE_CRYSTAL, new LargeResoniteCrystalFeatureConfig(
                10, 20, Direction.UP, 4));

        register(context, HUGE_SCULK_FUNGUS_KEY, ModFeatures.HUGE_SCULK_FUNGUS, new HugeMushroomFeatureConfig(
                BlockStateProvider.of(ModBlocks.SCULK_FUNGUS), BlockStateProvider.of(Blocks.MUSHROOM_STEM), 2));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(EchoesOfTheDepths.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

    public static void register(
            Registerable<ConfiguredFeature<?, ?>> registerable, RegistryKey<ConfiguredFeature<?, ?>> key, Feature<DefaultFeatureConfig> feature
    ) {
        register(registerable, key, feature, FeatureConfig.DEFAULT);
    }

}
