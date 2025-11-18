package at.extendedreach;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    // Single config value for the bonus reach
    public static final ModConfigSpec.DoubleValue REACH_BONUS = BUILDER
            .comment("The amount of reach (blocks) added to both block and entity interaction when the amulet is equipped.")
            .defineInRange("reachBonus", 3.0, 0.0, 100.0);

    static final ModConfigSpec SPEC = BUILDER.build();
}