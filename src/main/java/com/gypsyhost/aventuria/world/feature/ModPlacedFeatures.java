package com.gypsyhost.aventuria.world.feature;

import com.gypsyhost.aventuria.config.CommonConfigs;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModPlacedFeatures {

    public static final Holder<PlacedFeature> TITANIUM_ORE_PLACED = PlacementUtils.register("titanium_ore_placed",
            ModConfiguredFeature.TITANIUM_ORE, ModOrePlacement.commonOrePlacement(CommonConfigs.titaniumOreVeinsPerChunk.get(), // VeinsPerChunk
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

    public static final Holder<PlacedFeature> TUNGSTEN_ORE_PLACED = PlacementUtils.register("tungsten_ore_placed",
            ModConfiguredFeature.TUNGSTEN_ORE, ModOrePlacement.commonOrePlacement(CommonConfigs.tungstenOreVeinsPerChunk.get(), // VeinsPerChunk
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

    public static final Holder<PlacedFeature> AVENTURINE_ORE_PLACED = PlacementUtils.register("aventurine_ore_placed",
            ModConfiguredFeature.AVENTURINE_ORE, ModOrePlacement.commonOrePlacement(CommonConfigs.aventurineOreVeinsPerChunk.get(), // VeinsPerChunk
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

    public static final Holder<PlacedFeature> CALCITE_ORE_PLACED = PlacementUtils.register("calcite_ore_placed",
            ModConfiguredFeature.CALCITE_ORE, ModOrePlacement.commonOrePlacement(CommonConfigs.calciteOreVeinsPerChunk.get(), // VeinsPerChunk
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

    public static final Holder<PlacedFeature> PYRITE_ORE_PLACED = PlacementUtils.register("pyrite_ore_placed",
            ModConfiguredFeature.PYRITE_ORE, ModOrePlacement.commonOrePlacement(CommonConfigs.pyriteOreVeinsPerChunk.get(), // VeinsPerChunk
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

    public static final Holder<PlacedFeature> RUBY_ORE_PLACED = PlacementUtils.register("ruby_ore_placed",
            ModConfiguredFeature.RUBY_ORE, ModOrePlacement.commonOrePlacement(CommonConfigs.rubyOreVeinsPerChunk.get(), // VeinsPerChunk
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

    public static final Holder<PlacedFeature> TOPAZ_ORE_PLACED = PlacementUtils.register("topaz_ore_placed",
            ModConfiguredFeature.TOPAZ_ORE, ModOrePlacement.commonOrePlacement(CommonConfigs.topazOreVeinsPerChunk.get(), // VeinsPerChunk
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

    public static final Holder<PlacedFeature> UNAKITE_ORE_PLACED = PlacementUtils.register("unakite_ore_placed",
            ModConfiguredFeature.UNAKITE_ORE, ModOrePlacement.commonOrePlacement(CommonConfigs.unakiteOreVeinsPerChunk.get(), // VeinsPerChunk
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

}
