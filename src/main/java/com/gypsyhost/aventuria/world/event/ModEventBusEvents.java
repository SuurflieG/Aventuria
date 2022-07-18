package com.gypsyhost.aventuria.world.event;

import com.gypsyhost.aventuria.Aventuria;
import com.gypsyhost.aventuria.custom.recipe.CatalyzerRecipe;
import com.gypsyhost.aventuria.custom.recipe.PressRecipe;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Aventuria.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerRecipeTypes(final RegistryEvent.Register<RecipeSerializer<?>> event) {
        Registry.register(Registry.RECIPE_TYPE, PressRecipe.Type.ID, PressRecipe.Type.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, CatalyzerRecipe.Type.ID, CatalyzerRecipe.Type.INSTANCE);
    }
}
