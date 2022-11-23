package net.jdtibochab.revengemod.entity;

import net.jdtibochab.revengemod.RevengeMod;
import net.jdtibochab.revengemod.entity.client.siege_creeper.SiegeCreeperEntity;
import net.jdtibochab.revengemod.entity.client.face_hugger.FaceHuggerEntity;
import net.jdtibochab.revengemod.entity.client.healer_zombie.HealerZombieEntity;
import net.jdtibochab.revengemod.entity.client.nuclear_creeper.NuclearCreeperEntity;
import net.jdtibochab.revengemod.entity.client.sanic_creeper.SanicCreeperEntity;
import net.jdtibochab.revengemod.entity.client.sniper_skeleton.SniperSkeletonEntity;
import net.jdtibochab.revengemod.entity.client.super_tnt.PrimedSuperTntEntity;
import net.jdtibochab.revengemod.entity.client.tank_zombie.TankZombieEntity;
import net.jdtibochab.revengemod.entity.client.ultimate_creeper.UltimateCreeperEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, RevengeMod.MOD_ID);

    public static final RegistryObject<EntityType<SanicCreeperEntity>> SANIC_CREEPER =
            ENTITY_TYPES.register("sanic_creeper",
                    () -> EntityType.Builder.of(SanicCreeperEntity::new, MobCategory.MONSTER)
                            .sized(0.6f,1.7f)
                            .build(new ResourceLocation(RevengeMod.MOD_ID, "sanic_creeper").toString()));

    public static final RegistryObject<EntityType<NuclearCreeperEntity>> NUCLEAR_CREEPER =
                ENTITY_TYPES.register("nuclear_creeper",
                        () -> EntityType.Builder.of(NuclearCreeperEntity::new, MobCategory.MONSTER)
                                .sized(0.6f,1.7f)
                                .build(new ResourceLocation(RevengeMod.MOD_ID, "nuclear_creeper").toString()));

    public static final RegistryObject<EntityType<SiegeCreeperEntity>> SIEGE_CREEPER =
            ENTITY_TYPES.register("siege_creeper",
                    () -> EntityType.Builder.of(SiegeCreeperEntity::new, MobCategory.MONSTER)
                            .sized(0.6f,1.7f)
                            .build(new ResourceLocation(RevengeMod.MOD_ID, "siege_creeper").toString()));

    public static final RegistryObject<EntityType<UltimateCreeperEntity>> ULTIMATE_CREEPER =
                ENTITY_TYPES.register("ultimate_creeper",
                        () -> EntityType.Builder.of(UltimateCreeperEntity::new, MobCategory.MONSTER)
                                .sized(0.6f*1.5f,1.7f*1.5f)
                                .build(new ResourceLocation(RevengeMod.MOD_ID, "ultimate_creeper").toString()));
    public static final RegistryObject<EntityType<SniperSkeletonEntity>> SNIPER_SKELETON =
                ENTITY_TYPES.register("sniper_skeleton",
                        () -> EntityType.Builder.of(SniperSkeletonEntity::new, MobCategory.MONSTER)
                                .sized(0.6f,1.99f)
                                .build(new ResourceLocation(RevengeMod.MOD_ID, "sniper_skeleton").toString()));

    public static final RegistryObject<EntityType<FaceHuggerEntity>> FACE_HUGGER =
                ENTITY_TYPES.register("face_hugger",
                        () -> EntityType.Builder.of(FaceHuggerEntity::new, MobCategory.MONSTER)
                                .sized(1.4f*0.5f,0.9f*0.5f)
                                .build(new ResourceLocation(RevengeMod.MOD_ID, "face_hugger").toString()));

    public static final RegistryObject<EntityType<HealerZombieEntity>> HEALER_ZOMBIE =
                ENTITY_TYPES.register("healer_zombie",
                        () -> EntityType.Builder.of(HealerZombieEntity::new, MobCategory.MONSTER)
                                .sized(0.6f,1.95f)
                                .build(new ResourceLocation(RevengeMod.MOD_ID, "healer_zombie").toString()));

    public static final RegistryObject<EntityType<TankZombieEntity>> TANK_ZOMBIE =
                ENTITY_TYPES.register("tank_zombie",
                        () -> EntityType.Builder.of(TankZombieEntity::new, MobCategory.MONSTER)
                                .sized(0.6f*2.0f,1.95f*2.0f)
                                .build(new ResourceLocation(RevengeMod.MOD_ID, "tank_zombie").toString()));

    public static final RegistryObject<EntityType<PrimedSuperTntEntity>> SUPER_TNT =
            ENTITY_TYPES.register("super_tnt",
                    () -> EntityType.Builder.<PrimedSuperTntEntity>of(PrimedSuperTntEntity::new, MobCategory.MISC)
                            .sized(0.98F, 0.98F)
                            .clientTrackingRange(10)
                            .updateInterval(10)
                            .build(new ResourceLocation(RevengeMod.MOD_ID, "super_tnt").toString()));
    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
