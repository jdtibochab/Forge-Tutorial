package net.jdtibochab.revengemod.world.feature;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import net.jdtibochab.revengemod.RevengeMod;
import net.jdtibochab.revengemod.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModConfiguredFeatures {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, RevengeMod.MOD_ID);

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_URANIUM_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.URANIUM_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_URANIUM_ORE.get().defaultBlockState())));

        public static final Supplier<List<OreConfiguration.TargetBlockState>> NETHER_LEAD_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.NETHER_ORE_REPLACEABLES, ModBlocks.NETHERRACK_LEAD_ORE.get().defaultBlockState())));


    public static final RegistryObject<ConfiguredFeature<?, ?>> URANIUM_ORE = CONFIGURED_FEATURES.register("uranium_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_URANIUM_ORES.get(),4,0.7F)));


    public static final RegistryObject<ConfiguredFeature<?, ?>> NETHER_LEAD_ORE = CONFIGURED_FEATURES.register("nether_lead_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(NETHER_LEAD_ORES.get(),4,0.7F)));


    public static void register(IEventBus eventBus) {
        CONFIGURED_FEATURES.register(eventBus);
    }
}
