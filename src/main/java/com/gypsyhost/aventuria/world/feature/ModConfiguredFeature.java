package com.gypsyhost.aventuria.world.feature;

import com.gypsyhost.aventuria.Aventuria;
import com.gypsyhost.aventuria.config.CommonConfigs;
import com.gypsyhost.aventuria.registry.ModBlocks;
import com.gypsyhost.aventuria.registry.ModTreeBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GeodeBlockSettings;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.GeodeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModConfiguredFeature {

    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, Aventuria.MOD_ID);


    public static final RegistryObject<ConfiguredFeature<?, ?>> TITANIUM_ORE = CONFIGURED_FEATURES.register("titanium_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.TITANIUM_ORE.get().defaultBlockState(),CommonConfigs.titaniumOreVeinSize.get())));

    public static final RegistryObject<ConfiguredFeature<?, ?>> DEEPSLATE_TITANIUM_ORE = CONFIGURED_FEATURES.register("deepslate_titanium_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_TITANIUM_ORE.get().defaultBlockState(),CommonConfigs.deepslateTitaniumOreVeinSize.get())));

    public static final RegistryObject<ConfiguredFeature<?,?>> LARCH_TREE = CONFIGURED_FEATURES.register("larch_tree", () ->
            new ConfiguredFeature<>(Feature.TREE,
                    new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(ModTreeBlocks.LARCH_LOG.get()),
                            new StraightTrunkPlacer(20,8,4), BlockStateProvider.simple(ModTreeBlocks.LARCH_LEAVES.get()),
                            new SpruceFoliagePlacer(UniformInt.of(8, 12), UniformInt.of(2, 4), UniformInt.of(5, 8)),
                            new TwoLayersFeatureSize(10,2,8)).build()));

    public static final RegistryObject<ConfiguredFeature<?, ?>> LARCH_SPAWN = CONFIGURED_FEATURES.register("larch_spawn", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(ModPlacedFeatures.LARCH_CHECKED.getHolder().get(),
                            0.9F)), ModPlacedFeatures.LARCH_CHECKED.getHolder().get())));



    public static final RegistryObject<ConfiguredFeature<?, ?>> AVENTURINE_GEODE = CONFIGURED_FEATURES.register("amethyst_geode",
            () -> new ConfiguredFeature<>(Feature.GEODE, new GeodeConfiguration(new GeodeBlockSettings(BlockStateProvider.simple(Blocks.AIR),
                    BlockStateProvider.simple(ModBlocks.AVENTURINE_BLOCK.get()),
                    BlockStateProvider.simple(ModBlocks.BUDDING_AVENTURINE.get()),
                    BlockStateProvider.simple(Blocks.CALCITE),
                    BlockStateProvider.simple(Blocks.SMOOTH_BASALT),
                    List.of(ModBlocks.SMALL_AVENTURINE_BUD.get().defaultBlockState(),
                            ModBlocks.MEDIUM_AVENTURINE_BUD.get().defaultBlockState(),
                            ModBlocks.LARGE_AVENTURINE_BUD.get().defaultBlockState(),
                            ModBlocks.AVENTURINE_CLUSTER.get().defaultBlockState()),
                    BlockTags.FEATURES_CANNOT_REPLACE,
                    BlockTags.GEODE_INVALID_BLOCKS),
                    new GeodeLayerSettings(1.7D, 2.2D, 3.2D, 4.2D),
                    new GeodeCrackSettings(0.95D, 2.0D, 2), 0.35D,
                    0.083D, true, UniformInt.of(4, 6),
                    UniformInt.of(3, 4),
                    UniformInt.of(1, 2), -16, 16, 0.05D, 1)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> PYRITE_GEODE = CONFIGURED_FEATURES.register("pyrite_geode",
            () -> new ConfiguredFeature<>(Feature.GEODE, new GeodeConfiguration(new GeodeBlockSettings(BlockStateProvider.simple(Blocks.AIR),
                    BlockStateProvider.simple(ModBlocks.PYRITE_BLOCK.get()),
                    BlockStateProvider.simple(ModBlocks.BUDDING_PYRITE.get()),
                    BlockStateProvider.simple(Blocks.CALCITE),
                    BlockStateProvider.simple(Blocks.SMOOTH_BASALT),
                    List.of(ModBlocks.SMALL_PYRITE_BUD.get().defaultBlockState(),
                            ModBlocks.MEDIUM_PYRITE_BUD.get().defaultBlockState(),
                            ModBlocks.LARGE_PYRITE_BUD.get().defaultBlockState(),
                            ModBlocks.PYRITE_CLUSTER.get().defaultBlockState()),
                    BlockTags.FEATURES_CANNOT_REPLACE,
                    BlockTags.GEODE_INVALID_BLOCKS),
                    new GeodeLayerSettings(1.7D, 2.2D, 3.2D, 4.2D),
                    new GeodeCrackSettings(0.95D, 2.0D, 2), 0.35D,
                    0.083D, true, UniformInt.of(4, 6),
                    UniformInt.of(3, 4),
                    UniformInt.of(1, 2), -16, 16, 0.05D, 1)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> RUBY_GEODE = CONFIGURED_FEATURES.register("ruby_geode",
            () -> new ConfiguredFeature<>(Feature.GEODE, new GeodeConfiguration(new GeodeBlockSettings(BlockStateProvider.simple(Blocks.AIR),
                    BlockStateProvider.simple(ModBlocks.RUBY_BLOCK.get()),
                    BlockStateProvider.simple(ModBlocks.BUDDING_RUBY.get()),
                    BlockStateProvider.simple(Blocks.CALCITE),
                    BlockStateProvider.simple(Blocks.SMOOTH_BASALT),
                    List.of(ModBlocks.SMALL_RUBY_BUD.get().defaultBlockState(),
                            ModBlocks.MEDIUM_RUBY_BUD.get().defaultBlockState(),
                            ModBlocks.LARGE_RUBY_BUD.get().defaultBlockState(),
                            ModBlocks.RUBY_CLUSTER.get().defaultBlockState()),
                    BlockTags.FEATURES_CANNOT_REPLACE,
                    BlockTags.GEODE_INVALID_BLOCKS),
                    new GeodeLayerSettings(1.7D, 2.2D, 3.2D, 4.2D),
                    new GeodeCrackSettings(0.95D, 2.0D, 2), 0.35D,
                    0.083D, true, UniformInt.of(4, 6),
                    UniformInt.of(3, 4),
                    UniformInt.of(1, 2), -16, 16, 0.05D, 1)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> TOPAZ_GEODE = CONFIGURED_FEATURES.register("topaz_geode",
            () -> new ConfiguredFeature<>(Feature.GEODE, new GeodeConfiguration(new GeodeBlockSettings(BlockStateProvider.simple(Blocks.AIR),
                    BlockStateProvider.simple(ModBlocks.TOPAZ_BLOCK.get()),
                    BlockStateProvider.simple(ModBlocks.BUDDING_TOPAZ.get()),
                    BlockStateProvider.simple(Blocks.CALCITE),
                    BlockStateProvider.simple(Blocks.SMOOTH_BASALT),
                    List.of(ModBlocks.SMALL_TOPAZ_BUD.get().defaultBlockState(),
                            ModBlocks.MEDIUM_TOPAZ_BUD.get().defaultBlockState(),
                            ModBlocks.LARGE_TOPAZ_BUD.get().defaultBlockState(),
                            ModBlocks.TOPAZ_CLUSTER.get().defaultBlockState()),
                    BlockTags.FEATURES_CANNOT_REPLACE,
                    BlockTags.GEODE_INVALID_BLOCKS),
                    new GeodeLayerSettings(1.7D, 2.2D, 3.2D, 4.2D),
                    new GeodeCrackSettings(0.95D, 2.0D, 2), 0.35D,
                    0.083D, true, UniformInt.of(4, 6),
                    UniformInt.of(3, 4),
                    UniformInt.of(1, 2), -16, 16, 0.05D, 1)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> SPECTROLITE_GEODE = CONFIGURED_FEATURES.register("spectrolite_geode",
            () -> new ConfiguredFeature<>(Feature.GEODE, new GeodeConfiguration(new GeodeBlockSettings(BlockStateProvider.simple(Blocks.AIR),
                    BlockStateProvider.simple(ModBlocks.SPECTROLITE_BLOCK.get()),
                    BlockStateProvider.simple(ModBlocks.BUDDING_SPECTROLITE.get()),
                    BlockStateProvider.simple(Blocks.CALCITE),
                    BlockStateProvider.simple(Blocks.SMOOTH_BASALT),
                    List.of(ModBlocks.SMALL_SPECTROLITE_BUD.get().defaultBlockState(),
                            ModBlocks.MEDIUM_SPECTROLITE_BUD.get().defaultBlockState(),
                            ModBlocks.LARGE_SPECTROLITE_BUD.get().defaultBlockState(),
                            ModBlocks.SPECTROLITE_CLUSTER.get().defaultBlockState()),
                    BlockTags.FEATURES_CANNOT_REPLACE,
                    BlockTags.GEODE_INVALID_BLOCKS),
                    new GeodeLayerSettings(1.7D, 2.2D, 3.2D, 4.2D),
                    new GeodeCrackSettings(0.95D, 2.0D, 2), 0.35D,
                    0.083D, true, UniformInt.of(4, 6),
                    UniformInt.of(3, 4),
                    UniformInt.of(1, 2), -16, 16, 0.05D, 1)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> UNAKITE_GEODE = CONFIGURED_FEATURES.register("unakite_geode",
            () -> new ConfiguredFeature<>(Feature.GEODE, new GeodeConfiguration(new GeodeBlockSettings(BlockStateProvider.simple(Blocks.AIR),
                    BlockStateProvider.simple(ModBlocks.UNAKITE_BLOCK.get()),
                    BlockStateProvider.simple(ModBlocks.BUDDING_UNAKITE.get()),
                    BlockStateProvider.simple(Blocks.CALCITE),
                    BlockStateProvider.simple(Blocks.SMOOTH_BASALT),
                    List.of(ModBlocks.SMALL_UNAKITE_BUD.get().defaultBlockState(),
                            ModBlocks.MEDIUM_UNAKITE_BUD.get().defaultBlockState(),
                            ModBlocks.LARGE_UNAKITE_BUD.get().defaultBlockState(),
                            ModBlocks.UNAKITE_CLUSTER.get().defaultBlockState()),
                    BlockTags.FEATURES_CANNOT_REPLACE,
                    BlockTags.GEODE_INVALID_BLOCKS),
                    new GeodeLayerSettings(1.7D, 2.2D, 3.2D, 4.2D),
                    new GeodeCrackSettings(0.95D, 2.0D, 2), 0.35D,
                    0.083D, true, UniformInt.of(4, 6),
                    UniformInt.of(3, 4),
                    UniformInt.of(1, 2), -16, 16, 0.05D, 1)));


    public static void register(IEventBus eventBus) {
        CONFIGURED_FEATURES.register(eventBus);
    }


}
