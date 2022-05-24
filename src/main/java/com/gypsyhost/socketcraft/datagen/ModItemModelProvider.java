package com.gypsyhost.socketcraft.datagen;

import com.gypsyhost.socketcraft.SocketCraft;
import com.gypsyhost.socketcraft.registry.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, SocketCraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        withExistingParent("steel_block", modLoc("block/steel_block"));
        withExistingParent("titanium_block", modLoc("block/titanium_block"));
        withExistingParent("tungsten_block", modLoc("block/tungsten_block"));

        withExistingParent("aventurine_ore", modLoc("block/aventurine_ore"));
        withExistingParent("calcite_ore", modLoc("block/calcite_ore"));
        withExistingParent("pyrite_ore", modLoc("block/pyrite_ore"));
        withExistingParent("ruby_ore", modLoc("block/ruby_ore"));
        withExistingParent("topaz_ore", modLoc("block/topaz_ore"));
        withExistingParent("unakite_ore", modLoc("block/unakite_ore"));
        withExistingParent("titanium_ore", modLoc("block/titanium_ore"));
        withExistingParent("tungsten_ore", modLoc("block/tungsten_ore"));

        withExistingParent("deepslate_aventurine_ore", modLoc("block/deepslate_aventurine_ore"));
        withExistingParent("deepslate_calcite_ore", modLoc("block/deepslate_calcite_ore"));
        withExistingParent("deepslate_pyrite_ore", modLoc("block/deepslate_pyrite_ore"));
        withExistingParent("deepslate_ruby_ore", modLoc("block/deepslate_ruby_ore"));
        withExistingParent("deepslate_topaz_ore", modLoc("block/deepslate_topaz_ore"));
        withExistingParent("deepslate_unakite_ore", modLoc("block/deepslate_unakite_ore"));
        withExistingParent("deepslate_titanium_ore", modLoc("block/deepslate_titanium_ore"));
        withExistingParent("deepslate_tungsten_ore", modLoc("block/deepslate_tungsten_ore"));


        simpleItem(ModItems.CATALYST.get());

        simpleItem(ModItems.CRAFTING_HAMMER_IRON.get());
        simpleItem(ModItems.CRAFTING_HAMMER_STEEL.get());
        simpleItem(ModItems.CRAFTING_HAMMER_TITANIUM.get());
        simpleItem(ModItems.CRAFTING_HAMMER_TUNGSTEN.get());

        simpleItem(ModItems.IRON_PLATE.get());
        simpleItem(ModItems.REDSTONE_PLATE.get());
        simpleItem(ModItems.STEEL_PLATE.get());
        simpleItem(ModItems.TITANIUM_PLATE.get());
        simpleItem(ModItems.TUNGSTEN_PLATE.get());

        simpleItem(ModItems.STEEL_NUGGET.get());
        simpleItem(ModItems.TITANIUM_NUGGET.get());
        simpleItem(ModItems.TUNGSTEN_NUGGET.get());

        simpleItem(ModItems.STEEL_INGOT.get());
        simpleItem(ModItems.TITANIUM_INGOT.get());
        simpleItem(ModItems.TUNGSTEN_INGOT.get());

        simpleItem(ModItems.STEEL_DUST.get());
        simpleItem(ModItems.TITANIUM_DUST.get());
        simpleItem(ModItems.TUNGSTEN_DUST.get());
        simpleItem(ModItems.COAL_DUST.get());
        simpleItem(ModItems.IRON_DUST.get());
        simpleItem(ModItems.GOLD_DUST.get());

        simpleItem(ModItems.TITANIUM_RAW_ORE.get());
        simpleItem(ModItems.TUNGSTEN_RAW_ORE.get());
        simpleItem(ModItems.GEM_RAW_AVENTURINE.get());
        simpleItem(ModItems.GEM_RAW_CALCITE.get());
        simpleItem(ModItems.GEM_RAW_PYRITE.get());
        simpleItem(ModItems.GEM_RAW_RUBY.get());
        simpleItem(ModItems.GEM_RAW_TOPAZ.get());
        simpleItem(ModItems.GEM_RAW_UNAKITE.get());

        simpleItem(ModItems.GEM_AVENTURINE.get());
        simpleItem(ModItems.GEM_CALCITE.get());
        simpleItem(ModItems.GEM_PYRITE.get());
        simpleItem(ModItems.GEM_RUBY.get());
        simpleItem(ModItems.GEM_TOPAZ.get());
        simpleItem(ModItems.GEM_UNAKITE.get());

        simpleItem(ModArmorItems.STEEL_HELMET.get());
        simpleItem(ModArmorItems.STEEL_CHESTPLATE.get());
        simpleItem(ModArmorItems.STEEL_LEGGINGS.get());
        simpleItem(ModArmorItems.STEEL_BOOTS.get());
        simpleItem(ModArmorItems.TITANIUM_HELMET.get());
        simpleItem(ModArmorItems.TITANIUM_CHESTPLATE.get());
        simpleItem(ModArmorItems.TITANIUM_LEGGINGS.get());
        simpleItem(ModArmorItems.TITANIUM_BOOTS.get());
        simpleItem(ModArmorItems.TUNGSTEN_HELMET.get());
        simpleItem(ModArmorItems.TUNGSTEN_CHESTPLATE.get());
        simpleItem(ModArmorItems.TUNGSTEN_LEGGINGS.get());
        simpleItem(ModArmorItems.TUNGSTEN_BOOTS.get());

        handheldItem(ModToolItems.STEEL_AXE.get());
        handheldItem(ModToolItems.STEEL_SHOVEL.get());
        handheldItem(ModToolItems.STEEL_PICKAXE.get());
        handheldItem(ModToolItems.STEEL_HOE.get());
        handheldItem(ModToolItems.TITANIUM_AXE.get());
        handheldItem(ModToolItems.TITANIUM_SHOVEL.get());
        handheldItem(ModToolItems.TITANIUM_PICKAXE.get());
        handheldItem(ModToolItems.TITANIUM_HOE.get());
        handheldItem(ModToolItems.TUNGSTEN_AXE.get());
        handheldItem(ModToolItems.TUNGSTEN_SHOVEL.get());
        handheldItem(ModToolItems.TUNGSTEN_PICKAXE.get());
        handheldItem(ModToolItems.TUNGSTEN_HOE.get());
        
        handheldItem(ModWeaponItems.STEEL_SWORD.get());
        handheldItem(ModWeaponItems.TITANIUM_SWORD.get());
        handheldItem(ModWeaponItems.TUNGSTEN_SWORD.get());

        simpleItem(ModSocketCards.BLANK.get());
        simpleItem(ModSocketCards.FORTUNE_1.get());
        simpleItem(ModSocketCards.FORTUNE_2.get());
        simpleItem(ModSocketCards.FORTUNE_3.get());
        simpleItem(ModSocketCards.EFFICIENCY_1.get());
        simpleItem(ModSocketCards.EFFICIENCY_2.get());
        simpleItem(ModSocketCards.EFFICIENCY_3.get());
        simpleItem(ModSocketCards.EFFICIENCY_4.get());
        simpleItem(ModSocketCards.EFFICIENCY_5.get());
        simpleItem(ModSocketCards.MAGNET.get());
        simpleItem(ModSocketCards.VOID_JUNK.get());
        simpleItem(ModSocketCards.SILK.get());
        simpleItem(ModSocketCards.THREE_BY_THREE.get());

        simpleItem(ModHandleItems.WOOD.get());

        simpleItem(ModPickaxeHeadItems.WOOD.get());



    }

    private ItemModelBuilder simpleItem(Item item) {
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(SocketCraft.MOD_ID,"item/" + item.getRegistryName().getPath()));
    }

    private ItemModelBuilder handheldItem(Item item) {
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(SocketCraft.MOD_ID,"item/" + item.getRegistryName().getPath()));
    }
}
