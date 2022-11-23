package net.jdtibochab.revengemod.entity.client.tank_zombie;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jdtibochab.revengemod.RevengeMod;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;

public class TankZombieRenderer extends ZombieRenderer {
    public TankZombieRenderer(EntityRendererProvider.Context context) {
        super(context);
    }
    @Override
    public ResourceLocation getTextureLocation(Zombie pEntity) {
        return new ResourceLocation(RevengeMod.MOD_ID,"textures/entity/tank_zombie.png");
    }

    @Override
    protected void scale(Zombie pLivingEntity, PoseStack pMatrixStack, float pPartialTickTime) {
        float f = 0.9375F;
        f *= 2.0F;
        this.shadowRadius = 0.25F;
        pMatrixStack.scale(f, f, f);
    }
}
