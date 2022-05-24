package com.gypsyhost.socketcraft.datagen;

import com.gypsyhost.socketcraft.SocketCraft;
import com.gypsyhost.socketcraft.registry.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, SocketCraft.MOD_ID, existingFileHelper);
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


    }
}
