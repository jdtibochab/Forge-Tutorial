package net.jdtibochab.revengemod.item.custom;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class HazmatArmorItem extends ArmorItem {
    private int hazmatProtected;

    public HazmatArmorItem(ArmorMaterial pMaterial, EquipmentSlot pSlot, Properties pProperties) {
        super(pMaterial, pSlot, pProperties);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if (!level.isClientSide) {
            hazmatProtected = 0;
            Iterable<ItemStack> armorSlots = player.getArmorSlots();
            for (ItemStack armorItemStack : armorSlots) {
                if (armorItemStack.getItem() instanceof HazmatArmorItem) {
                    hazmatProtected++;
                }
            }

            if (hazmatProtected >= 4) {
                List<MobEffect> effects = new ArrayList<>();
                effects.add(MobEffects.FIRE_RESISTANCE);
                LivingEntity livingEntity = (LivingEntity) player;
                for (MobEffect e : effects) {
                    if (!livingEntity.hasEffect(e)) {
                        MobEffectInstance eInstance = new MobEffectInstance(e, (int) 100);
                        livingEntity.addEffect(eInstance);
                    }
                }
            }
        }
    }
}
