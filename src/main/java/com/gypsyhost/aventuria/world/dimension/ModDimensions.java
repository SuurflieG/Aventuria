package com.gypsyhost.aventuria.world.dimension;

import com.gypsyhost.aventuria.Aventuria;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

public class ModDimensions {

    public static final ResourceKey<Level> AVENTURIA_KEY = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(Aventuria.MOD_ID, "aventuriadim"));
    public static final ResourceKey<DimensionType> AVENTURIA_TYPE = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, AVENTURIA_KEY.getRegistryName());

    public static void register() {
        System.out.println("Registering ModDimensions for " + Aventuria.MOD_ID);
    }
}
