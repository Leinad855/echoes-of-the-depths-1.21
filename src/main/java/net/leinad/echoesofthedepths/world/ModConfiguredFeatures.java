package net.leinad.echoesofthedepths.world;

import net.leinad.echoesofthedepths.EchoesOfTheDepths;
import net.leinad.echoesofthedepths.block.ModBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.*;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> RESONITE_ORE_KEY = registerKey("resonite_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> BONE_STONE_PATCH_KEY = registerKey("bone_stone_patch_key");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest deepslateReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreFeatureConfig.Target> overworldResoniteOres =
                List.of(OreFeatureConfig.createTarget(deepslateReplaceables, ModBlocks.RESONITE_ORE.getDefaultState()));

        register(context, RESONITE_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldResoniteOres, 2, 0.5F));
        register(context, BONE_STONE_PATCH_KEY, Feature.SCULK_PATCH, new SculkPatchFeatureConfig(32, 500, 64, 8, 8, ConstantIntProvider.create(0), 0.0F));

    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(EchoesOfTheDepths.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
