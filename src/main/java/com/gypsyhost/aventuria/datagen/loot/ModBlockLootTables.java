package com.gypsyhost.aventuria.datagen.loot;

import com.gypsyhost.aventuria.registry.ModBlocks;
import com.gypsyhost.aventuria.registry.ModItems;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockLootTables extends BlockLoot {

    @Override
    protected void addTables() {
        //region Blocks that drop them self
        this.dropSelf(ModBlocks.TITANIUM_BLOCK.get());
        this.dropSelf(ModBlocks.CATALYZER.get());
        this.dropSelf(ModBlocks.UPGRADE_STATION.get());


        //endregion

        //region Ore Blocks that drop raw ores
        this.add(ModBlocks.TITANIUM_ORE.get(), (Block) -> createOreDrop(ModBlocks.TITANIUM_ORE.get(), ModItems.TITANIUM_RAW_ORE.get()));
        this.add(ModBlocks.DEEPSLATE_TITANIUM_ORE.get(), (Block) -> createOreDrop(ModBlocks.DEEPSLATE_TITANIUM_ORE.get(), ModItems.TITANIUM_RAW_ORE.get()));
        //endregion



        this.dropSelf(ModBlocks.AVENTURINE_BLOCK.get());
        this.add(ModBlocks.AVENTURINE_CLUSTER.get(), (p_176063_) -> createSilkTouchDispatchTable(p_176063_, LootItem.lootTableItem(ModItems.AVENTURINE_SHARD.get())
                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0F)))
                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE)).when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.CLUSTER_MAX_HARVESTABLES)))
                .otherwise(applyExplosionDecay(p_176063_, LootItem.lootTableItem(ModItems.AVENTURINE_SHARD.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)))))));
        this.dropWhenSilkTouch(ModBlocks.SMALL_AVENTURINE_BUD.get());
        this.dropWhenSilkTouch(ModBlocks.MEDIUM_AVENTURINE_BUD.get());
        this.dropWhenSilkTouch(ModBlocks.LARGE_AVENTURINE_BUD.get());
        this.add(ModBlocks.BUDDING_AVENTURINE.get(), noDrop());

        this.dropSelf(ModBlocks.PYRITE_BLOCK.get());
        this.add(ModBlocks.PYRITE_CLUSTER.get(), (p_176063_) -> createSilkTouchDispatchTable(p_176063_, LootItem.lootTableItem(ModItems.PYRITE_SHARD.get())
                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0F)))
                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE)).when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.CLUSTER_MAX_HARVESTABLES)))
                .otherwise(applyExplosionDecay(p_176063_, LootItem.lootTableItem(ModItems.PYRITE_SHARD.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)))))));
        this.dropWhenSilkTouch(ModBlocks.SMALL_PYRITE_BUD.get());
        this.dropWhenSilkTouch(ModBlocks.MEDIUM_PYRITE_BUD.get());
        this.dropWhenSilkTouch(ModBlocks.LARGE_PYRITE_BUD.get());
        this.add(ModBlocks.BUDDING_PYRITE.get(), noDrop());

        this.dropSelf(ModBlocks.RUBY_BLOCK.get());
        this.add(ModBlocks.RUBY_CLUSTER.get(), (p_176063_) -> createSilkTouchDispatchTable(p_176063_, LootItem.lootTableItem(ModItems.RUBY_SHARD.get())
                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0F)))
                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE)).when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.CLUSTER_MAX_HARVESTABLES)))
                .otherwise(applyExplosionDecay(p_176063_, LootItem.lootTableItem(ModItems.RUBY_SHARD.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)))))));
        this.dropWhenSilkTouch(ModBlocks.SMALL_RUBY_BUD.get());
        this.dropWhenSilkTouch(ModBlocks.MEDIUM_RUBY_BUD.get());
        this.dropWhenSilkTouch(ModBlocks.LARGE_RUBY_BUD.get());
        this.add(ModBlocks.BUDDING_RUBY.get(), noDrop());

        this.dropSelf(ModBlocks.TOPAZ_BLOCK.get());
        this.add(ModBlocks.TOPAZ_CLUSTER.get(), (p_176063_) -> createSilkTouchDispatchTable(p_176063_, LootItem.lootTableItem(ModItems.TOPAZ_SHARD.get())
                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0F)))
                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE)).when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.CLUSTER_MAX_HARVESTABLES)))
                .otherwise(applyExplosionDecay(p_176063_, LootItem.lootTableItem(ModItems.TOPAZ_SHARD.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)))))));
        this.dropWhenSilkTouch(ModBlocks.SMALL_TOPAZ_BUD.get());
        this.dropWhenSilkTouch(ModBlocks.MEDIUM_TOPAZ_BUD.get());
        this.dropWhenSilkTouch(ModBlocks.LARGE_TOPAZ_BUD.get());
        this.add(ModBlocks.BUDDING_TOPAZ.get(), noDrop());

        this.dropSelf(ModBlocks.SPECTROLITE_BLOCK.get());
        this.add(ModBlocks.SPECTROLITE_CLUSTER.get(), (p_176063_) -> createSilkTouchDispatchTable(p_176063_, LootItem.lootTableItem(ModItems.SPECTROLITE_SHARD.get())
                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0F)))
                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE)).when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.CLUSTER_MAX_HARVESTABLES)))
                .otherwise(applyExplosionDecay(p_176063_, LootItem.lootTableItem(ModItems.SPECTROLITE_SHARD.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)))))));
        this.dropWhenSilkTouch(ModBlocks.SMALL_SPECTROLITE_BUD.get());
        this.dropWhenSilkTouch(ModBlocks.MEDIUM_SPECTROLITE_BUD.get());
        this.dropWhenSilkTouch(ModBlocks.LARGE_SPECTROLITE_BUD.get());
        this.add(ModBlocks.BUDDING_SPECTROLITE.get(), noDrop());

        this.dropSelf(ModBlocks.UNAKITE_BLOCK.get());
        this.add(ModBlocks.UNAKITE_CLUSTER.get(), (p_176063_) -> createSilkTouchDispatchTable(p_176063_, LootItem.lootTableItem(ModItems.UNAKITE_SHARD.get())
                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0F)))
                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE)).when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.CLUSTER_MAX_HARVESTABLES)))
                .otherwise(applyExplosionDecay(p_176063_, LootItem.lootTableItem(ModItems.UNAKITE_SHARD.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)))))));
        this.dropWhenSilkTouch(ModBlocks.SMALL_UNAKITE_BUD.get());
        this.dropWhenSilkTouch(ModBlocks.MEDIUM_UNAKITE_BUD.get());
        this.dropWhenSilkTouch(ModBlocks.LARGE_UNAKITE_BUD.get());
        this.add(ModBlocks.BUDDING_UNAKITE.get(), noDrop());


    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
