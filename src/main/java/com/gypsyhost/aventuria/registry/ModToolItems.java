package com.gypsyhost.aventuria.registry;

import com.gypsyhost.aventuria.Aventuria;
import com.gypsyhost.aventuria.custom.item.tool.CustomAxeItem;
import com.gypsyhost.aventuria.custom.item.tool.CustomHoeItem;
import com.gypsyhost.aventuria.custom.item.tool.CustomPickaxeItem;
import com.gypsyhost.aventuria.custom.item.tool.CustomShovelItem;
import com.gypsyhost.aventuria.modtiers.ModItemTier;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModToolItems {

    public static final DeferredRegister<Item> TOOL_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Aventuria.MOD_ID);

    private static final Item.Properties CREATIVE_TAB = new Item.Properties().stacksTo(1).tab(ModCreativeModeTab.MOD_TAB);

    public static final RegistryObject<Item> TITANIUM_AXE = TOOL_ITEMS.register("titanium_axe", () -> new CustomAxeItem(ModItemTier.TITANIUM, 5, 0f, CREATIVE_TAB));
    public static final RegistryObject<Item> TITANIUM_PICKAXE = TOOL_ITEMS.register("titanium_pickaxe", () -> new CustomPickaxeItem(ModItemTier.TITANIUM, 5, 0f, CREATIVE_TAB));
    public static final RegistryObject<Item> TITANIUM_SHOVEL = TOOL_ITEMS.register("titanium_shovel", () -> new CustomShovelItem(ModItemTier.TITANIUM, 2, 0f, CREATIVE_TAB));
    public static final RegistryObject<Item> TITANIUM_HOE = TOOL_ITEMS.register("titanium_hoe", () -> new CustomHoeItem(ModItemTier.TITANIUM, 2, 0f, CREATIVE_TAB));

    public static void  register(IEventBus eventBus) {
        TOOL_ITEMS.register(eventBus);
    }
}
