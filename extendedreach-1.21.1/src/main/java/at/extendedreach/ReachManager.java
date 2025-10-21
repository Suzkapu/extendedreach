package at.extendedreach;

import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

public class ReachManager {

    @SubscribeEvent
    public void onPlayerJoin(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof Player player) {
            updateReachAttributes(player);
        }
    }

    @SubscribeEvent
    public void onPlayerTick(PlayerTickEvent.Pre event) {
        updateReachAttributes(event.getEntity());
    }

    private void updateReachAttributes(Player player) {
        // Check if config is loaded before accessing values
        if (Config.SPEC.isLoaded()) {
            // Update block interaction range
            AttributeInstance blockReach = player.getAttribute(Attributes.BLOCK_INTERACTION_RANGE);
            if (blockReach != null) {
                blockReach.setBaseValue(Config.BLOCK_REACH_DISTANCE.get());
            }

            // Update entity interaction range
            AttributeInstance entityReach = player.getAttribute(Attributes.ENTITY_INTERACTION_RANGE);
            if (entityReach != null) {
                entityReach.setBaseValue(Config.ENTITY_REACH_DISTANCE.get());
            }
        }
    }
}