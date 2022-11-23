package net.jdtibochab.revengemod.item;

import net.jdtibochab.revengemod.RevengeMod;
import net.jdtibochab.revengemod.entity.ModEntityTypes;
import net.jdtibochab.revengemod.item.custom.UraniumItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    // List of items
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, RevengeMod.MOD_ID);

    public static final RegistryObject<Item> SANIC_CREEPER_SPAWN_EGG = ITEMS.register("sanic_creeper_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.SANIC_CREEPER, 0x22b341,0x19732e,
                    new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> NUCLEAR_CREEPER_SPAWN_EGG = ITEMS.register("nuclear_creeper_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.NUCLEAR_CREEPER, 0x22b341,0x19732e,
                    new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> SIEGE_CREEPER_SPAWN_EGG = ITEMS.register("siege_creeper_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.SIEGE_CREEPER, 0x22b341,0x19732e,
                    new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> SNIPER_SKELETON_SPAWN_EGG = ITEMS.register("sniper_skeleton_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.SNIPER_SKELETON, 0x22b341,0x19732e,
                    new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> FACE_HUGGER_SPAWN_EGG = ITEMS.register("face_hugger_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.FACE_HUGGER, 0x22b341,0x19732e,
                    new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> ULTIMATE_CREEPER_SPAWN_EGG = ITEMS.register("ultimate_creeper_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.ULTIMATE_CREEPER, 0x22b341,0x19732e,
                    new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> HEALER_ZOMBIE_SPAWN_EGG = ITEMS.register("healer_zombie_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.HEALER_ZOMBIE, 0x22b341,0x19732e,
                    new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> TANK_ZOMBIE_SPAWN_EGG = ITEMS.register("tank_zombie_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.TANK_ZOMBIE, 0x22b341,0x19732e,
                    new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> URANIUM = ITEMS.register("uranium",
            () -> new UraniumItem(new Item.Properties()
                    .tab(CreativeModeTab.TAB_MATERIALS)
                    .rarity(Rarity.RARE)
                    .stacksTo(1)));
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
