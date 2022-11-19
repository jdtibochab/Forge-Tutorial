package net.jdtibochab.tutorialmod.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.jdtibochab.tutorialmod.TutorialMod;
import net.jdtibochab.tutorialmod.entity.ModEntityTypes;
import net.jdtibochab.tutorialmod.entity.SanicCreeperEntity;
import net.jdtibochab.tutorialmod.item.ModItems;
import net.jdtibochab.tutorialmod.villager.ModVillagers;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;


public class ModEvents {
    @Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID)
    public static class ForgeEvents {
        @SubscribeEvent
        public static void addCustomTrades(VillagerTradesEvent event){
            if(event.getType() == ModVillagers.JUMPY_MASTER.get()) {
                Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
                ItemStack stack = new ItemStack(ModItems.BLUEBERRY.get(), 15);
                int villagerLevel = 1;

                trades.get(villagerLevel).add((trader,rand) -> new MerchantOffer(
                        new ItemStack(Items.EMERALD, 5),
                        stack, 10, 8, 0.02F
                ));
            }
        }
    }
    @Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents {
        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
            event.put(ModEntityTypes.SANIC_CREEPER.get(), SanicCreeperEntity.setAttributes());
        }
    }
}
