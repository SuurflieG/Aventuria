package com.gypsyhost.socketcraft.util.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class SocketCraftCommonConfig {

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> TITANIUM_ORE_VEINS_PER_CHUNK;
    public static final ForgeConfigSpec.ConfigValue<Integer> TITANIUM_ORE_VEIN_SIZE;

    public static final ForgeConfigSpec.ConfigValue<Integer> TUNGSTEN_ORE_VEINS_PER_CHUNK;
    public static final ForgeConfigSpec.ConfigValue<Integer> TUNGSTEN_ORE_VEIN_SIZE;


    static {
        BUILDER.push("Configs for MCCourseMod");

        TITANIUM_ORE_VEINS_PER_CHUNK = BUILDER.comment("How many Titanium Ore Veins spawn per chunk!")
                .define("Veins Per Chunk", 7);
        TITANIUM_ORE_VEIN_SIZE = BUILDER.comment("How many Titanium Ore Blocks spawn in one Vein!")
                .define("Vein Size", 9);

        TUNGSTEN_ORE_VEINS_PER_CHUNK = BUILDER.comment("How many Tungsten Ore Veins spawn per chunk!")
                .define("Veins Per Chunk", 7);
        TUNGSTEN_ORE_VEIN_SIZE = BUILDER.comment("How many Tungsten Ore Blocks spawn in one Vein!")
                .define("Vein Size", 9);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
