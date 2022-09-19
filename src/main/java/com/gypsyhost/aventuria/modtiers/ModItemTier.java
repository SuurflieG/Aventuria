package com.gypsyhost.aventuria.modtiers;

import com.gypsyhost.aventuria.registry.ModItems;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModItemTier {

    public static final ForgeTier TITANIUM = new ForgeTier(3, 1796, 8f, 4f, 18, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(ModItems.TITANIUM_INGOT.get()));

}
