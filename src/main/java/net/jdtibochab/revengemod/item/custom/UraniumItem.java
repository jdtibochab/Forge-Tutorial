package net.jdtibochab.revengemod.item.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;

public class UraniumItem extends Item {
    private int cooldown = 0;
    private int maxCooldown = 200;
    public UraniumItem(Properties pProperties) {
        super(pProperties);
    }

    public int getCooldown() {
        return this.cooldown;
    }

    public void decrementCooldown() {
        --this.cooldown;
    }

    public void resetCooldown() {
        this.cooldown = maxCooldown;
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int pSlotId, boolean pIsSelected) {
        if (!level.isClientSide) {
            if (this.getCooldown() <= 0) {
                MobEffects.POISON.applyEffectTick((LivingEntity) entity, 1);
                this.resetCooldown();
            } else {
                this.decrementCooldown();
            }
        }
    }
}
