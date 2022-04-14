package com.gypsyhost.socketcraft.world.feature;

import com.gypsyhost.socketcraft.registry.ModBlocks;
import com.gypsyhost.socketcraft.util.config.SocketCraftCommonConfig;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

import java.util.List;

public class ModConfiguredFeature {

    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_TITANIUM_ORES = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.TITANIUM_ORE.get().defaultBlockState()));
            //OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.TITANIUM_DEEPSLATE_ORE.get().defaultBlockState())),

    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_TUNGSTEN_ORES = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.TUNGSTEN_ORE.get().defaultBlockState()));
            //OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.TUNGSTEN_DEEPSLATE_ORE.get().defaultBlockState()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> TITANIUM_ORE = FeatureUtils.register("titanium_ore",
            Feature.ORE, new OreConfiguration(OVERWORLD_TITANIUM_ORES, SocketCraftCommonConfig.TITANIUM_ORE_VEIN_SIZE.get()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> TUNGSTEN_ORE = FeatureUtils.register("tungsten_ore",
            Feature.ORE, new OreConfiguration(OVERWORLD_TUNGSTEN_ORES, SocketCraftCommonConfig.TUNGSTEN_ORE_VEIN_SIZE.get()));
}
