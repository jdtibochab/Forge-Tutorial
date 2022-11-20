package net.jdtibochab.revengemod.entity.client.nuclear_creeper;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jdtibochab.revengemod.RevengeMod;
import net.jdtibochab.revengemod.entity.client.sanic_creeper.SanicCreeperEyesLayer;
import net.jdtibochab.revengemod.entity.client.sanic_creeper.SanicCreeperPowerLayer;
import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.CreeperRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NuclearCreeperRenderer extends CreeperRenderer{
    public NuclearCreeperRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(Creeper pEntity) {
        return new ResourceLocation(RevengeMod.MOD_ID,"textures/entity/nuclear_creeper.png");
    }
}
