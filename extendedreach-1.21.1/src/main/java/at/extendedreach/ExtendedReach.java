package at.extendedreach;

import at.extendedreach.registry.ModItems; // Import the new class
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;

@Mod(ExtendedReach.MODID)
public class ExtendedReach {
    public static final String MODID = "extendedreach";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ExtendedReach(IEventBus modEventBus, ModContainer modContainer) {
        // Register config FIRST
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        // Register Items
        ModItems.register(modEventBus);

        LOGGER.info("Extended Reach mod initialized");
    }
}