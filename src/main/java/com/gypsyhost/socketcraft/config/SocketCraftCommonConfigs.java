package com.gypsyhost.socketcraft.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class SocketCraftCommonConfigs {

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> TITANIUM_ORE_VEINS_PER_CHUNK;
    public static final ForgeConfigSpec.ConfigValue<Integer> TUNGSTEN_ORE_VEINS_PER_CHUNK;
    public static final ForgeConfigSpec.ConfigValue<Integer> AVENTURINE_ORE_VEINS_PER_CHUNK;
    public static final ForgeConfigSpec.ConfigValue<Integer> CALCITE_ORE_VEINS_PER_CHUNK;
    public static final ForgeConfigSpec.ConfigValue<Integer> PYRITE_ORE_VEINS_PER_CHUNK;
    public static final ForgeConfigSpec.ConfigValue<Integer> RUBY_ORE_VEINS_PER_CHUNK;
    public static final ForgeConfigSpec.ConfigValue<Integer> TOPAZ_ORE_VEINS_PER_CHUNK;
    public static final ForgeConfigSpec.ConfigValue<Integer> UNAKITE_ORE_VEINS_PER_CHUNK;
    
    public static final ForgeConfigSpec.ConfigValue<Integer> TITANIUM_ORE_VEIN_SIZE;
    public static final ForgeConfigSpec.ConfigValue<Integer> TUNGSTEN_ORE_VEIN_SIZE;
    public static final ForgeConfigSpec.ConfigValue<Integer> AVENTURINE_ORE_VEIN_SIZE;
    public static final ForgeConfigSpec.ConfigValue<Integer> CALCITE_ORE_VEIN_SIZE;
    public static final ForgeConfigSpec.ConfigValue<Integer> PYRITE_ORE_VEIN_SIZE;
    public static final ForgeConfigSpec.ConfigValue<Integer> RUBY_ORE_VEIN_SIZE;
    public static final ForgeConfigSpec.ConfigValue<Integer> TOPAZ_ORE_VEIN_SIZE;
    public static final ForgeConfigSpec.ConfigValue<Integer> UNAKITE_ORE_VEIN_SIZE;

    public static final ForgeConfigSpec.ConfigValue<Integer> GENERATOR_BASIC_MAX_CAPACITY;


    static {
        BUILDER.push("Configs for SocketCraft");

        BUILDER.push("Basic Power Generator");
        GENERATOR_BASIC_MAX_CAPACITY = BUILDER.define("Max Power Storage", 50000);
        BUILDER.pop();


        BUILDER.comment("Ore Configs");
        BUILDER.pop();
        BUILDER.push("Titanium Ore");
        TITANIUM_ORE_VEINS_PER_CHUNK = BUILDER.define("Titanium Ore Veins Per Chunk", 10);
        TITANIUM_ORE_VEIN_SIZE = BUILDER.define("Titanium Ore Vein Size", 13);
        BUILDER.pop();
        BUILDER.push("Tungsten Ore");
        TUNGSTEN_ORE_VEINS_PER_CHUNK = BUILDER.define("Tungsten Ore Veins Per Chunk", 10);
        TUNGSTEN_ORE_VEIN_SIZE = BUILDER.define("Tungsten Ore Vein Size", 13);
        BUILDER.pop();
        BUILDER.push("Aventurine Ore");
        AVENTURINE_ORE_VEINS_PER_CHUNK = BUILDER.define("Aventurine Ore Veins Per Chunk", 10);
        AVENTURINE_ORE_VEIN_SIZE = BUILDER.define("Aventurine Ore Vein Size", 13);
        BUILDER.pop();
        BUILDER.push("Calcite Ore");
        CALCITE_ORE_VEINS_PER_CHUNK = BUILDER.define("Calcite Ore Veins Per Chunk", 10);
        CALCITE_ORE_VEIN_SIZE = BUILDER.define("Calcite Ore Vein Size", 13);
        BUILDER.pop();
        BUILDER.push("Pyrite Ore");
        PYRITE_ORE_VEINS_PER_CHUNK = BUILDER.define("Pyrite Ore Veins Per Chunk", 10);
        PYRITE_ORE_VEIN_SIZE = BUILDER.define("Pyrite Ore Vein Size", 13);
        BUILDER.pop();
        BUILDER.push("Ruby Ore");
        RUBY_ORE_VEINS_PER_CHUNK = BUILDER.define("Ruby Ore Veins Per Chunk", 10);
        RUBY_ORE_VEIN_SIZE = BUILDER.define("Ruby Ore Vein Size", 13);
        BUILDER.pop();
        BUILDER.push("Topaz Ore");
        TOPAZ_ORE_VEINS_PER_CHUNK = BUILDER.define("Topaz Ore Veins Per Chunk", 10);
        TOPAZ_ORE_VEIN_SIZE = BUILDER.define("Topaz Ore Vein Size", 13);
        BUILDER.pop();
        BUILDER.push("Unakite Ore");
        UNAKITE_ORE_VEINS_PER_CHUNK = BUILDER.define("Unakite Ore Veins Per Chunk", 10);
        UNAKITE_ORE_VEIN_SIZE = BUILDER.define("Unakite Ore Vein Size", 13);
        BUILDER.pop();

        SPEC = BUILDER.build();
    }
}
