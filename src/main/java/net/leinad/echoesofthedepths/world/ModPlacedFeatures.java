package net.leinad.echoesofthedepths.world;

import net.leinad.echoesofthedepths.EchoesOfTheDepths;
import net.minecraft.registry.BuiltinRegistries;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> RESONITE_ORE_PLACED_KEY = registerKey("resonite_ore_placed");

    public static final RegistryKey<PlacedFeature> LARGE_CRYSTAL_PLACED_KEY = registerKey("large_crystal_placed");

    public static final RegistryKey<PlacedFeature> HUGE_SCULK_FUNGUS_PLACED_KEY = registerKey("huge_sculk_fungus_placed");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatures = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, RESONITE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.RESONITE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(2,
                        HeightRangePlacementModifier.trapezoid(YOffset.BOTTOM, YOffset.fixed(256))));

        register(context, LARGE_CRYSTAL_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.LARGE_CRYSTAL_KEY),
                List.of(
                        CountPlacementModifier.of(2),
                        SquarePlacementModifier.of(),
                        HeightRangePlacementModifier.uniform(
                                YOffset.fixed(0),
                                YOffset.fixed(142)
                        ),
                        BiomePlacementModifier.of()
                )
        );

        register(context, HUGE_SCULK_FUNGUS_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.HUGE_SCULK_FUNGUS_KEY),
                List.of(
                        CountPlacementModifier.of(50),
                        SquarePlacementModifier.of(),
                        HeightRangePlacementModifier.uniform(
                                YOffset.fixed(0),
                                YOffset.fixed(142)
                        ),
                        BiomePlacementModifier.of()
                ));
    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(EchoesOfTheDepths.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
