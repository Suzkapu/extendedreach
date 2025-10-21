package at.extendedreach;

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
        // Register config FIRST - before accessing any config values
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        // Register reach manager
        NeoForge.EVENT_BUS.register(new ReachManager());

        LOGGER.info("Extended Reach mod initialized");
        // Don't log config values here - they're not loaded yet
    }
}