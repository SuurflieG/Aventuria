package com.gypsyhost.aventuria.world.gen;

import com.gypsyhost.aventuria.world.feature.ModPlacedFeatures;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;

public class ModOreGeneration {
    public static void generateOres(final BiomeLoadingEvent event) {
        List<Holder<PlacedFeature>> base = event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES);

        base.add(ModPlacedFeatures.TITANIUM_ORE_PLACED.getHolder().get());
        base.add(ModPlacedFeatures.TUNGSTEN_ORE_PLACED.getHolder().get());
        base.add(ModPlacedFeatures.AVENTURINE_ORE_PLACED.getHolder().get());
        base.add(ModPlacedFeatures.CALCITE_ORE_PLACED.getHolder().get());
        base.add(ModPlacedFeatures.PYRITE_ORE_PLACED.getHolder().get());
        base.add(ModPlacedFeatures.RUBY_ORE_PLACED.getHolder().get());
        base.add(ModPlacedFeatures.TOPAZ_ORE_PLACED.getHolder().get());
        base.add(ModPlacedFeatures.UNAKITE_ORE_PLACED.getHolder().get());
    }
}