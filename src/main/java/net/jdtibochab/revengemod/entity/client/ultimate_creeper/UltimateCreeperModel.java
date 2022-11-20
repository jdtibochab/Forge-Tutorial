package net.jdtibochab.revengemod.entity.client.ultimate_creeper;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.model.geom.ModelPart;

public class UltimateCreeperModel extends CreeperModel {
    public UltimateCreeperModel(ModelPart pRoot) {
        super(pRoot);
    }

    @Override
    public void renderToBuffer(PoseStack stack, VertexConsumer vertexConsumer, int i, int i1, float v, float v1, float p_170631_, float p_170632_) {
        super.renderToBuffer(stack, vertexConsumer, 15728640, i1, v, v1, p_170631_, p_170632_);
    }
}
