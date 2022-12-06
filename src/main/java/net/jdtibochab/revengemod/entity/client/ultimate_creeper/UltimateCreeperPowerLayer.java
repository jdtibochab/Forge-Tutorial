package net.jdtibochab.revengemod.entity.client.ultimate_creeper;

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
public class UltimateCreeperPowerLayer extends UltimateCreeperSwirlLayer<UltimateCreeperEntity, UltimateCreeperModel<UltimateCreeperEntity>> {
    private final UltimateCreeperModel<UltimateCreeperEntity> model;

    public UltimateCreeperPowerLayer(RenderLayerParent<UltimateCreeperEntity, UltimateCreeperModel<UltimateCreeperEntity>> pRenderer, EntityModelSet modelPart) {
        super(pRenderer);
        this.model = new UltimateCreeperModel<>(modelPart.bakeLayer(ModelLayers.CREEPER_ARMOR));
    }

    protected float xOffset(float v) {
        return v * 0.01F;
    }

    protected ResourceLocation getTextureLocation() {
        return new ResourceLocation(RevengeMod.MOD_ID,"textures/entity/ultimate_creeper_armor.png");
    }

    protected EntityModel<UltimateCreeperEntity> model() {
        return this.model;
    }
}
