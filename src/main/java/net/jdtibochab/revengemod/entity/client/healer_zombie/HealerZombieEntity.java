package net.jdtibochab.revengemod.entity.client.healer_zombie;


import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;

import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class HealerZombieEntity extends Zombie implements RangedAttackMob {
    public HealerZombieEntity(EntityType<? extends Zombie> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

    }
    public static AttributeSupplier setAttributes() {
        return Zombie.createAttributes()
                .add(Attributes.FOLLOW_RANGE, 10.0D)
                .build();
    }
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Monster.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.addBehaviourGoals();
    }

    @Override
    protected void addBehaviourGoals() {
        this.goalSelector.addGoal(2, new RangedAttackGoal(this, 1.0D, 60, 10.0F));
        this.goalSelector.addGoal(6, new MoveThroughVillageGoal(this, 1.0D, true, 4, this::canBreakDoors));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(ZombifiedPiglin.class));
        this.targetSelector.addGoal(2, new NearestHealableTargetGoal<>(this, Monster.class, true,false));
        this.targetSelector.addGoal(2, new NearestHealableTargetGoal<>(this, AbstractVillager.class, true,false));
        this.targetSelector.addGoal(2, new NearestHealableTargetGoal<>(this, IronGolem.class, true,false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(4, new NearestHealableTargetGoal<>(this, Monster.class, true,true));
    }

    @Override
    public void aiStep() {
        super.aiStep();
        Potion potion = Potions.REGENERATION;
        this.setItemSlot(EquipmentSlot.MAINHAND, PotionUtils.setPotion(new ItemStack(Items.POTION), potion));
    }


    @Override
    public void performRangedAttack(LivingEntity pTarget, float pVelocity) {
        if (pTarget instanceof Monster && pTarget.getHealth()/pTarget.getMaxHealth() > 0.99f) {
            return;
        }
        // Track target
        Vec3 vec3 = pTarget.getDeltaMovement();
        double d0 = pTarget.getX() + vec3.x - this.getX();
        double d1 = pTarget.getEyeY() - (double)1.1F - this.getY();
        double d2 = pTarget.getZ() + vec3.z - this.getZ();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);

        // Throw potion
        Potion potion;
        if (
                pTarget instanceof Zombie ||
                        pTarget instanceof Skeleton ||
                        pTarget instanceof Player ||
                        pTarget instanceof AbstractVillager ||
                        pTarget instanceof IronGolem) {
            potion = Potions.STRONG_HARMING;
        } else {
            potion = Potions.STRONG_HEALING;
        }

        ThrownPotion thrownpotion = new ThrownPotion(this.level, this);
        thrownpotion.setItem(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), potion));
        thrownpotion.setXRot(thrownpotion.getXRot() - -20.0F);
        thrownpotion.shoot(d0, d1 + d3 * 0.2D, d2, 0.75F, 8.0F);
        this.level.addFreshEntity(thrownpotion);
    }
}