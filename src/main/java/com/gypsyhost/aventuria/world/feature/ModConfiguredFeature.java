package com.gypsyhost.aventuria.world.feature;

import com.gypsyhost.aventuria.Aventuria;
import com.gypsyhost.aventuria.config.CommonConfigs;
import com.gypsyhost.aventuria.registry.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModConfiguredFeature {

    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, Aventuria.MOD_ID);

    // Stone spawn Ores

    public static final RegistryObject<ConfiguredFeature<?, ?>> TITANIUM_ORE = CONFIGURED_FEATURES.register("titanium_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.TITANIUM_ORE.get().defaultBlockState(),CommonConfigs.titaniumOreVeinSize.get())));

    public static final RegistryObject<ConfiguredFeature<?, ?>> TUNGSTEN_ORE = CONFIGURED_FEATURES.register("tungsten_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.TUNGSTEN_ORE.get().defaultBlockState(),CommonConfigs.tungstenOreVeinSize.get())));

    public static final RegistryObject<ConfiguredFeature<?, ?>> AVENTURINE_ORE = CONFIGURED_FEATURES.register("aventurine_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.AVENTURINE_ORE.get().defaultBlockState(),CommonConfigs.aventurineOreVeinSize.get())));

    public static final RegistryObject<ConfiguredFeature<?, ?>> CALCITE_ORE = CONFIGURED_FEATURES.register("calcite_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.CALCITE_ORE.get().defaultBlockState(),CommonConfigs.calciteOreVeinSize.get())));

    public static final RegistryObject<ConfiguredFeature<?, ?>> PYRITE_ORE = CONFIGURED_FEATURES.register("pyrite_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.PYRITE_ORE.get().defaultBlockState(),CommonConfigs.pyriteOreVeinSize.get())));

    public static final RegistryObject<ConfiguredFeature<?, ?>> RUBY_ORE = CONFIGURED_FEATURES.register("ruby_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.RUBY_ORE.get().defaultBlockState(),CommonConfigs.rubyOreVeinSize.get())));

    public static final RegistryObject<ConfiguredFeature<?, ?>> TOPAZ_ORE = CONFIGURED_FEATURES.register("topaz_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.TOPAZ_ORE.get().defaultBlockState(),CommonConfigs.topazOreVeinSize.get())));

    public static final RegistryObject<ConfiguredFeature<?, ?>> UNAKITE_ORE = CONFIGURED_FEATURES.register("unakite_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.UNAKITE_ORE.get().defaultBlockState(),CommonConfigs.unakiteOreVeinSize.get())));

    // Deepslate Ores

    public static final RegistryObject<ConfiguredFeature<?, ?>> DEEPSLATE_TITANIUM_ORE = CONFIGURED_FEATURES.register("deepslate_titanium_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_TITANIUM_ORE.get().defaultBlockState(),CommonConfigs.titaniumOreVeinSize.get())));

    public static final RegistryObject<ConfiguredFeature<?, ?>> DEEPSLATE_TUNGSTEN_ORE = CONFIGURED_FEATURES.register("deepslate_tungsten_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_TUNGSTEN_ORE.get().defaultBlockState(),CommonConfigs.tungstenOreVeinSize.get())));

    public static final RegistryObject<ConfiguredFeature<?, ?>> DEEPSLATE_AVENTURINE_ORE = CONFIGURED_FEATURES.register("deepslate_aventurine_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_AVENTURINE_ORE.get().defaultBlockState(),CommonConfigs.aventurineOreVeinSize.get())));

    public static final RegistryObject<ConfiguredFeature<?, ?>> DEEPSLATE_CALCITE_ORE = CONFIGURED_FEATURES.register("deepslate_calcite_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_CALCITE_ORE.get().defaultBlockState(),CommonConfigs.calciteOreVeinSize.get())));

    public static final RegistryObject<ConfiguredFeature<?, ?>> DEEPSLATE_PYRITE_ORE = CONFIGURED_FEATURES.register("deepslate_pyrite_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_PYRITE_ORE.get().defaultBlockState(),CommonConfigs.pyriteOreVeinSize.get())));

    public static final RegistryObject<ConfiguredFeature<?, ?>> DEEPSLATE_RUBY_ORE = CONFIGURED_FEATURES.register("deepslate_ruby_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_RUBY_ORE.get().defaultBlockState(),CommonConfigs.rubyOreVeinSize.get())));

    public static final RegistryObject<ConfiguredFeature<?, ?>> DEEPSLATE_TOPAZ_ORE = CONFIGURED_FEATURES.register("deepslate_topaz_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_TOPAZ_ORE.get().defaultBlockState(),CommonConfigs.topazOreVeinSize.get())));

    public static final RegistryObject<ConfiguredFeature<?, ?>> DEEPSLATE_UNAKITE_ORE = CONFIGURED_FEATURES.register("deepslate_unakite_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_UNAKITE_ORE.get().defaultBlockState(),CommonConfigs.unakiteOreVeinSize.get())));

    public static void register(IEventBus eventBus) {
        CONFIGURED_FEATURES.register(eventBus);
    }


}
