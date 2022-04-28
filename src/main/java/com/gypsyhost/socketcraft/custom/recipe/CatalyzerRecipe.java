package com.gypsyhost.socketcraft.custom.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.gypsyhost.socketcraft.SocketCraft;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;

import javax.annotation.Nullable;

public class CatalyzerRecipe implements Recipe<SimpleContainer> {


    private static final int INPUT_SLOT_A = 1;
    private static final int INPUT_SLOT_B = 2;
    private static final int INPUT_SLOT_C = 3;
    private static final int INPUT_SLOT_D = 4;
    private static final int CATALYZER_SLOT = 5;

    private final ResourceLocation id;
    private final ItemStack output;
    private final int maxProgress;
    private final NonNullList<Ingredient> recipeItems;

    public CatalyzerRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems, int maxProgress) {
        this.id = id;
        this.output = output;
        this.maxProgress = maxProgress;
        this.recipeItems = recipeItems;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, net.minecraft.world.level.Level pLevel) {
        if(recipeItems.get(0).test(pContainer.getItem(INPUT_SLOT_A)) && recipeItems.get(1).test(pContainer.getItem(INPUT_SLOT_B))
                && recipeItems.get(2).test(pContainer.getItem(INPUT_SLOT_C)) && recipeItems.get(3).test(pContainer.getItem(INPUT_SLOT_D))) {
            return recipeItems.get(4).test(pContainer.getItem(CATALYZER_SLOT));
        }

        return false;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return recipeItems;
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer) {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return output.copy();
    }

    public int getMaxProgress() {
        return this.maxProgress;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<CatalyzerRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "catalyzing";
    }

    public static class Serializer implements RecipeSerializer<CatalyzerRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(SocketCraft.MOD_ID,"catalyzing");

        @Override
        public CatalyzerRecipe fromJson(ResourceLocation id, JsonObject json) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));
            int maxProgress = GsonHelper.getAsInt(json, "crafttime");
            JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(5, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new CatalyzerRecipe(id, output, inputs, maxProgress);
        }

        @Override
        public CatalyzerRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buf));
            }

            ItemStack output = buf.readItem();
            int maxProgress = buf.readInt();
            return new CatalyzerRecipe(id, output, inputs, maxProgress);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, CatalyzerRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }
            buf.writeItemStack(recipe.getResultItem(), false);
            buf.writeVarInt(recipe.getMaxProgress());
        }

        @Override
        public RecipeSerializer<?> setRegistryName(ResourceLocation name) {
            return INSTANCE;
        }

        @Nullable
        @Override
        public ResourceLocation getRegistryName() {
            return ID;
        }

        @Override
        public Class<RecipeSerializer<?>> getRegistryType() {
            return Serializer.castClass(RecipeSerializer.class);
        }

        @SuppressWarnings("unchecked") // Need this wrapper, because generics
        private static <G> Class<G> castClass(Class<?> cls) {
            return (Class<G>)cls;
        }
    }
}