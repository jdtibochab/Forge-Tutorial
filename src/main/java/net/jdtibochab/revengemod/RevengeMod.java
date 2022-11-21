package net.jdtibochab.revengemod;

import com.mojang.logging.LogUtils;
import net.jdtibochab.revengemod.entity.ModEntityTypes;
import net.jdtibochab.revengemod.entity.client.face_hugger.FaceHuggerRenderer;
import net.jdtibochab.revengemod.entity.client.nuclear_creeper.NuclearCreeperRenderer;
import net.jdtibochab.revengemod.entity.client.sanic_creeper.SanicCreeperRenderer;
import net.jdtibochab.revengemod.entity.client.sniper_skeleton.SniperSkeletonRenderer;
import net.jdtibochab.revengemod.entity.client.ultimate_creeper.UltimateCreeperRenderer;
import net.jdtibochab.revengemod.item.ModItems;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;


// The value here should match an entry in the META-INF/mods.toml file

@Mod(RevengeMod.MOD_ID)
public class RevengeMod
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "revengemod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    public RevengeMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.register(modEventBus);
        ModEntityTypes.register(modEventBus);
        // Register ourselves for server and other game events we are interested in
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {

            SpawnPlacements.register(ModEntityTypes.SANIC_CREEPER.get(),
                    SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Monster::checkMonsterSpawnRules);

            SpawnPlacements.register(ModEntityTypes.NUCLEAR_CREEPER.get(),
                    SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Monster::checkMonsterSpawnRules);

            SpawnPlacements.register(ModEntityTypes.SNIPER_SKELETON.get(),
                    SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Monster::checkMonsterSpawnRules);

            SpawnPlacements.register(ModEntityTypes.FACE_HUGGER.get(),
                    SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Monster::checkMonsterSpawnRules);

            SpawnPlacements.register(ModEntityTypes.ULTIMATE_CREEPER.get(),
                    SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Monster::checkMonsterSpawnRules);
        });
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents{
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntityTypes.SANIC_CREEPER.get(), SanicCreeperRenderer::new);
            EntityRenderers.register(ModEntityTypes.NUCLEAR_CREEPER.get(), NuclearCreeperRenderer::new);
            EntityRenderers.register(ModEntityTypes.ULTIMATE_CREEPER.get(), UltimateCreeperRenderer::new);
            EntityRenderers.register(ModEntityTypes.SNIPER_SKELETON.get(), SniperSkeletonRenderer::new);
            EntityRenderers.register(ModEntityTypes.FACE_HUGGER.get(), FaceHuggerRenderer::new);
        }
    }
}