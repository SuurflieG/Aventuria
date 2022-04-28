package com.gypsyhost.socketcraft.custom.integration;

import com.gypsyhost.socketcraft.SocketCraft;
import com.gypsyhost.socketcraft.custom.gui.slot.ModFuelSlot;
import com.gypsyhost.socketcraft.custom.gui.slot.ModResultSlot;
import com.gypsyhost.socketcraft.custom.recipe.CatalyzerRecipe;
import com.gypsyhost.socketcraft.custom.recipe.PressRecipe;
import com.gypsyhost.socketcraft.registry.ModBlocks;
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
import net.minecraft.world.inventory.FurnaceFuelSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class CatalyzerRecipeCategory implements IRecipeCategory<CatalyzerRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(SocketCraft.MOD_ID, "catalyzing");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(SocketCraft.MOD_ID, "textures/gui/catalyzer_gui.png");

    private final IDrawable background;
    private final IDrawable icon;

    private final int FUEL_SLOT = 0;
    private final int INPUT_SLOT_LEFT = 0;
    private final int INPUT_SLOT_TOP = 1;
    private final int INPUT_SLOT_RIGHT = 2;
    private final int INPUT_SLOT_BOTTOM = 3;
    private final int CATALYZER = 4;

    public CatalyzerRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 100);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM, new ItemStack(ModBlocks.CATALYZER.get()));
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends CatalyzerRecipe> getRecipeClass() {
        return CatalyzerRecipe.class;
    }

    @Override
    public Component getTitle() {
        return new TextComponent("Catalyzer");
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
    public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull CatalyzerRecipe catalyzerRecipes, @Nonnull IFocusGroup focusGroup) {
        builder.addSlot(RecipeIngredientRole.INPUT, 47, 46).addIngredients(catalyzerRecipes.getIngredients().get(INPUT_SLOT_LEFT));
        builder.addSlot(RecipeIngredientRole.INPUT, 80, 13).addIngredients(catalyzerRecipes.getIngredients().get(INPUT_SLOT_TOP));
        builder.addSlot(RecipeIngredientRole.INPUT, 113, 46).addIngredients(catalyzerRecipes.getIngredients().get(INPUT_SLOT_RIGHT));
        builder.addSlot(RecipeIngredientRole.INPUT, 80, 79).addIngredients(catalyzerRecipes.getIngredients().get(INPUT_SLOT_BOTTOM));
        builder.addSlot(RecipeIngredientRole.CATALYST, 80, 46).addIngredients(catalyzerRecipes.getIngredients().get(CATALYZER));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 148, 46).addItemStack(catalyzerRecipes.getResultItem());
    }
}