package net.jdtibochab.revengemod.entity.client.sanic_creeper;

import net.jdtibochab.revengemod.RevengeMod;
import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SanicCreeperPowerLayer extends SanicEnergySwirlLayer<Creeper, CreeperModel<Creeper>> {
    private final CreeperModel<Creeper> model;

    public SanicCreeperPowerLayer(RenderLayerParent<Creeper, CreeperModel<Creeper>> pRenderer, EntityModelSet modelPart) {
        super(pRenderer);
        this.model = new CreeperModel<>(modelPart.bakeLayer(ModelLayers.CREEPER_ARMOR));
    }

    protected float xOffset(float v) {
        return v * 0.01F;
    }

    protected ResourceLocation getTextureLocation() {
        return new ResourceLocation(RevengeMod.MOD_ID,"textures/entity/sanic_creeper_armor.png");
    }

    protected EntityModel<Creeper> model() {
        return this.model;
    }
}
