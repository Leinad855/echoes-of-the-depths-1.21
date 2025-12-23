package net.leinad.echoesofthedepths.util;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.leinad.echoesofthedepths.Item.custom.ResoniteAxeItem;
import net.leinad.echoesofthedepths.Item.custom.ResonitePickaxeItem;
import net.leinad.echoesofthedepths.component.ModDataComponentsType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;

public class ResoniteAxeAbilityEvent implements PlayerBlockBreakEvents.Before{

    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    @Override
    public boolean beforeBlockBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity) {
        ItemStack mainHandItem = player.getMainHandStack();

        if (mainHandItem.getItem() instanceof ResoniteAxeItem axe && mainHandItem.get(ModDataComponentsType.CHARGE) == ResoniteAxeItem.MAX_CHARGE && player instanceof ServerPlayerEntity serverPlayer) {
            if (HARVESTED_BLOCKS.contains(pos)) {
                return true;
            }

            for (BlockPos position: ResoniteAxeItem.getBlocksToBeDestroyed(pos, serverPlayer, mainHandItem)) {
                if (pos == position || !axe.isCorrectForDrops(mainHandItem, world.getBlockState(position))) {
                    continue;
                }

                HARVESTED_BLOCKS.add(position);
                serverPlayer.interactionManager.tryBreakBlock(position);
                HARVESTED_BLOCKS.remove(position);
            }
        }

        return true;
    }
}
