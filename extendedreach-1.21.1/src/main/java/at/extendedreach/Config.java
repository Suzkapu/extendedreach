package at.extendedreach;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.DoubleValue BLOCK_REACH_DISTANCE = BUILDER
            .comment("Block interaction reach distance in blocks (vanilla is 4.5)")
            .defineInRange("blockReachDistance", 6.0, 1.0, 50.0);

    public static final ModConfigSpec.DoubleValue ENTITY_REACH_DISTANCE = BUILDER
            .comment("Entity interaction reach distance in blocks (vanilla is 3.0)")
            .defineInRange("entityReachDistance", 4.5, 1.0, 50.0);

    static final ModConfigSpec SPEC = BUILDER.build();
}