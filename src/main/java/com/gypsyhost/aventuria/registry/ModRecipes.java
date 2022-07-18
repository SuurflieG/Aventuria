package com.gypsyhost.aventuria.registry;

import com.gypsyhost.aventuria.Aventuria;
import com.gypsyhost.aventuria.custom.recipe.CatalyzerRecipe;
import com.gypsyhost.aventuria.custom.recipe.PressRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Aventuria.MOD_ID);

    public static final RegistryObject<RecipeSerializer<PressRecipe>> PRESS_SERIALIZER = SERIALIZERS.register("pressing", () -> PressRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<CatalyzerRecipe>> CATALYZER_SERIALIZER = SERIALIZERS.register("catalyzing", () -> CatalyzerRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}