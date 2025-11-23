package at.extendedreach.registry;

import at.extendedreach.ExtendedReach;
import at.extendedreach.item.ReachAmulet;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ExtendedReach.MODID);

    public static final DeferredItem<Item> REACH_AMULET = ITEMS.register("reach_amulet", ReachAmulet::new);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}