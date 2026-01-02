package net.leinad.echoesofthedepths.Item.custom;

import net.leinad.echoesofthedepths.component.ModDataComponentsType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class ResoniteShovelItem extends ShovelItem {
    public static final int MAX_CHARGE = 20;

    public ResoniteShovelItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    public static List<BlockPos> getBlocksToBeDestroyed(int range, BlockPos initialBlockPos, ServerPlayerEntity player) {
        List<BlockPos> positions = new ArrayList<>();
        HitResult hit = player.raycast(20, 0, false);
        if (hit.getType() == HitResult.Type.BLOCK) {

            for (int x = -range; x <= range; x++) {
                for (int y = -range; y <= range; y++) {
                    positions.add(new BlockPos(initialBlockPos.getX() + x, initialBlockPos.getY(), initialBlockPos.getZ() + y));
                }
            }

        }

        return positions;
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
        if (stack.get(ModDataComponentsType.CHARGE) != MAX_CHARGE && state.isIn(BlockTags.SHOVEL_MINEABLE)) {
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
