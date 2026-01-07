package net.leinad.echoesofthedepths.world.Biomes;

import net.leinad.echoesofthedepths.EchoesOfTheDepths;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class ModBiomesKeys {
    public static final RegistryKey<Biome> DEEP_FIELDS = register("deep_fields");
    public static final RegistryKey<Biome> RESONITE_CAVERNS = register("resonite_caverns");

    private static RegistryKey<Biome> register(String name) {
        return RegistryKey.of(RegistryKeys.BIOME, Identifier.of(EchoesOfTheDepths.MOD_ID, name));
    }
}
