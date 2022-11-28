package net.jdtibochab.revengemod.item.custom;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;


public class AtomicArmorItem extends HazmatArmorItem {
    private int maxCooldown = 20;
    private int cooldown = maxCooldown;

    public AtomicArmorItem(ArmorMaterial pMaterial, EquipmentSlot pSlot, Properties pProperties) {
        super(pMaterial, pSlot, pProperties);
    }
    private void resetCooldown(){
      this.cooldown = maxCooldown;
    };

    private int getCooldown(){
      return this.cooldown;
    };

    private int decreaseCooldown(){
        return --this.cooldown;
    }
    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if (!level.isClientSide) {
            if (getCooldown() <= 0) {
                player.heal(0.1f);
                if (stack.getDamageValue() > 0){
                    stack.setDamageValue(stack.getDamageValue() - 1);
                }
                resetCooldown();
            } else {
                decreaseCooldown();
            }
        }
    }
}
