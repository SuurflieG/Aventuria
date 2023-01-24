package com.gypsyhost.aventuria.datagen;

import com.gypsyhost.aventuria.Aventuria;
import com.gypsyhost.aventuria.registry.ModBlocks;
import com.gypsyhost.aventuria.registry.ModTreeBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
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

        logBlock((RotatedPillarBlock) ModTreeBlocks.LARCH_LOG.get());
        axisBlock((RotatedPillarBlock) ModTreeBlocks.LARCH_WOOD.get(), blockTexture(ModTreeBlocks.LARCH_LOG.get()), blockTexture(ModTreeBlocks.LARCH_LOG.get()));
        axisBlock((RotatedPillarBlock) ModTreeBlocks.STRIPPED_LARCH_LOG.get(), new ResourceLocation(Aventuria.MOD_ID, "block/stripped_larch_log"), new ResourceLocation(Aventuria.MOD_ID, "block/stripped_larch_top"));
        axisBlock((RotatedPillarBlock) ModTreeBlocks.STRIPPED_LARCH_WOOD.get(), new ResourceLocation(Aventuria.MOD_ID, "block/stripped_larch_log"), new ResourceLocation(Aventuria.MOD_ID, "block/stripped_larch_top"));

        simpleBlock(ModTreeBlocks.LARCH_PLANKS.get());
        simpleBlock(ModTreeBlocks.LARCH_LEAVES.get());

        simpleBlock(ModTreeBlocks.LARCH_SAPLING.get(), models().cross(ModTreeBlocks.LARCH_SAPLING.get().getRegistryName().getPath(), blockTexture(ModTreeBlocks.LARCH_SAPLING.get())));

    }
}
