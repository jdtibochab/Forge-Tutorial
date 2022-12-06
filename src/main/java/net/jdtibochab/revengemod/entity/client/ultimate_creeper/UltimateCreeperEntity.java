package net.jdtibochab.revengemod.entity.client.ultimate_creeper;

import net.jdtibochab.revengemod.entity.ModEntityTypes;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.gameevent.GameEvent;

import javax.annotation.Nullable;
import java.util.Collection;

public class UltimateCreeperEntity extends Monster implements PowerableMob {
    private static final EntityDataAccessor<Integer> DATA_SWELL_DIR = SynchedEntityData.defineId(UltimateCreeperEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> DATA_IS_POWERED = SynchedEntityData.defineId(UltimateCreeperEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_IS_IGNITED = SynchedEntityData.defineId(UltimateCreeperEntity.class, EntityDataSerializers.BOOLEAN);
    private int oldSwell;
    private int swell;
    private int maxSwell = 30;
    private int explosionRadius = 30;
    private int droppedSkulls;
    private int ultimateLevel;
    private final int avgLevel = 3;
    private final double stdDevLevel = 1.5D;

    private final ServerBossEvent bossEvent = (ServerBossEvent)(new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.PURPLE, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(true);


    public UltimateCreeperEntity(EntityType<? extends UltimateCreeperEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public UltimateCreeperEntity(Level pLevel, double pX, double pY, double pZ, int ultimateLevel, float health) {
        this(ModEntityTypes.ULTIMATE_CREEPER.get(), pLevel);
        this.setHealth(health);
        this.setPos(pX, pY, pZ);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
        this.ultimateLevel = ultimateLevel;
        setCustomName(Component.literal(this.getDisplayName().getString() + " Power " + (this.ultimateLevel)));
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new UltimateCreeperSwellGoal(this));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Ocelot.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Cat.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @org.jetbrains.annotations.Nullable SpawnGroupData pSpawnData, @org.jetbrains.annotations.Nullable CompoundTag pDataTag) {
        this.ultimateLevel = Math.max((int)Math.round(random.nextGaussian() * stdDevLevel) + avgLevel, 1);
        setCustomName(Component.literal(this.getDisplayName().getString() + " Power " + (this.ultimateLevel)));
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    public static AttributeSupplier setAttributes() {
        return Creeper.createAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.75D)
                .add(Attributes.MAX_HEALTH, 60.0D)
                .add(Attributes.FOLLOW_RANGE,100.0D)
                .build();
    }

    public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        boolean flag = super.causeFallDamage(pFallDistance, pMultiplier, pSource);
        this.swell += (int)(pFallDistance * 1.5F);
        if (this.swell > this.maxSwell - 5) {
            this.swell = this.maxSwell - 5;
        }
        return flag;
    }


    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_SWELL_DIR, -1);
        this.entityData.define(DATA_IS_POWERED, false);
        this.entityData.define(DATA_IS_IGNITED, false);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        if (this.entityData.get(DATA_IS_POWERED)) {
            pCompound.putBoolean("powered", true);
        }

        pCompound.putShort("Fuse", (short)this.maxSwell);
        pCompound.putByte("ExplosionRadius", (byte)this.explosionRadius);
        pCompound.putBoolean("ignited", this.isIgnited());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.entityData.set(DATA_IS_POWERED, pCompound.getBoolean("powered"));
        if (pCompound.contains("Fuse", 99)) {
            this.maxSwell = pCompound.getShort("Fuse");
        }

        if (pCompound.contains("ExplosionRadius", 99)) {
            this.explosionRadius = pCompound.getByte("ExplosionRadius");
        }

        if (pCompound.getBoolean("ignited")) {
            this.ignite();
        }
        if (this.hasCustomName()) {
            this.bossEvent.setName(this.getDisplayName());
        }
    }

    public void setCustomName(@Nullable Component pName) {
        super.setCustomName(pName);
        this.bossEvent.setName(this.getDisplayName());
    }

    private void spawnParticles(Level level){
        for(int i = 0; i < 360; i++){
            if(i % 10 == 0){
                level.addParticle(ParticleTypes.EXPLOSION,this.getX(), this.getY(), this.getZ(),
                        Math.cos(i) * 75.0D, 75.0D, Math.sin(i) + 75.0D);
            }
        }
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void tick() {
        if (this.isAlive()) {
            this.oldSwell = this.swell;
            if (this.isIgnited()) {
                this.setSwellDir(1);
            }

            int i = this.getSwellDir();
            if (i > 0 && this.swell == 0) {
                this.playSound(SoundEvents.CREEPER_PRIMED, 1.0F, 0.5F);
                this.gameEvent(GameEvent.PRIME_FUSE);
            }

            this.swell += i;
            if (this.swell < 0) {
                this.swell = 0;
            }

            if (this.swell >= this.maxSwell) {
                this.swell = this.maxSwell;
                this.spawnParticles(this.level);
                this.explodeCreeper();
            }
        }
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
        super.tick();
    }

    @Override
    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        this.bossEvent.addPlayer(player);
    }
    @Override
    public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        this.bossEvent.removePlayer(player);
    }

    public boolean isPowered() {
        return this.entityData.get(DATA_IS_POWERED);
    }

    /**
     * Params: (Float)Render tick. Returns the intensity of the creeper's flash when it is ignited.
     */
    public float getSwelling(float pPartialTicks) {
        return Mth.lerp(pPartialTicks, (float)this.oldSwell, (float)this.swell) / (float)(this.maxSwell - 2);
    }

    /**
     * Returns the current state of creeper, -1 is idle, 1 is 'in fuse'
     */
    public int getSwellDir() {
        return this.entityData.get(DATA_SWELL_DIR);
    }

    /**
     * Sets the state of creeper, -1 to idle and 1 to be 'in fuse'
     */
    public void setSwellDir(int pState) {
        this.entityData.set(DATA_SWELL_DIR, pState);
    }

    public void thunderHit(ServerLevel pLevel, LightningBolt pLightning) {
        super.thunderHit(pLevel, pLightning);
        this.entityData.set(DATA_IS_POWERED, true);
    }

    /**
     * Creates an explosion as determined by this creeper's power and explosion radius.
     */
    private void explodeCreeper() {
        if (!this.level.isClientSide) {
            Explosion.BlockInteraction explosion$blockinteraction = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this) ? Explosion.BlockInteraction.DESTROY : Explosion.BlockInteraction.NONE;
            float f = this.isPowered() ? 2.0F : 1.0F;
            this.level.explode(this, this.getX(), this.getY(), this.getZ(), (float)this.explosionRadius * f, explosion$blockinteraction);
            this.dead = true;
            this.discard();
            if (this.ultimateLevel > 1){
                this.level.addFreshEntity(new UltimateCreeperEntity(this.level,this.getX(),this.getY(),this.getZ(),this.ultimateLevel - 1, this.getHealth()));
            } else{
                this.spawnLingeringCloud();
            }
        }
    }

    private void spawnLingeringCloud() {
        Collection<MobEffectInstance> collection = this.getActiveEffects();
        if (!collection.isEmpty()) {
            AreaEffectCloud areaeffectcloud = new AreaEffectCloud(this.level, this.getX(), this.getY(), this.getZ());
            areaeffectcloud.setRadius(2.5F);
            areaeffectcloud.setRadiusOnUse(-0.5F);
            areaeffectcloud.setWaitTime(10);
            areaeffectcloud.setDuration(areaeffectcloud.getDuration() / 2);
            areaeffectcloud.setRadiusPerTick(-areaeffectcloud.getRadius() / (float)areaeffectcloud.getDuration());

            for(MobEffectInstance mobeffectinstance : collection) {
                areaeffectcloud.addEffect(new MobEffectInstance(mobeffectinstance));
            }

            this.level.addFreshEntity(areaeffectcloud);
        }
    }

    public boolean isIgnited() {
        return this.entityData.get(DATA_IS_IGNITED);
    }

    public void ignite() {
        this.entityData.set(DATA_IS_IGNITED, true);
    }

    /**
     * Returns true if an entity is able to drop its skull due to being blown up by this creeper.
     *
     * Does not test if this creeper is charged" the caller must do that. However, does test the doMobLoot gamerule.
     */
    public boolean canDropMobsSkull() {
        return this.isPowered() && this.droppedSkulls < 1;
    }

    public void increaseDroppedSkulls() {
        ++this.droppedSkulls;
    }
}
