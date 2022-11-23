package net.jdtibochab.revengemod.event;

import net.jdtibochab.revengemod.RevengeMod;
import net.jdtibochab.revengemod.entity.ModEntityTypes;
import net.jdtibochab.revengemod.entity.client.siege_creeper.SiegeCreeperEntity;
import net.jdtibochab.revengemod.entity.client.face_hugger.FaceHuggerEntity;
import net.jdtibochab.revengemod.entity.client.healer_zombie.HealerZombieEntity;
import net.jdtibochab.revengemod.entity.client.nuclear_creeper.NuclearCreeperEntity;
import net.jdtibochab.revengemod.entity.client.sanic_creeper.SanicCreeperEntity;
import net.jdtibochab.revengemod.entity.client.sniper_skeleton.SniperSkeletonEntity;
import net.jdtibochab.revengemod.entity.client.tank_zombie.TankZombieEntity;
import net.jdtibochab.revengemod.entity.client.ultimate_creeper.UltimateCreeperEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


public class ModEvents {
    @Mod.EventBusSubscriber(modid = RevengeMod.MOD_ID)
    public static class ForgeEvents {
    }
    @Mod.EventBusSubscriber(modid = RevengeMod.MOD_ID, bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents {
        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
            event.put(ModEntityTypes.SANIC_CREEPER.get(), SanicCreeperEntity.setAttributes());
            event.put(ModEntityTypes.NUCLEAR_CREEPER.get(), NuclearCreeperEntity.setAttributes());
            event.put(ModEntityTypes.SIEGE_CREEPER.get(), SiegeCreeperEntity.setAttributes());
            event.put(ModEntityTypes.ULTIMATE_CREEPER.get(), UltimateCreeperEntity.setAttributes());
            event.put(ModEntityTypes.SNIPER_SKELETON.get(), SniperSkeletonEntity.setAttributes());
            event.put(ModEntityTypes.FACE_HUGGER.get(), FaceHuggerEntity.setAttributes());
            event.put(ModEntityTypes.HEALER_ZOMBIE.get(), HealerZombieEntity.setAttributes());
            event.put(ModEntityTypes.TANK_ZOMBIE.get(), TankZombieEntity.setAttributes());
        }
    }
}
