package com.gypsyhost.aventuria.world.feature;

import com.gypsyhost.aventuria.Aventuria;
import com.gypsyhost.aventuria.config.CommonConfigs;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModPlacedFeatures {

    public static final DeferredRegister<PlacedFeature> PLACED_FEATURE = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, Aventuria.MOD_ID);

    public static final RegistryObject<PlacedFeature> TITANIUM_ORE_PLACED = PLACED_FEATURE.register("titanium_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeature.TITANIUM_ORE.getHolder().get(),
                    ModOrePlacement.commonOrePlacement(CommonConfigs.titaniumOreVeinsPerChunk.get(), // VeinsPerChunk
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80),
                    VerticalAnchor.aboveBottom(80)))));

    public static final RegistryObject<PlacedFeature> TUNGSTEN_ORE_PLACED = PLACED_FEATURE.register("tungsten_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeature.TUNGSTEN_ORE.getHolder().get(),
                    ModOrePlacement.commonOrePlacement(CommonConfigs.tungstenOreVeinsPerChunk.get(), // VeinsPerChunk
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80),
                    VerticalAnchor.aboveBottom(80)))));

    public static final RegistryObject<PlacedFeature> AVENTURINE_ORE_PLACED = PLACED_FEATURE.register("aventurine_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeature.AVENTURINE_ORE.getHolder().get(),
                    ModOrePlacement.commonOrePlacement(CommonConfigs.aventurineOreVeinsPerChunk.get(), // VeinsPerChunk
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80),
                    VerticalAnchor.aboveBottom(80)))));

    public static final RegistryObject<PlacedFeature> CALCITE_ORE_PLACED = PLACED_FEATURE.register("calcite_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeature.CALCITE_ORE.getHolder().get(),
                    ModOrePlacement.commonOrePlacement(CommonConfigs.calciteOreVeinsPerChunk.get(), // VeinsPerChunk
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80),
                    VerticalAnchor.aboveBottom(80)))));

    public static final RegistryObject<PlacedFeature> PYRITE_ORE_PLACED = PLACED_FEATURE.register("pyrite_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeature.PYRITE_ORE.getHolder().get(),
                    ModOrePlacement.commonOrePlacement(CommonConfigs.pyriteOreVeinsPerChunk.get(), // VeinsPerChunk
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80),
                    VerticalAnchor.aboveBottom(80)))));

    public static final RegistryObject<PlacedFeature> RUBY_ORE_PLACED = PLACED_FEATURE.register("ruby_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeature.RUBY_ORE.getHolder().get(),
                    ModOrePlacement.commonOrePlacement(CommonConfigs.rubyOreVeinsPerChunk.get(), // VeinsPerChunk
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80),
                    VerticalAnchor.aboveBottom(80)))));

    public static final RegistryObject<PlacedFeature> TOPAZ_ORE_PLACED = PLACED_FEATURE.register("topaz_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeature.TOPAZ_ORE.getHolder().get(),
                    ModOrePlacement.commonOrePlacement(CommonConfigs.topazOreVeinsPerChunk.get(), // VeinsPerChunk
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80),
                    VerticalAnchor.aboveBottom(80)))));

    public static final RegistryObject<PlacedFeature> UNAKITE_ORE_PLACED = PLACED_FEATURE.register("unakite_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeature.UNAKITE_ORE.getHolder().get(),
                    ModOrePlacement.commonOrePlacement(CommonConfigs.unakiteOreVeinsPerChunk.get(), // VeinsPerChunk
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80),
                    VerticalAnchor.aboveBottom(80)))));

    public static final RegistryObject<PlacedFeature> DEEPSLATE_TITANIUM_ORE_PLACED = PLACED_FEATURE.register("deepslate_titanium_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeature.DEEPSLATE_TITANIUM_ORE.getHolder().get(),
                    ModOrePlacement.commonOrePlacement(CommonConfigs.titaniumOreVeinsPerChunk.get(), // VeinsPerChunk
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80),
                                    VerticalAnchor.aboveBottom(80)))));

    public static final RegistryObject<PlacedFeature> DEEPSLATE_TUNGSTEN_ORE_PLACED = PLACED_FEATURE.register("deepslate_tungsten_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeature.DEEPSLATE_TUNGSTEN_ORE.getHolder().get(),
                    ModOrePlacement.commonOrePlacement(CommonConfigs.tungstenOreVeinsPerChunk.get(), // VeinsPerChunk
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80),
                                    VerticalAnchor.aboveBottom(80)))));

    public static final RegistryObject<PlacedFeature> DEEPSLATE_AVENTURINE_ORE_PLACED = PLACED_FEATURE.register("deepslate_aventurine_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeature.DEEPSLATE_AVENTURINE_ORE.getHolder().get(),
                    ModOrePlacement.commonOrePlacement(CommonConfigs.aventurineOreVeinsPerChunk.get(), // VeinsPerChunk
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80),
                                    VerticalAnchor.aboveBottom(80)))));

    public static final RegistryObject<PlacedFeature> DEEPSLATE_CALCITE_ORE_PLACED = PLACED_FEATURE.register("deepslate_calcite_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeature.DEEPSLATE_CALCITE_ORE.getHolder().get(),
                    ModOrePlacement.commonOrePlacement(CommonConfigs.calciteOreVeinsPerChunk.get(), // VeinsPerChunk
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80),
                                    VerticalAnchor.aboveBottom(80)))));

    public static final RegistryObject<PlacedFeature> DEEPSLATE_PYRITE_ORE_PLACED = PLACED_FEATURE.register("deepslate_pyrite_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeature.DEEPSLATE_PYRITE_ORE.getHolder().get(),
                    ModOrePlacement.commonOrePlacement(CommonConfigs.pyriteOreVeinsPerChunk.get(), // VeinsPerChunk
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80),
                                    VerticalAnchor.aboveBottom(80)))));

    public static final RegistryObject<PlacedFeature> DEEPSLATE_RUBY_ORE_PLACED = PLACED_FEATURE.register("deepslate_ruby_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeature.DEEPSLATE_RUBY_ORE.getHolder().get(),
                    ModOrePlacement.commonOrePlacement(CommonConfigs.rubyOreVeinsPerChunk.get(), // VeinsPerChunk
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80),
                                    VerticalAnchor.aboveBottom(80)))));


    public static final RegistryObject<PlacedFeature> DEEPSLATE_TOPAZ_ORE_PLACED = PLACED_FEATURE.register("deepslate_topaz_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeature.DEEPSLATE_TOPAZ_ORE.getHolder().get(),
                    ModOrePlacement.commonOrePlacement(CommonConfigs.topazOreVeinsPerChunk.get(), // VeinsPerChunk
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80),
                                    VerticalAnchor.aboveBottom(80)))));

    public static final RegistryObject<PlacedFeature> DEEPSLATE_UNAKITE_ORE_PLACED = PLACED_FEATURE.register("deepslate_unakite_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeature.DEEPSLATE_UNAKITE_ORE.getHolder().get(),
                    ModOrePlacement.commonOrePlacement(CommonConfigs.unakiteOreVeinsPerChunk.get(), // VeinsPerChunk
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80),
                                    VerticalAnchor.aboveBottom(80)))));

    public static void register(IEventBus eventBus) {
        PLACED_FEATURE.register(eventBus);
    }

}
