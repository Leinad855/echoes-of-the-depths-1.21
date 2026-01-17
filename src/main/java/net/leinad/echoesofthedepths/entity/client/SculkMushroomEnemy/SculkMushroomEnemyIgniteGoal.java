package net.leinad.echoesofthedepths.entity.client.SculkMushroomEnemy;

import net.leinad.echoesofthedepths.entity.custom.SculkMushroomEnemyEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.CreeperEntity;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class SculkMushroomEnemyIgniteGoal extends Goal {
    private final SculkMushroomEnemyEntity sculkMushroomEnemy;
    @Nullable
    private LivingEntity target;

    public SculkMushroomEnemyIgniteGoal(SculkMushroomEnemyEntity sculkMushroomEnemy) {
        this.sculkMushroomEnemy = sculkMushroomEnemy;
        this.setControls(EnumSet.of(Goal.Control.MOVE));
    }

    @Override
    public boolean canStart() {
        LivingEntity livingEntity = this.sculkMushroomEnemy.getTarget();
        return this.sculkMushroomEnemy.getFuseSpeed() > 0 || livingEntity != null && this.sculkMushroomEnemy.squaredDistanceTo(livingEntity) < 9.0;
    }

    @Override
    public void start() {
        this.sculkMushroomEnemy.getNavigation().stop();
        this.target = this.sculkMushroomEnemy.getTarget();
    }

    @Override
    public void stop() {
        this.target = null;
    }

    @Override
    public boolean shouldRunEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        if (this.target == null) {
            this.sculkMushroomEnemy.setFuseSpeed(-1);
        } else if (this.sculkMushroomEnemy.squaredDistanceTo(this.target) > 49.0) {
            this.sculkMushroomEnemy.setFuseSpeed(-1);
        } else if (!this.sculkMushroomEnemy.getVisibilityCache().canSee(this.target)) {
            this.sculkMushroomEnemy.setFuseSpeed(-1);
        } else {
            this.sculkMushroomEnemy.setFuseSpeed(1);
        }
    }
}
