package net.jdtibochab.revengemod.entity.client.tank_zombie;

import net.jdtibochab.revengemod.sound.ModSounds;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;

import javax.annotation.Nullable;
import java.util.List;

public class TankZombieEntity extends Zombie {
    private static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(TankZombieEntity.class, EntityDataSerializers.BYTE);
    private int attackAnimationTick;

    private double shakeDistance = 5.0D;
    private double pullDistance = 3.0D;

    private static final UniformInt SHAKE_INTERVAL = rangeOfTicks(4,6);
    private static final UniformInt PULL_INTERVAL = rangeOfTicks(100,200);
    private static final UniformInt ALERT_INTERVAL = rangeOfTicks(80,120);
    private int shakeTick;
    private int alertTick;
    private int pullTick;


    public TankZombieEntity(EntityType<? extends Zombie> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.LAVA, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, 0.0F);
        this.xpReward = 30;
        this.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE,99999));
    }
    public static AttributeSupplier setAttributes() {
        return Zombie.createAttributes()
                .add(Attributes.MOVEMENT_SPEED,0.40D)
                .add(Attributes.MAX_HEALTH,750.0D)
                .add(Attributes.ATTACK_DAMAGE, 12.0D)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.addBehaviourGoals();
    }
    @Override
    protected void addBehaviourGoals() {
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(6, new MoveThroughVillageGoal(this, 1.0D, true, 4, this::canBreakDoors));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
    }


//    private final ServerBossEvent bossEvent = (ServerBossEvent)(new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.PURPLE, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(true);

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
//        if (this.hasCustomName()) {
//            this.bossEvent.setName(this.getDisplayName());
//        }
    }

    public void setCustomName(@Nullable Component pName) {
        super.setCustomName(pName);
//        this.bossEvent.setName(this.getDisplayName());
    }
    @Override
    public void tick() {
        super.tick();
        if (!this.level.isClientSide) {
            this.setClimbing(this.horizontalCollision);
        }

        if (this.isClimbing()){
            if (shakeTick <= 0){
                shakeSurroundingEntities(0.15D);
                shakeTick = SHAKE_INTERVAL.sample(this.random);
            } else{
                --shakeTick;
            }
        }

        if (this.alertTick <=0){
            alertOthers();
            alertTick = ALERT_INTERVAL.sample(this.random);
        } else {
            --alertTick;
        }

        if (pullTick <= 0){
            pullTarget(3.0D);
            pullTick = PULL_INTERVAL.sample(this.random);
        } else {
            --pullTick;
        }

        this.floatTank();
//        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
    }

    protected AABB getTargetSearchArea() {
        double pTargetDistance = this.getAttributeValue(Attributes.FOLLOW_RANGE);
        return this.getBoundingBox().inflate(pTargetDistance, pTargetDistance, pTargetDistance);
    }

    public void shakeSurroundingEntities(double strength){
        List<LivingEntity> entities = this.level.getEntitiesOfClass(LivingEntity.class,this.getTargetSearchArea());
        for (LivingEntity entity : entities){
            if (entity == this || entity.getY() <= this.getY() + 1.0D){
                continue;
            }
            double d = Math.sqrt(this.distanceToSqr(entity.getX(), this.getY(), entity.getZ())); // 2D distance
            if (d <= shakeDistance){
                double f;
                f  = Math.random()/Math.nextDown(1.0);
                double x = (-strength)*(1.0 - f) + (strength)*f;
                f  = Math.random()/Math.nextDown(1.0);
                double z = (-strength)*(1.0 - f) + (strength)*f;
                Vec3 dV = new Vec3(x,0.0D,z);
                entity.setDeltaMovement(entity.getDeltaMovement().add(dV));
            }
        }
        this.playSound(SoundEvents.WARDEN_STEP, 2.0F, 0.5F);
    }

    public static UniformInt rangeOfTicks(int pMinInclusive,int pMaxInclusive){
        return UniformInt.of(pMinInclusive, pMaxInclusive);
    }

    public void alertOthers(){
        double d0 = this.getAttributeValue(Attributes.FOLLOW_RANGE);
        AABB aabb = AABB.unitCubeFromLowerCorner(this.position()).inflate(d0, 10.0D, d0);
        List<Monster> entities = this.level.getEntitiesOfClass(Monster.class,aabb);
        for (Monster entity : entities){
            if (entity == this || entity.getTarget() != null){
                continue;
            }
            entity.setTarget(this.getTarget());
        }
    }

    private void pullTarget(double strength) {
        LivingEntity target = this.getTarget();
        if (target == null
                || Math.sqrt(this.distanceToSqr(target.getX(),this.getY(),target.getZ())) < pullDistance){
            return;
        }
        Vec3 dV = new Vec3(this.getX() - target.getX(),0.0D,this.getZ() - target.getZ())
                .normalize()
                .multiply(strength,0.0D,strength);
        target.hurtMarked = true;
        target.setDeltaMovement(target.getDeltaMovement().add(dV));
        this.playSound(ModSounds.TANK_ATTACK.get(), 1.0F, 5.0F);
    }

    @Override
    protected boolean isSunSensitive() {
        return false;
    }

    @Override
    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
//        this.bossEvent.addPlayer(player);
    }
    @Override
    public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
//        this.bossEvent.removePlayer(player);
    }


    @Override
    public void knockback(double pStrength, double pX, double pZ) {
        return;
    }
    // Attack animation
    @Override
    public boolean doHurtTarget(Entity pEntity) {
        this.attackAnimationTick = 10;
        this.level.broadcastEntityEvent(this, (byte)4);
        float f = this.getAttackDamage();
        float f1 = (int)f > 0 ? f / 2.0F + (float)this.random.nextInt((int)f) : f;
        boolean flag = pEntity.hurt(DamageSource.mobAttack(this), f1);
        if (flag) {
            double d2;
            if (pEntity instanceof LivingEntity) {
                LivingEntity livingentity = (LivingEntity)pEntity;
                d2 = livingentity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE);
                livingentity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,60));
            } else {
                d2 = 0.0D;
            }
            double d0 = d2;
            double d1 = Math.max(0.0D, 2.0D - d0);
            pEntity.setDeltaMovement(pEntity.getDeltaMovement().add(0.0D, (double)0.4F * d1, 0.0D));
            this.doEnchantDamageEffects(this, pEntity);
        }
        this.playSound(ModSounds.TANK_ATTACK.get(), 1.0F, 1.0F);
        return flag;
    }


    private float getAttackDamage() {
        return (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
    }

    public int getAttackAnimationTick() {
        return this.attackAnimationTick;
    }

    // Lava
    @Override
    public void aiStep() {
        super.aiStep();
        if (this.attackAnimationTick > 0) {
            --this.attackAnimationTick;
        }
    }

    @Override
    public boolean isOnFire() {
        return false;
    }

    private void floatTank() {
        if (this.isInLava()) {
            CollisionContext collisioncontext = CollisionContext.of(this);
            if (collisioncontext.isAbove(LiquidBlock.STABLE_SHAPE, this.blockPosition(), true) && !this.level.getFluidState(this.blockPosition().above()).is(FluidTags.LAVA)) {
                this.onGround = true;
            } else {
                this.setDeltaMovement(this.getDeltaMovement().scale(0.5D).add(0.0D, 0.1D, 0.0D));
            }
        }

    }
    // Climbing
    @Override
    protected PathNavigation createNavigation(Level pLevel) {
        return new WallClimberNavigation(this, pLevel);
    }

    public boolean isClimbing() {
        return (this.entityData.get(DATA_FLAGS_ID) & 1) != 0;
    }

    public void setClimbing(boolean pClimbing) {
        byte b0 = this.entityData.get(DATA_FLAGS_ID);
        if (pClimbing) {
            b0 = (byte)(b0 | 1);
        } else {
            b0 = (byte)(b0 & -2);
        }

        this.entityData.set(DATA_FLAGS_ID, b0);
    }

    public boolean onClimbable() {
        return this.isClimbing();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_FLAGS_ID, (byte)0);
    }

    // Sound


    @Override
    protected float getSoundVolume() {
        return 5.0f;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return ModSounds.TANK_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.TANK_HURT.get();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.TANK_AMBIENT.get();
    }

    @Override
    public int getAmbientSoundInterval() {
        return 160;
    }

    @Override
    public void playAmbientSound() {
        SoundEvent soundevent = this.getAmbientSound();
        if (soundevent != null) {
            this.playSound(soundevent, this.getSoundVolume(), this.getVoicePitch());
        }
    }

    // Despawn
    @Override
    public void checkDespawn() {
    }
}
