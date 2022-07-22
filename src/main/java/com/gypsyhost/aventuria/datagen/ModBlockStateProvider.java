package com.gypsyhost.aventuria.datagen;

import com.gypsyhost.aventuria.Aventuria;
import com.gypsyhost.aventuria.registry.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Aventuria.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.STEEL_BLOCK.get());
        simpleBlock(ModBlocks.TITANIUM_BLOCK.get());
        simpleBlock(ModBlocks.TUNGSTEN_BLOCK.get());

        simpleBlock(ModBlocks.AVENTURINE_ORE.get());
        simpleBlock(ModBlocks.CALCITE_ORE.get());
        simpleBlock(ModBlocks.PYRITE_ORE.get());
        simpleBlock(ModBlocks.RUBY_ORE.get());
        simpleBlock(ModBlocks.TOPAZ_ORE.get());
        simpleBlock(ModBlocks.UNAKITE_ORE.get());
        simpleBlock(ModBlocks.TITANIUM_ORE.get());
        simpleBlock(ModBlocks.TUNGSTEN_ORE.get());

        simpleBlock(ModBlocks.DEEPSLATE_AVENTURINE_ORE.get());
        simpleBlock(ModBlocks.DEEPSLATE_CALCITE_ORE.get());
        simpleBlock(ModBlocks.DEEPSLATE_PYRITE_ORE.get());
        simpleBlock(ModBlocks.DEEPSLATE_RUBY_ORE.get());
        simpleBlock(ModBlocks.DEEPSLATE_TOPAZ_ORE.get());
        simpleBlock(ModBlocks.DEEPSLATE_UNAKITE_ORE.get());
        simpleBlock(ModBlocks.DEEPSLATE_TITANIUM_ORE.get());
        simpleBlock(ModBlocks.DEEPSLATE_TUNGSTEN_ORE.get());

        horizontalBlock(ModBlocks.UPGRADE_STATION.get(), new ModelFile.UncheckedModelFile(modLoc("block/upgrade_station")));
        horizontalBlock(ModBlocks.CATALYZER.get(), new ModelFile.UncheckedModelFile(modLoc("block/catalyzer")));
        horizontalBlock(ModBlocks.BASIC_PHOTON_PANEL.get(), new ModelFile.UncheckedModelFile(modLoc("block/basic_photon_panel")));




    }
}
