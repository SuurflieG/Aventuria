package com.gypsyhost.socketcraft.registry;

import com.gypsyhost.socketcraft.SocketCraft;
import com.gypsyhost.socketcraft.custom.recipe.PressRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, SocketCraft.MOD_ID);

    public static final RegistryObject<RecipeSerializer<PressRecipe>> METAL_FORMER_SERIALIZER = SERIALIZERS.register("metal_forming", () -> PressRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}