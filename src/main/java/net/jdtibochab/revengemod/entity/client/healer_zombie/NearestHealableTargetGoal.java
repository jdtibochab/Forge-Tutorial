package net.jdtibochab.revengemod.entity.client.healer_zombie;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;

public class NearestHealableTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
    private boolean only_follow;
    public NearestHealableTargetGoal(Mob pMob, Class pTargetType, boolean pMustSee, boolean only_follow) {
        super(pMob, pTargetType, pMustSee);
        this.only_follow = only_follow;
    }

    @Override
    public boolean canContinueToUse() {
        super.canContinueToUse();
        if (!only_follow && this.target.getHealth() / this.target.getMaxHealth() > 0.99f
                || only_follow && this.target instanceof HealerZombieEntity) {
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
        if (this.target != null){
            if (!only_follow && this.target.getHealth() / this.target.getMaxHealth() > 0.99f
                    || only_follow && this.target instanceof HealerZombieEntity){
                this.target = null;
            }
        }
    }
}
