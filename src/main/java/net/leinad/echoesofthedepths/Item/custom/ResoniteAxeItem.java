package net.leinad.echoesofthedepths.Item.custom;

import net.leinad.echoesofthedepths.component.ModDataComponentsType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.*;

public class ResoniteAxeItem extends AxeItem {

    public static final int MAX_CHARGE = 20;

    public ResoniteAxeItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    public static List<BlockPos> getBlocksToBeDestroyed( BlockPos initialBlockPos, ServerPlayerEntity player, ItemStack mainHandItem) {
        World world = player.getWorld();
        BlockState startState = world.getBlockState(initialBlockPos);

        Set<BlockPos> visited = new HashSet<>();
        Queue<BlockPos> queue = new ArrayDeque<>();

        queue.add(initialBlockPos);
        visited.add(initialBlockPos);

        while (!queue.isEmpty()) {
            BlockPos current = queue.poll();

            for (Direction dir : Direction.values()) {
                BlockPos neighbor = current.offset(dir);

                if (visited.contains(neighbor)) continue;

                BlockState neighborState = world.getBlockState(neighbor);

                if (neighborState.isIn(BlockTags.AXE_MINEABLE)) {
                    visited.add(neighbor);
                    if (visited.size() < 128) {
                        queue.add(neighbor);
                    }
                }
            }
        }

        for (BlockPos pos: visited) {
            world.breakBlock(pos, true, player);
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

        return (List<BlockPos>) visited;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (stack.get(ModDataComponentsType.CHARGE) == null) {
            stack.set(ModDataComponentsType.CHARGE, 0);
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (stack.get(ModDataComponentsType.CHARGE) != MAX_CHARGE) {
            stack.set(ModDataComponentsType.CHARGE, stack.get(ModDataComponentsType.CHARGE) + 1);
        }
        return super.postMine(stack, world, state, pos, miner);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.literal("" + stack.get(ModDataComponentsType.CHARGE)));
        super.appendTooltip(stack, context, tooltip, type);
    }

}
