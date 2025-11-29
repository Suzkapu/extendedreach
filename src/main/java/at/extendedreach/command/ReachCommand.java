package at.extendedreach.command;

import at.extendedreach.Config;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class ReachCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("reach")
                .requires(source -> source.hasPermission(2))
                .then(Commands.literal("set")
                        .then(Commands.argument("amount", DoubleArgumentType.doubleArg(0.0, 100.0))
                                .executes(context -> {
                                    double amount = DoubleArgumentType.getDouble(context, "amount");

                                    Config.REACH_BONUS.set(amount);

                                    context.getSource().sendSuccess(() ->
                                                    Component.literal("Reach bonus set to: " + amount + " blocks. \nNOTE: Re-equip your amulet for this to take effect!"),
                                            true
                                    );
                                    return 1;
                                })
                        )
                )
        );
    }
}