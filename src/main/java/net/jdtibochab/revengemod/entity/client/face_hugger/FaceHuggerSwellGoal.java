package net.jdtibochab.revengemod.entity.client.face_hugger;

import java.util.EnumSet;
import javax.annotation.Nullable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

public class FaceHuggerSwellGoal extends Goal {
    private final FaceHuggerEntity facehugger;
    @Nullable
    private LivingEntity target;

    public FaceHuggerSwellGoal(FaceHuggerEntity facehugger) {
        this.facehugger = facehugger;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean canUse() {
        LivingEntity livingentity = this.facehugger.getTarget();
        return this.facehugger.getSwellDir() > 0 || livingentity != null && this.facehugger.distanceToSqr(livingentity) < 9.0D;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void start() {
        this.facehugger.getNavigation().stop();
        this.target = this.facehugger.getTarget();
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void stop() {
        this.target = null;
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        if (this.target == null) {
            this.facehugger.setSwellDir(-1);
        } else if (this.facehugger.distanceToSqr(this.target) > 49.0D) {
            this.facehugger.setSwellDir(-1);
        } else if (!this.facehugger.getSensing().hasLineOfSight(this.target)) {
            this.facehugger.setSwellDir(-1);
        } else {
            this.facehugger.setSwellDir(1);
        }
    }
}