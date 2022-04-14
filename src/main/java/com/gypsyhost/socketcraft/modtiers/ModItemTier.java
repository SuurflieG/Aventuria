package com.gypsyhost.socketcraft.modtiers;

import com.gypsyhost.socketcraft.registry.ModItems;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModItemTier {

    public static final ForgeTier STEEL = new ForgeTier(2, 905, 7f, 2f, 15, BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(ModItems.STEEL_INGOT.get()));
    public static final ForgeTier TITANIUM = new ForgeTier(3, 1796, 10f, 5f, 18, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(ModItems.TITANIUM_INGOT.get()));
    public static final ForgeTier TUNGSTEN = new ForgeTier(3, 3122, 12f, 10f, 25, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(ModItems.TUNGSTEN_INGOT.get()));

}
