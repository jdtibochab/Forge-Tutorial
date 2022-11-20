package net.jdtibochab.revengemod.entity.client.sniper_skeleton;

import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class SniperSkeletonEntity extends Skeleton {
    public SniperSkeletonEntity(EntityType<? extends Skeleton> entityType, Level level) {
        super(entityType, level);
    }
    public static AttributeSupplier setAttributes() {
        return Skeleton.createAttributes()
                .build();
    }
    @Override
    protected void enchantSpawnedWeapon(RandomSource pRandom, float pChanceMultiplier) {
        if (!this.getMainHandItem().isEmpty()) {
            this.getMainHandItem().enchant(Enchantments.POWER_ARROWS,25);
            this.getMainHandItem().enchant(Enchantments.VANISHING_CURSE,1);
            this.setItemSlot(EquipmentSlot.MAINHAND, this.getMainHandItem());
        }
    }
}
