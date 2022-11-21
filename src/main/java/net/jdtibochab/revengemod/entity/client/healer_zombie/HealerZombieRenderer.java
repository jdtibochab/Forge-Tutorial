package net.jdtibochab.revengemod.entity.client.healer_zombie;

import net.jdtibochab.revengemod.RevengeMod;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;

public class HealerZombieRenderer extends ZombieRenderer {
    public HealerZombieRenderer(EntityRendererProvider.Context p_174456_) {
        super(p_174456_);
    }

    @Override
    public ResourceLocation getTextureLocation(Zombie pEntity) {
        return new ResourceLocation(RevengeMod.MOD_ID,"textures/entity/healer_zombie.png");
    }
}
