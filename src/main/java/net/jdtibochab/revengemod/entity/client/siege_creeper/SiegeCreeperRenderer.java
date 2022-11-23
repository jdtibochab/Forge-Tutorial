package net.jdtibochab.revengemod.entity.client.siege_creeper;

import net.jdtibochab.revengemod.RevengeMod;
import net.minecraft.client.renderer.entity.CreeperRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SiegeCreeperRenderer extends CreeperRenderer{
    public SiegeCreeperRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(Creeper pEntity) {
        return new ResourceLocation(RevengeMod.MOD_ID,"textures/entity/nuclear_creeper.png");
    }
}
