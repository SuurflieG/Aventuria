package com.gypsyhost.aventuria.config;

import net.minecraftforge.common.ForgeConfigSpec;


public class ClientConfigs {


    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    static {
        BUILDER.push("Configs for SocketCraft");
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}

