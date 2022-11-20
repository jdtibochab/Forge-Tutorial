package net.jdtibochab.revengemod.entity.client.face_hugger;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jdtibochab.revengemod.RevengeMod;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.SpiderRenderer;
import net.minecraft.client.renderer.entity.layers.SpiderEyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Spider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FaceHuggerRenderer<T extends Spider> extends MobRenderer<T, SpiderModel<T>> {
    private static final ResourceLocation SPIDER_LOCATION = new ResourceLocation("textures/entity/spider/spider.png");

    public FaceHuggerRenderer(EntityRendererProvider.Context p_174401_) {
        this(p_174401_, ModelLayers.SPIDER);
    }

    @Override
    public ResourceLocation getTextureLocation(T pEntity) {
        return new ResourceLocation(RevengeMod.MOD_ID, "textures/entity/face_hugger.png");
    }

    public FaceHuggerRenderer(EntityRendererProvider.Context pContext, ModelLayerLocation pLayer) {
        super(pContext, new SpiderModel<>(pContext.bakeLayer(pLayer)), 0.8F);
        this.addLayer(new FaceHuggerEyesLayer(this));
    }

    protected float getFlipDegrees(T pLivingEntity) {
        return 180.0F;
    }

    @Override
    protected void scale(T pLivingEntity, PoseStack pMatrixStack, float pPartialTickTime) {
        float f = 0.9375F;
        f *= 0.5F;
        this.shadowRadius = 0.25F;
        pMatrixStack.scale(f, f, f);
    }

}