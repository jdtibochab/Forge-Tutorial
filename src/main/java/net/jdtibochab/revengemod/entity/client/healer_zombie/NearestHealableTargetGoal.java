package net.jdtibochab.revengemod.entity.client.healer_zombie;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;

public class NearestHealableTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {

    public NearestHealableTargetGoal(Mob pMob, Class pTargetType, boolean pMustSee) {
        super(pMob, pTargetType, pMustSee);
    }
    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    @Override
    public boolean canUse() {
        return super.canUse();
    }

    @Override
    public boolean canContinueToUse() {
        super.canContinueToUse();
        if (this.target.getHealth() / this.target.getMaxHealth() > 0.99f) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    protected void findTarget() {
        this.target = this.mob.level.getNearestEntity(
                this.mob.level.getEntitiesOfClass(
                        this.targetType, this.getTargetSearchArea(this.getFollowDistance()), (p_148152_) -> true),
                this.targetConditions,
                this.mob,
                this.mob.getX(),
                this.mob.getEyeY(),
                this.mob.getZ()
        );
        if (this.target != null && this.target.getHealth() / this.target.getMaxHealth() > 0.99f) {
                this.target = null;
        }
    }
    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void start() {
        super.start();
    }
}
