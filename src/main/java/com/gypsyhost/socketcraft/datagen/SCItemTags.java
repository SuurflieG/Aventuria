package com.gypsyhost.socketcraft.datagen;

import com.gypsyhost.socketcraft.SocketCraft;
import com.gypsyhost.socketcraft.registry.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class SCItemTags extends ItemTagsProvider {

    public SCItemTags(DataGenerator pGenerator, BlockTagsProvider pBlockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, pBlockTagsProvider, SocketCraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(Tags.Items.DUSTS)
                .add(ModItems.COAL_DUST.get())
                .add(ModItems.IRON_DUST.get())
                .add(ModItems.GOLD_DUST.get())
                .add(ModItems.STEEL_DUST.get())
                .add(ModItems.TITANIUM_DUST.get())
                .add(ModItems.TUNGSTEN_DUST.get());
        tag(Tags.Items.GEMS)
                .add(ModItems.GEM_RAW_AVENTURINE.get())
                .add(ModItems.GEM_RAW_CALCITE.get())
                .add(ModItems.GEM_RAW_PYRITE.get())
                .add(ModItems.GEM_RAW_RUBY.get())
                .add(ModItems.GEM_RAW_TOPAZ.get())
                .add(ModItems.GEM_RAW_UNAKITE.get());
        tag(Tags.Items.INGOTS)
                .add(ModItems.STEEL_INGOT.get())
                .add(ModItems.TITANIUM_INGOT.get())
                .add(ModItems.TUNGSTEN_INGOT.get());
        tag(Tags.Items.NUGGETS)
                .add(ModItems.STEEL_NUGGET.get())
                .add(ModItems.TITANIUM_NUGGET.get())
                .add(ModItems.TUNGSTEN_NUGGET.get());
    }

    @Override
    public String getName() {
        return "SocketCraft Tags";
    }
}
