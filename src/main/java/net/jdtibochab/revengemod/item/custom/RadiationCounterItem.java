package net.jdtibochab.revengemod.item.custom;

import net.jdtibochab.revengemod.entity.client.ultimate_creeper.UltimateCreeperEntity;
import net.minecraft.ChatFormatting;
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
import org.checkerframework.checker.units.qual.A;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    private Map<UltimateCreeperEntity,Double> getSortedUltimateCreeperMap(Player player){
        List<UltimateCreeperEntity> entities = player.level.getEntitiesOfClass(UltimateCreeperEntity.class,getTargetSearchArea(player));
        Map<UltimateCreeperEntity,Double> radiationLevelMap = new HashMap<>();
        if (entities.isEmpty()){
            return radiationLevelMap;
        }
        for (UltimateCreeperEntity entity : entities){
            radiationLevelMap.put(entity,getRadiation(player,entity));
        }
        Stream<Map.Entry<UltimateCreeperEntity,Double>> sorted = radiationLevelMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue());
        Map<UltimateCreeperEntity,Double> sortedRadiationMap = sorted.collect(Collectors.toMap(
                Map.Entry::getKey,Map.Entry::getValue, (e1,e2) -> e1, LinkedHashMap::new
        ));
        return sortedRadiationMap;
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
                        ChatFormatting.YELLOW +
                        "--- Reading ---\n"
                )
        );

        Map<UltimateCreeperEntity,Double> radiationMap = getSortedUltimateCreeperMap(player);
        if (radiationMap.isEmpty()) {
            player.sendSystemMessage(
                    Component.literal(
                            ChatFormatting.YELLOW +
                            "No radiation signal found\n"
                    )
            );
        }
        for (Map.Entry<UltimateCreeperEntity,Double> entry : radiationMap.entrySet()){
            player.sendSystemMessage(
                    Component.literal(
                            ChatFormatting.YELLOW +
                            "Reading of " + String.format(entry.getKey().getDisplayName().getString())
                    )
            );
            player.sendSystemMessage(
                    Component.literal(
                            ChatFormatting.YELLOW +
                            "- Intensity: " + String.format("%.1f", entry.getValue()) + "\n"
                    )
            );
        }

        player.sendSystemMessage(
                Component.literal(
                        ChatFormatting.YELLOW +
                        "--- End ---\n"
                )
        );
    }

}
