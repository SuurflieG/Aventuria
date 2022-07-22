package com.gypsyhost.aventuria.datagen.loot;

import com.gypsyhost.aventuria.registry.ModBlocks;
import com.gypsyhost.aventuria.registry.ModItems;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockLootTables extends BlockLoot {

    @Override
    protected void addTables() {
        //region Blocks that drop them self
        this.dropSelf(ModBlocks.STEEL_BLOCK.get());
        this.dropSelf(ModBlocks.TITANIUM_BLOCK.get());
        this.dropSelf(ModBlocks.TUNGSTEN_BLOCK.get());
        this.dropSelf(ModBlocks.CATALYZER.get());
        this.dropSelf(ModBlocks.PRESS.get());
        this.dropSelf(ModBlocks.MACHINE_CASING.get());
        this.dropSelf(ModBlocks.UPGRADE_STATION.get());
        this.dropSelf(ModBlocks.ELECTRIC_RAIL.get());
        this.dropSelf(ModBlocks.BASIC_PHOTON_PANEL.get());
        //endregion

        //region Ore Blocks that drop raw ores
        this.add(ModBlocks.AVENTURINE_ORE.get(), (Block) -> createOreDrop(ModBlocks.AVENTURINE_ORE.get(), ModItems.GEM_RAW_AVENTURINE.get()));
        this.add(ModBlocks.CALCITE_ORE.get(), (Block) -> createOreDrop(ModBlocks.CALCITE_ORE.get(), ModItems.GEM_RAW_CALCITE.get()));
        this.add(ModBlocks.PYRITE_ORE.get(), (Block) -> createOreDrop(ModBlocks.PYRITE_ORE.get(), ModItems.GEM_RAW_PYRITE.get()));
        this.add(ModBlocks.RUBY_ORE.get(), (Block) -> createOreDrop(ModBlocks.RUBY_ORE.get(), ModItems.GEM_RAW_RUBY.get()));
        this.add(ModBlocks.TOPAZ_ORE.get(), (Block) -> createOreDrop(ModBlocks.TOPAZ_ORE.get(), ModItems.GEM_RAW_TOPAZ.get()));
        this.add(ModBlocks.UNAKITE_ORE.get(), (Block) -> createOreDrop(ModBlocks.UNAKITE_ORE.get(), ModItems.GEM_RAW_UNAKITE.get()));
        this.add(ModBlocks.TITANIUM_ORE.get(), (Block) -> createOreDrop(ModBlocks.TITANIUM_ORE.get(), ModItems.TITANIUM_RAW_ORE.get()));
        this.add(ModBlocks.TUNGSTEN_ORE.get(), (Block) -> createOreDrop(ModBlocks.TUNGSTEN_ORE.get(), ModItems.TUNGSTEN_RAW_ORE.get()));

        this.add(ModBlocks.DEEPSLATE_AVENTURINE_ORE.get(), (Block) -> createOreDrop(ModBlocks.DEEPSLATE_AVENTURINE_ORE.get(), ModItems.GEM_RAW_AVENTURINE.get()));
        this.add(ModBlocks.DEEPSLATE_CALCITE_ORE.get(), (Block) -> createOreDrop(ModBlocks.DEEPSLATE_CALCITE_ORE.get(), ModItems.GEM_RAW_CALCITE.get()));
        this.add(ModBlocks.DEEPSLATE_PYRITE_ORE.get(), (Block) -> createOreDrop(ModBlocks.DEEPSLATE_PYRITE_ORE.get(), ModItems.GEM_RAW_PYRITE.get()));
        this.add(ModBlocks.DEEPSLATE_RUBY_ORE.get(), (Block) -> createOreDrop(ModBlocks.DEEPSLATE_RUBY_ORE.get(), ModItems.GEM_RAW_RUBY.get()));
        this.add(ModBlocks.DEEPSLATE_TOPAZ_ORE.get(), (Block) -> createOreDrop(ModBlocks.DEEPSLATE_TOPAZ_ORE.get(), ModItems.GEM_RAW_TOPAZ.get()));
        this.add(ModBlocks.DEEPSLATE_UNAKITE_ORE.get(), (Block) -> createOreDrop(ModBlocks.DEEPSLATE_UNAKITE_ORE.get(), ModItems.GEM_RAW_UNAKITE.get()));
        this.add(ModBlocks.DEEPSLATE_TITANIUM_ORE.get(), (Block) -> createOreDrop(ModBlocks.DEEPSLATE_TITANIUM_ORE.get(), ModItems.TITANIUM_RAW_ORE.get()));
        this.add(ModBlocks.DEEPSLATE_TUNGSTEN_ORE.get(), (Block) -> createOreDrop(ModBlocks.DEEPSLATE_TUNGSTEN_ORE.get(), ModItems.TUNGSTEN_RAW_ORE.get()));
        //endregion


    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
