package net.jdtibochab.revengemod.entity.client.face_hugger;

import net.jdtibochab.revengemod.RevengeMod;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.SpiderEyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class FaceHuggerEyesLayer extends SpiderEyesLayer {

    private static final RenderType SPIDER_EYES = RenderType.eyes(new ResourceLocation(RevengeMod.MOD_ID,"textures/entity/face_hugger_eyes.png"));
    public FaceHuggerEyesLayer(RenderLayerParent layerParent) {
        super(layerParent);
    }

    @Override
    public RenderType renderType() {
        return SPIDER_EYES;
    }
}
