package net.jdtibochab.revengemod.entity.client.sniper_skeleton;

import net.jdtibochab.revengemod.RevengeMod;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SkeletonRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.AbstractSkeleton;

public class SniperSkeletonRenderer extends SkeletonRenderer {
    public SniperSkeletonRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(AbstractSkeleton pEntity) {
        return new ResourceLocation(RevengeMod.MOD_ID,"textures/entity/sniper_skeleton.png");
    }
}
