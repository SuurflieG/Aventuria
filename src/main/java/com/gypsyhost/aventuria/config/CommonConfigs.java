package com.gypsyhost.aventuria.config;

import net.minecraftforge.common.ForgeConfigSpec;


public class CommonConfigs {


    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> titaniumOreVeinsPerChunk;
    public static final ForgeConfigSpec.ConfigValue<Integer> titaniumOreVeinSize;

    public static final ForgeConfigSpec.ConfigValue<Integer> deepslateTitaniumOreVeinsPerChunk;
    public static final ForgeConfigSpec.ConfigValue<Integer> deepslateTitaniumOreVeinSize;

    static {
        BUILDER.push("Configs for SocketCraft");
        BUILDER.pop();

        BUILDER.comment("Ore Configs");
        BUILDER.push("Titanium Ore");
        titaniumOreVeinsPerChunk = BUILDER.define("Titanium Ore Veins Per Chunk", 10);
        titaniumOreVeinSize = BUILDER.define("Titanium Ore Vein Size", 14);
        BUILDER.pop();
        BUILDER.push("Deepslate Titanium Ore");
        deepslateTitaniumOreVeinsPerChunk = BUILDER.define("Deepslate Titanium Ore Veins Per Chunk", 10);
        deepslateTitaniumOreVeinSize = BUILDER.define("Deepslate Ore Vein Size", 17);
        BUILDER.pop();

        SPEC = BUILDER.build();
    }
}

