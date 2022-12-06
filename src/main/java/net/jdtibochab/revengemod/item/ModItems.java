package net.jdtibochab.revengemod.item;

import net.jdtibochab.revengemod.RevengeMod;
import net.jdtibochab.revengemod.entity.ModEntityTypes;
import net.jdtibochab.revengemod.item.custom.AtomicArmorItem;
import net.jdtibochab.revengemod.item.custom.HazmatArmorItem;
import net.jdtibochab.revengemod.item.custom.UraniumItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
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

    public static final RegistryObject<Item> RAW_LEAD = ITEMS.register("raw_lead",
            () -> new Item(new Item.Properties()
                    .tab(CreativeModeTab.TAB_MATERIALS)
                    .fireResistant()
                    .rarity(Rarity.RARE)));

    public static final RegistryObject<Item> LEAD_INGOT = ITEMS.register("lead_ingot",
            () -> new Item(new Item.Properties()
                    .tab(CreativeModeTab.TAB_MATERIALS)
                    .fireResistant()
                    .rarity(Rarity.RARE)));

    // ATOMIC
    public static final RegistryObject<Item> ATOMIC_HELMET = ITEMS.register("atomic_helmet",
            () -> new AtomicArmorItem(ModArmorMaterials.ATOMIC, EquipmentSlot.HEAD,
                    new Item.Properties().tab(CreativeModeTab.TAB_COMBAT).fireResistant()));

    public static final RegistryObject<Item> ATOMIC_CHESTPLATE = ITEMS.register("atomic_chestplate",
            () -> new AtomicArmorItem(ModArmorMaterials.ATOMIC, EquipmentSlot.CHEST,
                    new Item.Properties().tab(CreativeModeTab.TAB_COMBAT).fireResistant()));

    public static final RegistryObject<Item> ATOMIC_LEGGINGS = ITEMS.register("atomic_leggings",
            () -> new AtomicArmorItem(ModArmorMaterials.ATOMIC, EquipmentSlot.LEGS,
                    new Item.Properties().tab(CreativeModeTab.TAB_COMBAT).fireResistant()));

    public static final RegistryObject<Item> ATOMIC_BOOTS = ITEMS.register("atomic_boots",
            () -> new AtomicArmorItem(ModArmorMaterials.ATOMIC, EquipmentSlot.FEET,
                    new Item.Properties().tab(CreativeModeTab.TAB_COMBAT).fireResistant()));

    // HAZMAT
    public static final RegistryObject<Item> HAZMAT_HELMET = ITEMS.register("hazmat_helmet",
            () -> new HazmatArmorItem(ModArmorMaterials.HAZMAT, EquipmentSlot.HEAD,
                    new Item.Properties().tab(CreativeModeTab.TAB_COMBAT).fireResistant()));

    public static final RegistryObject<Item> HAZMAT_CHESTPLATE = ITEMS.register("hazmat_chestplate",
            () -> new HazmatArmorItem(ModArmorMaterials.HAZMAT, EquipmentSlot.CHEST,
                    new Item.Properties().tab(CreativeModeTab.TAB_COMBAT).fireResistant()));

    public static final RegistryObject<Item> HAZMAT_LEGGINGS = ITEMS.register("hazmat_leggings",
            () -> new HazmatArmorItem(ModArmorMaterials.HAZMAT, EquipmentSlot.LEGS,
                    new Item.Properties().tab(CreativeModeTab.TAB_COMBAT).fireResistant()));

    public static final RegistryObject<Item> HAZMAT_BOOTS = ITEMS.register("hazmat_boots",
            () -> new HazmatArmorItem(ModArmorMaterials.HAZMAT, EquipmentSlot.FEET,
                    new Item.Properties().tab(CreativeModeTab.TAB_COMBAT).fireResistant()));
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
