package net.jdtibochab.revengemod.entity.client.tank_zombie;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class TankZombieEntity extends Zombie {

    public TankZombieEntity(EntityType<? extends Zombie> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public static AttributeSupplier setAttributes() {
        return Zombie.createAttributes()
                .add(Attributes.MOVEMENT_SPEED,0.15D)
                .add(Attributes.MAX_HEALTH,1000.0D)
                .add(Attributes.ATTACK_DAMAGE, 6.0D)
                .build();
    }

    private final ServerBossEvent bossEvent = (ServerBossEvent)(new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.PURPLE, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(true);

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        if (this.hasCustomName()) {
            this.bossEvent.setName(this.getDisplayName());
        }
    }

    public void setCustomName(@Nullable Component pName) {
        super.setCustomName(pName);
        this.bossEvent.setName(this.getDisplayName());
    }

    @Override
    public void tick() {
        super.tick();
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
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

    @Override
    public void knockback(double pStrength, double pX, double pZ) {
        double tank_strength = 2;
        net.minecraftforge.event.entity.living.LivingKnockBackEvent event = net.minecraftforge.common.ForgeHooks.onLivingKnockBack(this, (float) pStrength, pX, pZ);
        if(event.isCanceled()) return;
        pStrength = event.getStrength();
        pX = event.getRatioX();
        pZ = event.getRatioZ();
        pStrength *= 1.0D - this.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE);
        pStrength /= tank_strength;
        if (!(pStrength <= 0.0D)) {
            this.hasImpulse = true;
            Vec3 vec3 = this.getDeltaMovement();
            Vec3 vec31 = (new Vec3(pX, 0.0D, pZ)).normalize().scale(pStrength);
            this.setDeltaMovement(vec3.x / 2.0D - vec31.x, this.onGround ? Math.min(0.4D, vec3.y / 2.0D + pStrength) : vec3.y, vec3.z / 2.0D - vec31.z);
        }
    }

//    @Override
//    protected void populateDefaultEquipmentSlots(RandomSource pRandom, DifficultyInstance pDifficulty) {
//        this.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(Items.SHIELD));
//        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.NETHERITE_SWORD));
//        this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(Items.DIAMOND_HELMET));
//        this.setItemSlot(EquipmentSlot.CHEST, new ItemStack(Items.DIAMOND_CHESTPLATE));
//        this.setItemSlot(EquipmentSlot.LEGS, new ItemStack(Items.DIAMOND_LEGGINGS));
//        this.setItemSlot(EquipmentSlot.FEET, new ItemStack(Items.DIAMOND_BOOTS));
//    }
//
//    @Override
//    protected void enchantSpawnedArmor(RandomSource pRandom, float pChanceMultiplier, EquipmentSlot pSlot) {
//        this.getMainHandItem().enchant(Enchantments.VANISHING_CURSE,1);
//        this.getOffhandItem().enchant(Enchantments.VANISHING_CURSE,1);
//        this.getItemBySlot(EquipmentSlot.HEAD).enchant(Enchantments.VANISHING_CURSE,1);
//        this.getItemBySlot(EquipmentSlot.CHEST).enchant(Enchantments.VANISHING_CURSE,1);
//        this.getItemBySlot(EquipmentSlot.LEGS).enchant(Enchantments.VANISHING_CURSE,1);
//        this.getItemBySlot(EquipmentSlot.FEET).enchant(Enchantments.VANISHING_CURSE,1);
//    }
}
