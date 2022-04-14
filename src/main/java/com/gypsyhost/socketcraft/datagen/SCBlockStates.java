package com.gypsyhost.socketcraft.datagen;

import com.gypsyhost.socketcraft.SocketCraft;
import com.gypsyhost.socketcraft.registry.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class SCBlockStates extends BlockStateProvider {


    public SCBlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, SocketCraft.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.TITANIUM_ORE.get());
        simpleBlock(ModBlocks.TUNGSTEN_ORE.get());
        simpleBlock(ModBlocks.AVENTURINE_ORE.get());
        simpleBlock(ModBlocks.CALCITE_ORE.get());
        simpleBlock(ModBlocks.PYRITE_ORE.get());
        simpleBlock(ModBlocks.RUBY_ORE.get());
        simpleBlock(ModBlocks.TOPAZ_ORE.get());
        simpleBlock(ModBlocks.UNAKITE_ORE.get());
        simpleBlock(ModBlocks.STEEL_BLOCK.get());
        simpleBlock(ModBlocks.TITANIUM_BLOCK.get());
        simpleBlock(ModBlocks.TUNGSTEN_BLOCK.get());

    }
}
