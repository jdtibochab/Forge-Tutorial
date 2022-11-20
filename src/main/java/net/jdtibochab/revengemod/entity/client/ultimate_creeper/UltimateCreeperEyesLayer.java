package net.jdtibochab.revengemod.entity.client.ultimate_creeper;

import net.jdtibochab.revengemod.RevengeMod;
import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class UltimateCreeperEyesLayer<T extends LivingEntity> extends EyesLayer<T, CreeperModel<T>> {
    private static final RenderType SANIC_CREEPER_EYES = RenderType.eyes(new ResourceLocation(RevengeMod.MOD_ID,"textures/entity/sanic_creeper_eyes.png"));

    public UltimateCreeperEyesLayer(RenderLayerParent<T, CreeperModel<T>> aSuper) {
        super(aSuper);
    }

    public RenderType renderType() {
        return SANIC_CREEPER_EYES;
    }
}
