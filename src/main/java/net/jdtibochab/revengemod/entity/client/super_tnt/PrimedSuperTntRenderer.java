package net.jdtibochab.revengemod.entity.client.super_tnt;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jdtibochab.revengemod.RevengeMod;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.TntRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.item.PrimedTnt;

public class PrimedSuperTntRenderer extends TntRenderer {
    public PrimedSuperTntRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }
}
