package net.jdtibochab.tutorialmod.entity;

import net.jdtibochab.tutorialmod.TutorialMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TutorialMod.MOD_ID);

    public static final RegistryObject<EntityType<SanicCreeperEntity>> SANIC_CREEPER =
            ENTITY_TYPES.register("sanic_creeper",
                    () -> EntityType.Builder.of(SanicCreeperEntity::new, MobCategory.MONSTER)
                            .build(new ResourceLocation(TutorialMod.MOD_ID, "sanic_creeper").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
