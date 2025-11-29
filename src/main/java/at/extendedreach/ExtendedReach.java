package at.extendedreach;

import at.extendedreach.command.ReachCommand;
import at.extendedreach.registry.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import org.slf4j.Logger;

@Mod(ExtendedReach.MODID)
public class ExtendedReach {
    public static final String MODID = "extendedreach";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ExtendedReach(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        ModItems.register(modEventBus);

        modEventBus.addListener(this::addCreative);

        NeoForge.EVENT_BUS.addListener(this::onRegisterCommands);

        LOGGER.info("Extended Reach mod initialized");
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.REACH_AMULET.get());
        }
    }

    private void onRegisterCommands(RegisterCommandsEvent event) {
        ReachCommand.register(event.getDispatcher());
    }
}