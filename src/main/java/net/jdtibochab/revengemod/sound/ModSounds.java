package net.jdtibochab.revengemod.sound;

import net.jdtibochab.revengemod.RevengeMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
        DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, RevengeMod.MOD_ID);

    public static final RegistryObject<SoundEvent> NUCLEAR_FUSE =
            registerSoundEvent("nuclear_fuse");
    public static final RegistryObject<SoundEvent> NUCLEAR_EXPLOSION =
        registerSoundEvent("nuclear_explosion");
    public static final RegistryObject<SoundEvent> ULTIMATE_AMBIENT =
        registerSoundEvent("ultimate_ambient");
    public static final RegistryObject<SoundEvent> ULTIMATE_FUSE =
        registerSoundEvent("ultimate_fuse");
    public static final RegistryObject<SoundEvent> ULTIMATE_MUSIC =
        registerSoundEvent("ultimate_music");
    public static final RegistryObject<SoundEvent> TANK_AMBIENT =
        registerSoundEvent("tank_ambient");
    public static final RegistryObject<SoundEvent> TANK_ATTACK =
        registerSoundEvent("tank_attack");
    public static final RegistryObject<SoundEvent> TANK_HURT =
        registerSoundEvent("tank_hurt");



    public static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(RevengeMod.MOD_ID,name)));
    }



    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }

}
