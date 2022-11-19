package net.jdtibochab.tutorialmod.entity.client;

import net.jdtibochab.tutorialmod.TutorialMod;
import net.minecraft.client.renderer.entity.CreeperRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Creeper;

public class SanicCreeperRenderer extends CreeperRenderer {
    public SanicCreeperRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(Creeper pEntity) {
        return new ResourceLocation(TutorialMod.MOD_ID,"textures/entity/sanic_creeper.png");
    }
}
