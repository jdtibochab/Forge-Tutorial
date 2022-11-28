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
    private int uraniumProtected;
    public UraniumItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int pSlotId, boolean pIsSelected) {
        if (!level.isClientSide) {
            uraniumProtected = 0;
            Iterable<ItemStack> armorSlots = entity.getArmorSlots();
            for (ItemStack armorItemStack : armorSlots){
                if (armorItemStack.getItem() instanceof HazmatArmorItem){
                    uraniumProtected++;
                }
            }
            if (uraniumProtected < 4){
                List<MobEffect> effects = new ArrayList<>();
                effects.add(MobEffects.POISON);
                effects.add(MobEffects.GLOWING);
                effects.add(MobEffects.NIGHT_VISION);
                effects.add(MobEffects.CONFUSION);
                LivingEntity livingEntity = (LivingEntity) entity;
                for (MobEffect e : effects){
                    if (!livingEntity.hasEffect(e)){
                        MobEffectInstance eInstance = new MobEffectInstance(e, (int) 100 / uraniumProtected);
                        livingEntity.addEffect(eInstance);
                    }
                }
            }
        }
    }
}
