package com.gypsyhost.socketcraft.datagen;

import com.gypsyhost.socketcraft.SocketCraft;
import com.gypsyhost.socketcraft.registry.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class SCItemModels extends ItemModelProvider {
    public SCItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, SocketCraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        withExistingParent("titanium_ore", modLoc("block/titanium_ore"));
        withExistingParent("tungsten_ore", modLoc("block/tungsten_ore"));
        withExistingParent("aventurine_ore", modLoc("block/aventurine_ore"));
        withExistingParent("calcite_ore", modLoc("block/calcite_ore"));
        withExistingParent("pyrite_ore", modLoc("block/pyrite_ore"));
        withExistingParent("ruby_ore", modLoc("block/ruby_ore"));
        withExistingParent("topaz_ore", modLoc("block/topaz_ore"));
        withExistingParent("unakite_ore", modLoc("block/unakite_ore"));

        withExistingParent("steel_block", modLoc("block/steel_block"));
        withExistingParent("titanium_block", modLoc("block/titanium_block"));
        withExistingParent("tungsten_block", modLoc("block/tungsten_block"));

        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));


    }
}
