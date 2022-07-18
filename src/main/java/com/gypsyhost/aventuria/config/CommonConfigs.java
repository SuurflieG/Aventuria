package com.gypsyhost.aventuria.config;

import net.minecraftforge.common.ForgeConfigSpec;


public class CommonConfigs {


    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> titaniumOreVeinsPerChunk;
    public static final ForgeConfigSpec.ConfigValue<Integer> tungstenOreVeinsPerChunk;
    public static final ForgeConfigSpec.ConfigValue<Integer> aventurineOreVeinsPerChunk;
    public static final ForgeConfigSpec.ConfigValue<Integer> calciteOreVeinsPerChunk;
    public static final ForgeConfigSpec.ConfigValue<Integer> pyriteOreVeinsPerChunk;
    public static final ForgeConfigSpec.ConfigValue<Integer> rubyOreVeinsPerChunk;
    public static final ForgeConfigSpec.ConfigValue<Integer> topazOreVeinsPerChunk;
    public static final ForgeConfigSpec.ConfigValue<Integer> unakiteOreVeinsPerChunk;
    
    public static final ForgeConfigSpec.ConfigValue<Integer> titaniumOreVeinSize;
    public static final ForgeConfigSpec.ConfigValue<Integer> tungstenOreVeinSize;
    public static final ForgeConfigSpec.ConfigValue<Integer> aventurineOreVeinSize;
    public static final ForgeConfigSpec.ConfigValue<Integer> calciteOreVeinSize;
    public static final ForgeConfigSpec.ConfigValue<Integer> pyriteOreVeinSize;
    public static final ForgeConfigSpec.ConfigValue<Integer> rubyOreVeinSize;
    public static final ForgeConfigSpec.ConfigValue<Integer> topazOreVeinSize;
    public static final ForgeConfigSpec.ConfigValue<Integer> unakiteOreVeinSize;


    static {
        BUILDER.push("Configs for SocketCraft");
        BUILDER.pop();

        BUILDER.comment("Ore Configs");
        BUILDER.push("Titanium Ore");
        titaniumOreVeinsPerChunk = BUILDER.define("Titanium Ore Veins Per Chunk", 10);
        titaniumOreVeinSize = BUILDER.define("Titanium Ore Vein Size", 13);
        BUILDER.pop();
        BUILDER.push("Tungsten Ore");
        tungstenOreVeinsPerChunk = BUILDER.define("Tungsten Ore Veins Per Chunk", 10);
        tungstenOreVeinSize = BUILDER.define("Tungsten Ore Vein Size", 13);
        BUILDER.pop();
        BUILDER.push("Aventurine Ore");
        aventurineOreVeinsPerChunk = BUILDER.define("Aventurine Ore Veins Per Chunk", 10);
        aventurineOreVeinSize = BUILDER.define("Aventurine Ore Vein Size", 13);
        BUILDER.pop();
        BUILDER.push("Calcite Ore");
        calciteOreVeinsPerChunk = BUILDER.define("Calcite Ore Veins Per Chunk", 10);
        calciteOreVeinSize = BUILDER.define("Calcite Ore Vein Size", 13);
        BUILDER.pop();
        BUILDER.push("Pyrite Ore");
        pyriteOreVeinsPerChunk = BUILDER.define("Pyrite Ore Veins Per Chunk", 10);
        pyriteOreVeinSize = BUILDER.define("Pyrite Ore Vein Size", 13);
        BUILDER.pop();
        BUILDER.push("Ruby Ore");
        rubyOreVeinsPerChunk = BUILDER.define("Ruby Ore Veins Per Chunk", 10);
        rubyOreVeinSize = BUILDER.define("Ruby Ore Vein Size", 13);
        BUILDER.pop();
        BUILDER.push("Topaz Ore");
        topazOreVeinsPerChunk = BUILDER.define("Topaz Ore Veins Per Chunk", 10);
        topazOreVeinSize = BUILDER.define("Topaz Ore Vein Size", 13);
        BUILDER.pop();
        BUILDER.push("Unakite Ore");
        unakiteOreVeinsPerChunk = BUILDER.define("Unakite Ore Veins Per Chunk", 10);
        unakiteOreVeinSize = BUILDER.define("Unakite Ore Vein Size", 13);
        BUILDER.pop();

        SPEC = BUILDER.build();
    }
}

