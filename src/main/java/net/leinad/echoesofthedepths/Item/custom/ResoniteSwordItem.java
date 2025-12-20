package net.leinad.echoesofthedepths.Item.custom;

import net.leinad.echoesofthedepths.Item.ModItems;
import net.leinad.echoesofthedepths.component.ModDataComponentsType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class ResoniteSwordItem extends SwordItem {

    private static final int STORE_SOULS_COOLDOWN = 12;
    private static final int MAX_CHARGE = 10;

    public ResoniteSwordItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    public static @Nullable Entity getLookedAtEntity(PlayerEntity player, double maxDistance) {
        Vec3d start = player.getCameraPosVec(1.0f);
        Vec3d direction = player.getRotationVec(1.0f);
        Vec3d end = start.add(direction.multiply(maxDistance));

        Box searchBox = player.getBoundingBox()
                .stretch(direction.multiply(maxDistance))
                .expand(1.0, 1.0, 1.0);

        EntityHitResult result = ProjectileUtil.raycast(
                player,
                start,
                end,
                searchBox,
                entity -> !entity.isSpectator() && entity.isAlive() && entity != player,
                maxDistance * maxDistance
        );

        return result != null ? result.getEntity() : null;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (stack.get(ModDataComponentsType.CHARGE) == null) {
            stack.set(ModDataComponentsType.CHARGE, 0);
            stack.set(ModDataComponentsType.COOLDOWN_USE, 0L);
        }

        super.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity player) {
            if (stack.get(ModDataComponentsType.CHARGE) == MAX_CHARGE) {
                int i = this.getMaxUseTime(stack, user) - remainingUseTicks;
                float f = getPullProgress(i);
                if (!(f < 0.1)) {

                    Entity target = getLookedAtEntity(player, 20.0);
                    if (target instanceof LivingEntity livingEntity) {
                        sonicBoomAttack(world, player, livingEntity);
                    }else {
                        sonicBoomAttack(world, player, null);
                    }

                    player.incrementStat(Stats.USED.getOrCreateStat(this));
                    stack.damage(10, player, EquipmentSlot.MAINHAND);
                }
            }
        }
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        return super.useOnEntity(stack, user, entity, hand);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        if (itemStack.get(ModDataComponentsType.CHARGE) != MAX_CHARGE) {
            return TypedActionResult.fail(itemStack);
        }

        user.setCurrentHand(hand);
        return TypedActionResult.consume(itemStack);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (world.isClient && user instanceof PlayerEntity player) {
            int used = getMaxUseTime(stack, user) - remainingUseTicks;

            Vec3d eyePos = player.getEyePos();
            Vec3d forward = player.getRotationVec(1.0f);
            Vec3d right = forward.crossProduct(new Vec3d(0, 1, 0)).normalize();

            boolean rightHanded = player.getMainArm() == Arm.RIGHT;
            Vec3d handOffset = rightHanded ? right.multiply(0.3) : right.multiply(-0.3);

            Vec3d particlePos = eyePos
                    .add(forward.multiply(0.6))  // forward
                    .add(handOffset)              // sideways
                    .add(0, -0.4, 0);

            if (used % 5 == 0) {
                world.addParticle(ParticleTypes.SCULK_SOUL, particlePos.x, particlePos.y,
                        particlePos.z, 0, 0.05, 0);
            }
        }
    }

    public static float getPullProgress(int useTicks) {
        float f = useTicks / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 50000;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        long currentTime = attacker.getWorld().getTime();
        long nextUse = stack.get(ModDataComponentsType.COOLDOWN_USE);

        if (currentTime > nextUse) {
            if (stack.get(ModDataComponentsType.CHARGE) < MAX_CHARGE) {
                stack.set(ModDataComponentsType.CHARGE, stack.get(ModDataComponentsType.CHARGE) + 1);
                World world = attacker.getWorld();
                ((ServerWorld) world).spawnParticles(
                        ParticleTypes.SCULK_SOUL, target.getX(), target.getY(), target.getZ(),
                        5, 0, 0.1, 0, 0.5
                );
            }

            nextUse = stack.set(ModDataComponentsType.COOLDOWN_USE, currentTime + STORE_SOULS_COOLDOWN);
        }

        super.postDamageEntity(stack, target, attacker);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.literal("" + stack.get(ModDataComponentsType.CHARGE)));
        super.appendTooltip(stack, context, tooltip, type);
    }

    private void sonicBoomAttack(World world, PlayerEntity player, LivingEntity target) {
        if (world.isClient) return;

        DamageSource source = world.getDamageSources().sonicBoom(player);

        Vec3d dir = player.getRotationVector();

        if (target != null) {
            target.damage(source, 10.0F);
            target.addVelocity(dir.x * 1.5, 0.5, dir.z * 1.5);
        }

        for (int i = 0; i < 20; i++) {
            Vec3d pos = player.getEyePos().add(dir.multiply(i * 0.5));
            ((ServerWorld) world).spawnParticles(
                    ParticleTypes.SONIC_BOOM, pos.x, pos.y, pos.z,
                    1, 0, 0 ,0 ,0
            );
        }

        world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_WARDEN_SONIC_BOOM,
                SoundCategory.PLAYERS, 3.0F, 1.0F);

        player.getMainHandStack().set(ModDataComponentsType.CHARGE, 0);
    }
}
