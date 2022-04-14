package com.gypsyhost.socketcraft.datagen;

import com.gypsyhost.socketcraft.SocketCraft;
import com.gypsyhost.socketcraft.registry.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class SCBlockTags extends BlockTagsProvider {
    public SCBlockTags(DataGenerator pGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, SocketCraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.TITANIUM_ORE.get())
                .add(ModBlocks.TUNGSTEN_ORE.get())
                .add(ModBlocks.AVENTURINE_ORE.get())
                .add(ModBlocks.CALCITE_ORE.get())
                .add(ModBlocks.PYRITE_ORE.get())
                .add(ModBlocks.RUBY_ORE.get())
                .add(ModBlocks.TOPAZ_ORE.get())
                .add(ModBlocks.UNAKITE_ORE.get())
                .add(ModBlocks.STEEL_BLOCK.get())
                .add(ModBlocks.TITANIUM_BLOCK.get())
                .add(ModBlocks.TUNGSTEN_BLOCK.get());
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.AVENTURINE_ORE.get())
                .add(ModBlocks.CALCITE_ORE.get())
                .add(ModBlocks.PYRITE_ORE.get())
                .add(ModBlocks.RUBY_ORE.get())
                .add(ModBlocks.TOPAZ_ORE.get())
                .add(ModBlocks.UNAKITE_ORE.get());
        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.TITANIUM_ORE.get())
                .add(ModBlocks.TUNGSTEN_ORE.get())
                .add(ModBlocks.STEEL_BLOCK.get())
                .add(ModBlocks.TITANIUM_BLOCK.get())
                .add(ModBlocks.TUNGSTEN_BLOCK.get());
        tag(Tags.Blocks.ORES)
                .add(ModBlocks.TITANIUM_ORE.get())
                .add(ModBlocks.TUNGSTEN_ORE.get())
                .add(ModBlocks.AVENTURINE_ORE.get())
                .add(ModBlocks.CALCITE_ORE.get())
                .add(ModBlocks.PYRITE_ORE.get())
                .add(ModBlocks.RUBY_ORE.get())
                .add(ModBlocks.TOPAZ_ORE.get())
                .add(ModBlocks.UNAKITE_ORE.get());
        tag(Tags.Blocks.STORAGE_BLOCKS)
                .add(ModBlocks.STEEL_BLOCK.get())
                .add(ModBlocks.TITANIUM_BLOCK.get())
                .add(ModBlocks.TUNGSTEN_BLOCK.get());
    }

    @Override
    public String getName() {
        return "SocketCraft Tags";
    }
}
