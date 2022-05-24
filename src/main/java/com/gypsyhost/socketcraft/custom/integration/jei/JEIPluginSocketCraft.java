package com.gypsyhost.socketcraft.custom.integration.jei;

import com.gypsyhost.socketcraft.SocketCraft;
import com.gypsyhost.socketcraft.custom.recipe.CatalyzerRecipe;
import com.gypsyhost.socketcraft.custom.recipe.PressRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class JEIPluginSocketCraft implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(SocketCraft.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new PressRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new CatalyzerRecipeCategory(registration.getJeiHelpers().getGuiHelper()));

    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        List<PressRecipe> pressRecipes = rm.getAllRecipesFor(PressRecipe.Type.INSTANCE);
        registration.addRecipes(new RecipeType<>(PressRecipeCategory.UID, PressRecipe.class), pressRecipes);

        List<CatalyzerRecipe> catalyzerRecipes = rm.getAllRecipesFor(CatalyzerRecipe.Type.INSTANCE);
        registration.addRecipes(new RecipeType<>(CatalyzerRecipeCategory.UID, CatalyzerRecipe.class), catalyzerRecipes);
    }
}
