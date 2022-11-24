package net.jdtibochab.revengemod.item.custom;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class UraniumItem extends Item {
    private int cooldown = 0;
    private int maxCooldown = 200;
    public UraniumItem(Properties pProperties) {
        super(pProperties);
    }

//    private List getMobEffectList() {
//        List<MobEffect> effects = new ArrayList<>();
//        effects.add(MobEffects.POISON);
//        effects.add(MobEffects.GLOWING);
//        effects.add(MobEffects.NIGHT_VISION);
//        return effects;
//    };

    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int pSlotId, boolean pIsSelected) {
        if (!level.isClientSide) {
            List<MobEffect> effects = new ArrayList<>();
            effects.add(MobEffects.POISON);
            effects.add(MobEffects.GLOWING);
            effects.add(MobEffects.NIGHT_VISION);
            effects.add(MobEffects.CONFUSION);
            LivingEntity livingEntity = (LivingEntity) entity;
            for (MobEffect e : effects){
                if (!livingEntity.hasEffect(e)){
                    MobEffectInstance nightVision = new MobEffectInstance(e, 100);
                    livingEntity.addEffect(nightVision);
                }
            }
        }
    }
}
