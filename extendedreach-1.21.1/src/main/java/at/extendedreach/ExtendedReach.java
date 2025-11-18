package at.extendedreach;

import at.extendedreach.registry.ModItems;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

import net.minecraft.world.item.CreativeModeTabs; // Import this
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent; // Import this

@Mod(ExtendedReach.MODID)
public class ExtendedReach {
    public static final String MODID = "extendedreach";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ExtendedReach(IEventBus modEventBus, ModContainer modContainer) {
        // Register config
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        // Register Items
        ModItems.register(modEventBus);

        // Register Creative Tab Event
        modEventBus.addListener(this::addCreative);

        LOGGER.info("Extended Reach mod initialized");
    }

    // NEW: Method to add item to the Tools tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.REACH_AMULET.get());
        }
    }
}