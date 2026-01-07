package net.leinad.echoesofthedepths.world.modFeatures;

import com.mojang.serialization.Codec;
import net.leinad.echoesofthedepths.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PointedDripstoneBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import org.jetbrains.annotations.Nullable;

public class LargeResoniteCrystalFeature extends Feature<LargeResoniteCrystalFeatureConfig> {
    public LargeResoniteCrystalFeature(Codec<LargeResoniteCrystalFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<LargeResoniteCrystalFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        Random random = context.getRandom();
        LargeResoniteCrystalFeatureConfig config = context.getConfig();
        BlockPos origin = context.getOrigin();

        int minHeight = config.minHeight;
        int maxHeight = config.maxHeight;
        int radius = config.radius;
        Direction direction = config.direction;

        BlockPos startPos = findStartPos(world, origin, direction, 30);

        if (startPos == null){
            return false;
        }

        int height = random.nextBetween(minHeight, maxHeight);
        int yStep = direction == Direction.UP ? 1 : -1;

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                BlockPos.Mutable pos = startPos.add(x, 0, y).mutableCopy();

                int distance = pos.getManhattanDistance(startPos);

                for (int h = 0; h < height - distance; h++){
                    if (world.isOutOfHeightLimit(pos)) break;

                    int randomN = random.nextBetween(0, 101);
                    if (randomN > 100 - (distance * radius * 2)){
                        break;
                    }

                    for (int i = 0; i < 100; i++) {
                        if (world.isOutOfHeightLimit(pos)) {
                            break;
                        }

                        if (world.getBlockState(pos.add(0, -yStep, 0).mutableCopy()).isSolidBlock(world, pos.add(0, -yStep, 0).mutableCopy())) {
                            break;
                        }

                        pos.move(0, -yStep, 0);
                    }

                    world.setBlockState(
                            pos,
                            ModBlocks.RESONITE_CRYSTAL.getDefaultState(),
                            PointedDripstoneBlock.NOTIFY_LISTENERS
                    );

                    pos.move(0,yStep , 0);
                }

            }
        }

        return true;
    }

    @Nullable
    private  BlockPos findStartPos(StructureWorldAccess world, BlockPos origin, Direction direction, int maxDistance) {

        BlockPos.Mutable pos = origin.mutableCopy();

        int step = direction == Direction.UP ? -1 : 1;

        for (int i = 0; i < maxDistance; i++) {
            pos.move(0, step, 0);

            if (world.isOutOfHeightLimit(pos)) {
                return null;
            }

            if (world.getBlockState(pos).isSolidBlock(world, pos)) {
                return pos.offset(direction);
            }
        }

        return null;
    }
}
