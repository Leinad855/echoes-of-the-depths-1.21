package net.leinad.echoesofthedepths.world.modFeatures;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.feature.FeatureConfig;

public class LargeResoniteCrystalFeatureConfig implements FeatureConfig {

    public static final Codec<LargeResoniteCrystalFeatureConfig> CODEC =
            RecordCodecBuilder.create(instance -> instance.group(
                    Codec.INT.fieldOf("min_height").forGetter(c -> c.minHeight),
                    Codec.INT.fieldOf("max_height").forGetter(c -> c.maxHeight),
                    Direction.CODEC.fieldOf("direction").forGetter(c -> c.direction),
                    Codec.INT.fieldOf("radius").forGetter(c -> c.radius)
            ).apply(instance, LargeResoniteCrystalFeatureConfig::new));

    public final int minHeight;
    public final int maxHeight;
    public final Direction direction;
    public final int radius;

    public LargeResoniteCrystalFeatureConfig(
            int minHeight,
            int maxHeight,
            Direction direction,
            int radius
    ) {
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.direction = direction;
        this.radius = radius;
    }
}
