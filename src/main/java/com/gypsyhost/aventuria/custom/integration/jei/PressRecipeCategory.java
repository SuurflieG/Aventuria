package com.gypsyhost.aventuria.custom.integration.jei;

import com.gypsyhost.aventuria.Aventuria;
import com.gypsyhost.aventuria.custom.recipe.PressRecipe;
import com.gypsyhost.aventuria.registry.ModBlocks;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

public class PressRecipeCategory implements IRecipeCategory<PressRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(Aventuria.MOD_ID, "pressing");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(Aventuria.MOD_ID, "textures/gui/press_gui.png");

    private final IDrawable background;
    private final IDrawable icon;

    public PressRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 80);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM, new ItemStack(ModBlocks.PRESS.get()));
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends PressRecipe> getRecipeClass() {
        return PressRecipe.class;
    }

    @Override
    public Component getTitle() {
        return new TextComponent("Press");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull PressRecipe pressRecipes, @Nonnull IFocusGroup focusGroup) {
        builder.addSlot(RecipeIngredientRole.INPUT, 79, 18).addIngredients(pressRecipes.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 79, 52).addIngredients(pressRecipes.getIngredients().get(1));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 148, 36).addItemStack(pressRecipes.getResultItem());
    }
}