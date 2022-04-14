package com.gypsyhost.socketcraft.world.feature;

import com.gypsyhost.socketcraft.util.config.SocketCraftCommonConfig;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModPlacedFeatures {

    public static final Holder<PlacedFeature> TITANIUM_ORE_PLACED = PlacementUtils.register("titanium_ore_placed",
            ModConfiguredFeature.TITANIUM_ORE, ModOrePlacement.commonOrePlacement(SocketCraftCommonConfig.TITANIUM_ORE_VEINS_PER_CHUNK.get(), // VeinsPerChunk
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

    public static final Holder<PlacedFeature> TUNGSTEN_ORE_PLACED = PlacementUtils.register("tungsten_ore_placed",
            ModConfiguredFeature.TUNGSTEN_ORE, ModOrePlacement.commonOrePlacement(SocketCraftCommonConfig.TITANIUM_ORE_VEINS_PER_CHUNK.get(), // VeinsPerChunk
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

}
