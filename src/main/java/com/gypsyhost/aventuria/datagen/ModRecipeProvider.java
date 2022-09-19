package com.gypsyhost.aventuria.datagen;

import com.gypsyhost.aventuria.datagen.custom.CatalyzerRecipeBuilder;
import com.gypsyhost.aventuria.registry.ModBlocks;
import com.gypsyhost.aventuria.registry.ModItems;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {

        //region Shaped Ingot to Block Recipes

        ShapedRecipeBuilder.shaped(ModBlocks.TITANIUM_BLOCK.get())
                .define('T', ModItems.TITANIUM_INGOT.get())
                .pattern("TTT")
                .pattern("TTT")
                .pattern("TTT")
                .unlockedBy("has_titanium_ingot", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.TITANIUM_INGOT.get()).build()))
                .save(pFinishedRecipeConsumer);

        //endregion

        //region Block to Ingot Recipes

        ShapelessRecipeBuilder.shapeless(ModItems.TITANIUM_INGOT.get(), 9)
                .requires(ModBlocks.TITANIUM_BLOCK.get())
                .unlockedBy("has_titanium_block", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.TITANIUM_BLOCK.get()).build()))
                .save(pFinishedRecipeConsumer);

        //endregion

        //region Ingot to Nugget Recipes

        ShapelessRecipeBuilder.shapeless(ModItems.TITANIUM_NUGGET.get(), 9)
                .requires(ModItems.TITANIUM_INGOT.get())
                .unlockedBy("has_titanium_ingot", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.TITANIUM_INGOT.get()).build()))
                .save(pFinishedRecipeConsumer);

        //endregion

        //region shards to blocks
        ShapedRecipeBuilder.shaped(ModBlocks.AVENTURINE_BLOCK.get())
                .define('S', ModItems.AVENTURINE_SHARD.get())
                .pattern("SS ")
                .pattern("SS ")
                .pattern("   ")
                .unlockedBy("has_aventurine_block", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.AVENTURINE_BLOCK.get()).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(ModBlocks.PYRITE_BLOCK.get())
                .define('S', ModItems.PYRITE_SHARD.get())
                .pattern("SS ")
                .pattern("SS ")
                .pattern("   ")
                .unlockedBy("has_pyrite_block", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.PYRITE_BLOCK.get()).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(ModBlocks.RUBY_BLOCK.get())
                .define('S', ModItems.RUBY_SHARD.get())
                .pattern("SS ")
                .pattern("SS ")
                .pattern("   ")
                .unlockedBy("has_ruby_block", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.RUBY_BLOCK.get()).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(ModBlocks.SPECTROLITE_BLOCK.get())
                .define('S', ModItems.SPECTROLITE_SHARD.get())
                .pattern("SS ")
                .pattern("SS ")
                .pattern("   ")
                .unlockedBy("has_spectrolite_block", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.SPECTROLITE_BLOCK.get()).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(ModBlocks.TOPAZ_BLOCK.get())
                .define('S', ModItems.TOPAZ_SHARD.get())
                .pattern("SS ")
                .pattern("SS ")
                .pattern("   ")
                .unlockedBy("has_topaz_block", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.TOPAZ_BLOCK.get()).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(ModBlocks.UNAKITE_BLOCK.get())
                .define('S', ModItems.UNAKITE_SHARD.get())
                .pattern("SS ")
                .pattern("SS ")
                .pattern("   ")
                .unlockedBy("has_unakite_block", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.UNAKITE_BLOCK.get()).build()))
                .save(pFinishedRecipeConsumer);


        //region Catalyzer Recipes
/*        new CatalyzerRecipeBuilder(ModItems.GEM_RAW_AVENTURINE.get(), ModItems.GEM_RAW_AVENTURINE.get(), ModItems.GEM_RAW_AVENTURINE.get(), ModItems.GEM_RAW_AVENTURINE.get(), ModItems.CATALYST.get(), ModItems.GEM_AVENTURINE.get(), 1,80)
                .unlockedBy("has_gem_raw_aventurine", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.GEM_RAW_AVENTURINE.get()).build())).save(pFinishedRecipeConsumer);
        new CatalyzerRecipeBuilder(ModItems.GEM_RAW_CALCITE.get(), ModItems.GEM_RAW_CALCITE.get(), ModItems.GEM_RAW_CALCITE.get(), ModItems.GEM_RAW_CALCITE.get(), ModItems.CATALYST.get(), ModItems.GEM_CALCITE.get(), 1,80)
                .unlockedBy("has_gem_raw_aventurine", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.GEM_RAW_CALCITE.get()).build())).save(pFinishedRecipeConsumer);
        new CatalyzerRecipeBuilder(ModItems.GEM_RAW_PYRITE.get(), ModItems.GEM_RAW_PYRITE.get(), ModItems.GEM_RAW_PYRITE.get(), ModItems.GEM_RAW_PYRITE.get(), ModItems.CATALYST.get(), ModItems.GEM_PYRITE.get(), 1,80)
                .unlockedBy("has_gem_raw_aventurine", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.GEM_RAW_PYRITE.get()).build())).save(pFinishedRecipeConsumer);
        new CatalyzerRecipeBuilder(ModItems.GEM_RAW_RUBY.get(), ModItems.GEM_RAW_RUBY.get(), ModItems.GEM_RAW_RUBY.get(), ModItems.GEM_RAW_RUBY.get(), ModItems.CATALYST.get(), ModItems.GEM_RUBY.get(), 1,80)
                .unlockedBy("has_gem_raw_aventurine", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.GEM_RAW_RUBY.get()).build())).save(pFinishedRecipeConsumer);
        new CatalyzerRecipeBuilder(ModItems.GEM_RAW_TOPAZ.get(), ModItems.GEM_RAW_TOPAZ.get(), ModItems.GEM_RAW_TOPAZ.get(), ModItems.GEM_RAW_TOPAZ.get(), ModItems.CATALYST.get(), ModItems.GEM_TOPAZ.get(), 1,80)
                .unlockedBy("has_gem_raw_aventurine", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.GEM_RAW_TOPAZ.get()).build())).save(pFinishedRecipeConsumer);
        new CatalyzerRecipeBuilder(ModItems.GEM_RAW_UNAKITE.get(), ModItems.GEM_RAW_UNAKITE.get(), ModItems.GEM_RAW_UNAKITE.get(), ModItems.GEM_RAW_UNAKITE.get(), ModItems.CATALYST.get(), ModItems.GEM_UNAKITE.get(), 1,80)
                .unlockedBy("has_gem_raw_aventurine", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.GEM_RAW_UNAKITE.get()).build())).save(pFinishedRecipeConsumer);*/
        //endregion


    }
}
