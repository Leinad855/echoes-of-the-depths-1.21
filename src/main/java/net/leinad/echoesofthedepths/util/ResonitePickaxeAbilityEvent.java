package net.leinad.echoesofthedepths.util;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.leinad.echoesofthedepths.Item.ModItems;
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

public class ResonitePickaxeAbilityEvent implements PlayerBlockBreakEvents.Before{

    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    @Override
    public boolean beforeBlockBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity) {
        ItemStack mainHandItem = player.getMainHandStack();

        if (mainHandItem.getItem() instanceof ResonitePickaxeItem pickaxe && mainHandItem.get(ModDataComponentsType.CHARGE) == ResonitePickaxeItem.MAX_CHARGE && player instanceof ServerPlayerEntity serverPlayer) {
            if (HARVESTED_BLOCKS.contains(pos)) {
                return true;
            }

            for (BlockPos position: ResonitePickaxeItem.getBlocksToBeDestroyed(1, pos, serverPlayer)) {
                if (pos == position || !pickaxe.isCorrectForDrops(mainHandItem, world.getBlockState(position))) {
                    continue;
                }

                HARVESTED_BLOCKS.add(position);
                serverPlayer.interactionManager.tryBreakBlock(position);
                HARVESTED_BLOCKS.remove(position);
            }
            mainHandItem.set(ModDataComponentsType.CHARGE, 0);

            Vec3d dir = player.getRotationVector();

            for (int i = 0; i < 20; i++) {
                Vec3d vPos = player.getEyePos().add(dir.multiply(i * 0.5));
                ((ServerWorld) world).spawnParticles(
                        ParticleTypes.SONIC_BOOM, vPos.x, vPos.y, vPos.z,
                        1, 0, 0 ,0 ,0
                );
            }

            world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_WARDEN_SONIC_BOOM,
                    SoundCategory.PLAYERS, 3.0F, 1.0F);
        }

        return true;
    }
}
