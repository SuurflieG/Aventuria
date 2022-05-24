package com.gypsyhost.socketcraft.datagen.custom;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.gypsyhost.socketcraft.SocketCraft;
import com.gypsyhost.socketcraft.custom.recipe.CatalyzerRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class CatalyzerRecipeBuilder implements RecipeBuilder {

    private final Item result;
    private final Ingredient gemRaw1;
    private final Ingredient gemRaw2;
    private final Ingredient gemRaw3;
    private final Ingredient gemRaw4;
    private final Ingredient catalyst;
    private final int count;
    private final int maxProgress;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();

    public CatalyzerRecipeBuilder(ItemLike gemRaw1, ItemLike gemRaw2, ItemLike gemRaw3, ItemLike gemRaw4, ItemLike catalyst, ItemLike result, int count, int maxProgress) {
        this.gemRaw1 = Ingredient.of(gemRaw1);
        this.gemRaw2 = Ingredient.of(gemRaw2);
        this.gemRaw3 = Ingredient.of(gemRaw3);
        this.gemRaw4 = Ingredient.of(gemRaw4);
        this.catalyst = Ingredient.of(catalyst);
        this.result = result.asItem();
        this.count = count;
        this.maxProgress = maxProgress;
    }



    @Override
    public RecipeBuilder unlockedBy(String pCriterionName, CriterionTriggerInstance pCriterionTrigger) {
        this.advancement.addCriterion(pCriterionName, pCriterionTrigger);
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String pGroupName) {
        return this;
    }

    @Override
    public Item getResult() {
        return result;
    }

    @Override
    public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId) {
        this.advancement.parent(new ResourceLocation("recipes/root"))
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(pRecipeId))
                .rewards(AdvancementRewards.Builder.recipe(pRecipeId)).requirements(RequirementsStrategy.OR);

        pFinishedRecipeConsumer.accept(new CatalyzerRecipeBuilder.Result(pRecipeId, this.result, this.count, this.maxProgress, this.gemRaw1, this.gemRaw2, this.gemRaw3, this.gemRaw4, this.catalyst,
                this.advancement, new ResourceLocation(pRecipeId.getNamespace(), "recipes/" +
                this.result.getItemCategory().getRecipeFolderName() + "/" + pRecipeId.getPath())));

    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final Item result;
        private final Ingredient gemRaw1;
        private final Ingredient gemRaw2;
        private final Ingredient gemRaw3;
        private final Ingredient gemRaw4;
        private final Ingredient catalyst;
        private final int count;
        private final int maxProgress;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation pId, Item pResult, int pCount, int maxProgress, Ingredient gemRaw1, Ingredient gemRaw2, Ingredient gemRaw3, Ingredient gemRaw4, Ingredient catalyst, Advancement.Builder pAdvancement,
                      ResourceLocation pAdvancementId) {
            this.id = pId;
            this.result = pResult;
            this.count = pCount;
            this.maxProgress = maxProgress;
            this.gemRaw1 = gemRaw1;
            this.gemRaw2 = gemRaw2;
            this.gemRaw3 = gemRaw3;
            this.gemRaw4 = gemRaw4;
            this.catalyst = catalyst;
            this.advancement = pAdvancement;
            this.advancementId = pAdvancementId;
        }

        @Override
        public void serializeRecipeData(JsonObject pJson) {
            JsonArray jsonarray = new JsonArray();
            jsonarray.add(gemRaw1.toJson());
            jsonarray.add(gemRaw2.toJson());
            jsonarray.add(gemRaw3.toJson());
            jsonarray.add(gemRaw4.toJson());
            jsonarray.add(catalyst.toJson());

            pJson.add("ingredients", jsonarray);
            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("item", this.result.getRegistryName().toString());
            if (this.count > 1) {
                jsonobject.addProperty("count", this.count);
            }

            pJson.add("output", jsonobject);

            pJson.addProperty("crafttime", this.maxProgress);
        }

        @Override
        public ResourceLocation getId() {
            return new ResourceLocation(SocketCraft.MOD_ID, this.result.getRegistryName().getPath() + "_from_catalyzer");
        }

        @Override
        public RecipeSerializer<?> getType() {
            return CatalyzerRecipe.Serializer.INSTANCE;
        }

        @javax.annotation.Nullable
        public JsonObject serializeAdvancement() {
            return this.advancement.serializeToJson();
        }

        @javax.annotation.Nullable
        public ResourceLocation getAdvancementId() {
            return this.advancementId;
        }
    }
}
