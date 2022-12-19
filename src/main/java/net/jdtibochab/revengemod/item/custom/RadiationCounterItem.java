package net.jdtibochab.revengemod.item.custom;

import net.jdtibochab.revengemod.entity.client.ultimate_creeper.UltimateCreeperEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class RadiationCounterItem extends Item {
    public RadiationCounterItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {
            outputReading(player);
            player.getCooldowns().addCooldown(this, 100);
        }
        return super.use(level, player, hand);
    }

    protected AABB getTargetSearchArea(Player player) {
        double pTargetDistance = 120.0D;
        return player.getBoundingBox().inflate(pTargetDistance, pTargetDistance, pTargetDistance);
    }

    private List<UltimateCreeperEntity> getUltimateCreepers(Player player){
        List<UltimateCreeperEntity> entities = player.level.getEntitiesOfClass(UltimateCreeperEntity.class,getTargetSearchArea(player));
        return entities;
    }

    public double getRadiation(Player player, UltimateCreeperEntity ultimateCreeper){
        double distanceToSource;
        distanceToSource = Math.sqrt(ultimateCreeper.distanceToSqr(player.getX(),player.getY(),player.getZ()));
        if (distanceToSource > 0) {
            return 10000.0D / Math.pow(distanceToSource,2);
        } else {
            return 99999.9D;
        }
    }

    private void outputReading(Player player){
        player.sendSystemMessage(
                Component.literal(
                        "--- Reading ---\n"
                )
        );

        List<UltimateCreeperEntity> entities = getUltimateCreepers(player);
        if (entities.isEmpty()) {
            player.sendSystemMessage(
                    Component.literal(
                            "No signal found\n"
                    )
            );
        }
        for (UltimateCreeperEntity entity : entities){
            player.sendSystemMessage(
                    Component.literal(
                            "Reading of " + String.format(entity.getDisplayName().getString())
                    )
            );
            player.sendSystemMessage(
                    Component.literal(
                            "- Intensity: " + String.format("%.1f", getRadiation(player,entity)) + "\n"
                    )
            );
        }

        player.sendSystemMessage(
                Component.literal(
                        "--- End ---\n"
                )
        );
    }
}
