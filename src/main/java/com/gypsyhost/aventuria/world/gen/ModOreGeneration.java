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
        base.add(ModPlacedFeatures.AVENTURINE_GEODE_PLACED.getHolder().get());
        base.add(ModPlacedFeatures.PYRITE_GEODE_PLACED.getHolder().get());
        base.add(ModPlacedFeatures.RUBY_GEODE_PLACED.getHolder().get());
        base.add(ModPlacedFeatures.TOPAZ_GEODE_PLACED.getHolder().get());
        base.add(ModPlacedFeatures.SPECTROLITE_GEODE_PLACED.getHolder().get());
        base.add(ModPlacedFeatures.UNAKITE_GEODE_PLACED.getHolder().get());

        base.add(ModPlacedFeatures.DEEPSLATE_TITANIUM_ORE_PLACED.getHolder().get());
    }
}