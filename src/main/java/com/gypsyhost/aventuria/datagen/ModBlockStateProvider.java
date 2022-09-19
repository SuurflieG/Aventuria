package com.gypsyhost.aventuria.datagen;

import com.gypsyhost.aventuria.Aventuria;
import com.gypsyhost.aventuria.registry.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Aventuria.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.TITANIUM_BLOCK.get());

        simpleBlock(ModBlocks.TITANIUM_ORE.get());

        simpleBlock(ModBlocks.DEEPSLATE_TITANIUM_ORE.get());

        simpleBlock(ModBlocks.AVENTURINE_BLOCK.get());
        simpleBlock(ModBlocks.BUDDING_AVENTURINE.get());
        simpleBlock(ModBlocks.PYRITE_BLOCK.get());
        simpleBlock(ModBlocks.BUDDING_PYRITE.get());
        simpleBlock(ModBlocks.RUBY_BLOCK.get());
        simpleBlock(ModBlocks.BUDDING_RUBY.get());
        simpleBlock(ModBlocks.TOPAZ_BLOCK.get());
        simpleBlock(ModBlocks.BUDDING_TOPAZ.get());
        simpleBlock(ModBlocks.SPECTROLITE_BLOCK.get());
        simpleBlock(ModBlocks.BUDDING_SPECTROLITE.get());
        simpleBlock(ModBlocks.UNAKITE_BLOCK.get());
        simpleBlock(ModBlocks.BUDDING_UNAKITE.get());

        horizontalBlock(ModBlocks.UPGRADE_STATION.get(), new ModelFile.UncheckedModelFile(modLoc("block/upgrade_station")));
        horizontalBlock(ModBlocks.CATALYZER.get(), new ModelFile.UncheckedModelFile(modLoc("block/catalyzer")));

    }
}
