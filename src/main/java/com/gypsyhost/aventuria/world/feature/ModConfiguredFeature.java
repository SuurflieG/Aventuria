package com.gypsyhost.aventuria.world.feature;

import com.gypsyhost.aventuria.config.CommonConfigs;
import com.gypsyhost.aventuria.registry.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

import java.util.List;

public class ModConfiguredFeature {

    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_TITANIUM_ORES = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.TITANIUM_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_TITANIUM_ORE.get().defaultBlockState()));

    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_TUNGSTEN_ORES = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.TUNGSTEN_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_TUNGSTEN_ORE.get().defaultBlockState()));

    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_AVENTURINE_ORES = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.AVENTURINE_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_AVENTURINE_ORE.get().defaultBlockState()));

    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_CALCITE_ORES = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.CALCITE_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_CALCITE_ORE.get().defaultBlockState()));

    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_PYRITE_ORES = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.PYRITE_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_PYRITE_ORE.get().defaultBlockState()));

    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_RUBY_ORES = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.RUBY_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_RUBY_ORE.get().defaultBlockState()));

    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_TOPAZ_ORES = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.TOPAZ_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_TOPAZ_ORE.get().defaultBlockState()));

    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_UNAKITE_ORES = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.UNAKITE_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_UNAKITE_ORE.get().defaultBlockState()));


    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> TITANIUM_ORE = FeatureUtils.register("titanium_ore",
            Feature.ORE, new OreConfiguration(OVERWORLD_TITANIUM_ORES, CommonConfigs.titaniumOreVeinSize.get()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> TUNGSTEN_ORE = FeatureUtils.register("tungsten_ore",
            Feature.ORE, new OreConfiguration(OVERWORLD_TUNGSTEN_ORES, CommonConfigs.tungstenOreVeinSize.get()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> AVENTURINE_ORE = FeatureUtils.register("aventurine_ore",
            Feature.ORE, new OreConfiguration(OVERWORLD_AVENTURINE_ORES, CommonConfigs.tungstenOreVeinSize.get()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> CALCITE_ORE = FeatureUtils.register("calcite_ore",
            Feature.ORE, new OreConfiguration(OVERWORLD_CALCITE_ORES, CommonConfigs.calciteOreVeinSize.get()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> PYRITE_ORE = FeatureUtils.register("pyrite_ore",
            Feature.ORE, new OreConfiguration(OVERWORLD_PYRITE_ORES, CommonConfigs.pyriteOreVeinSize.get()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> RUBY_ORE = FeatureUtils.register("ruby_ore",
            Feature.ORE, new OreConfiguration(OVERWORLD_RUBY_ORES, CommonConfigs.rubyOreVeinSize.get()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> TOPAZ_ORE = FeatureUtils.register("topaz_ore",
            Feature.ORE, new OreConfiguration(OVERWORLD_TOPAZ_ORES, CommonConfigs.topazOreVeinSize.get()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> UNAKITE_ORE = FeatureUtils.register("unakite_ore",
            Feature.ORE, new OreConfiguration(OVERWORLD_UNAKITE_ORES, CommonConfigs.unakiteOreVeinSize.get()));
}
