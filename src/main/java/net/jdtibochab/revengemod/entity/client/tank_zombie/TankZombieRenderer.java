package net.jdtibochab.revengemod.entity.client.tank_zombie;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jdtibochab.revengemod.RevengeMod;
import net.minecraft.client.model.IronGolemModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IronGolemRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Zombie;

public class TankZombieRenderer extends MobRenderer<TankZombieEntity, TankZombieModel<TankZombieEntity>> {
    public TankZombieRenderer(EntityRendererProvider.Context context) {
        super(context, new TankZombieModel<>(context.bakeLayer(ModelLayers.IRON_GOLEM)), 0.7F);
    }
    private static final ResourceLocation GOLEM_LOCATION = new ResourceLocation(RevengeMod.MOD_ID, "textures/entity/tank_zombie.png");
    @Override
    public ResourceLocation getTextureLocation(TankZombieEntity pEntity) {
        return GOLEM_LOCATION;
    }
//    @Override
//    public ResourceLocation getTextureLocation(Zombie pEntity) {
//        return new ResourceLocation(RevengeMod.MOD_ID,"textures/entity/tank_zombie.png");
//    }

//    @Override
//    protected void scale(IronGolem pLivingEntity, PoseStack pMatrixStack, float pPartialTickTime) {
//        float f = 0.9375F;
//        f *= 2.0F;
//        this.shadowRadius = 0.25F;
//        pMatrixStack.scale(f, f, f);
//    }
}
