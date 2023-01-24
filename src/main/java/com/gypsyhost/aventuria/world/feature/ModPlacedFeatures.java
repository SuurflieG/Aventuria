package com.gypsyhost.aventuria.world.feature;

import com.google.common.collect.ImmutableList;
import com.gypsyhost.aventuria.Aventuria;
import com.gypsyhost.aventuria.config.CommonConfigs;
import com.gypsyhost.aventuria.registry.ModTreeBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.CaveFeatures;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModPlacedFeatures {

    public static final DeferredRegister<PlacedFeature> PLACED_FEATURE = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, Aventuria.MOD_ID);

    // ORE CONFIGS

    public static final RegistryObject<PlacedFeature> TITANIUM_ORE_PLACED = PLACED_FEATURE.register("titanium_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeature.TITANIUM_ORE.getHolder().get(),
                    ModOrePlacement.commonOrePlacement(CommonConfigs.titaniumOreVeinsPerChunk.get(), /*VeinsPerChunk*/
                            HeightRangePlacement.triangle(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56)))));


    public static final RegistryObject<PlacedFeature> DEEPSLATE_TITANIUM_ORE_PLACED = PLACED_FEATURE.register("deepslate_titanium_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeature.DEEPSLATE_TITANIUM_ORE.getHolder().get(),
                    ModOrePlacement.commonOrePlacement(CommonConfigs.deepslateTitaniumOreVeinsPerChunk.get(), // VeinsPerChunk
                            HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(24)))));

    // TREE CONFIGS

    public static final RegistryObject<PlacedFeature> LARCH_CHECKED = PLACED_FEATURE.register("larch_checked",
            () -> new PlacedFeature(ModConfiguredFeature.LARCH_TREE.getHolder().get(),
                    List.of(PlacementUtils.filteredByBlockSurvival(ModTreeBlocks.LARCH_SAPLING.get()))));

    public static final RegistryObject<PlacedFeature> LARCH_PLACED = PLACED_FEATURE.register("larch_placed",
            () -> new PlacedFeature(ModConfiguredFeature.LARCH_SPAWN.getHolder().get(), VegetationPlacements.treePlacement
                    (RarityFilter.onAverageOnceEvery(10))));



    // GEODE CONFIGS

    public static final RegistryObject<PlacedFeature> AVENTURINE_GEODE_PLACED = PLACED_FEATURE.register("aventurine_geode_placed",
            () -> new PlacedFeature(ModConfiguredFeature.AVENTURINE_GEODE.getHolder().get(),
                    ImmutableList.of(RarityFilter.onAverageOnceEvery(128),
                            InSquarePlacement.spread(),
                            HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(24)),
                            BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> PYRITE_GEODE_PLACED = PLACED_FEATURE.register("pyrite_geode_placed",
            () -> new PlacedFeature(ModConfiguredFeature.PYRITE_GEODE.getHolder().get(),
                    ImmutableList.of(RarityFilter.onAverageOnceEvery(128),
                            InSquarePlacement.spread(),
                            HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(24)),
                            BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> RUBY_GEODE_PLACED = PLACED_FEATURE.register("ruby_geode_placed",
            () -> new PlacedFeature(ModConfiguredFeature.RUBY_GEODE.getHolder().get(),
                    ImmutableList.of(RarityFilter.onAverageOnceEvery(128),
                            InSquarePlacement.spread(),
                            HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(24)),
                            BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> TOPAZ_GEODE_PLACED = PLACED_FEATURE.register("topaz_geode_placed",
            () -> new PlacedFeature(ModConfiguredFeature.TOPAZ_GEODE.getHolder().get(),
                    ImmutableList.of(RarityFilter.onAverageOnceEvery(128),
                            InSquarePlacement.spread(),
                            HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(24)),
                            BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> SPECTROLITE_GEODE_PLACED = PLACED_FEATURE.register("spectrolite_geode_placed",
            () -> new PlacedFeature(ModConfiguredFeature.SPECTROLITE_GEODE.getHolder().get(),
                    ImmutableList.of(RarityFilter.onAverageOnceEvery(128),
                            InSquarePlacement.spread(),
                            HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(24)),
                            BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> UNAKITE_GEODE_PLACED = PLACED_FEATURE.register("unakite_geode_placed",
            () -> new PlacedFeature(ModConfiguredFeature.UNAKITE_GEODE.getHolder().get(),
                    ImmutableList.of(RarityFilter.onAverageOnceEvery(128),
                            InSquarePlacement.spread(),
                            HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(24)),
                            BiomeFilter.biome())));

    public static void register(IEventBus eventBus) {
        PLACED_FEATURE.register(eventBus);
    }

}
