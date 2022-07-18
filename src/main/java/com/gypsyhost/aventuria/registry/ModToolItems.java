package com.gypsyhost.aventuria.registry;

import com.gypsyhost.aventuria.Aventuria;
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

    public static final RegistryObject<Item> STEEL_AXE = TOOL_ITEMS.register("steel_axe", () -> new AxeItem(ModItemTier.STEEL, 2, 0f, CREATIVE_TAB));
    public static final RegistryObject<Item> STEEL_PICKAXE = TOOL_ITEMS.register("steel_pickaxe", () -> new CustomPickaxeItem(ModItemTier.STEEL, 2, -2.8f, CREATIVE_TAB));
    public static final RegistryObject<Item> STEEL_SHOVEL = TOOL_ITEMS.register("steel_shovel", () -> new CustomShovelItem(ModItemTier.STEEL, 0, 0f, CREATIVE_TAB));
    public static final RegistryObject<Item> STEEL_HOE = TOOL_ITEMS.register("steel_hoe", () -> new HoeItem(ModItemTier.STEEL, 0, 0f, CREATIVE_TAB));

    public static final RegistryObject<Item> TITANIUM_AXE = TOOL_ITEMS.register("titanium_axe", () -> new AxeItem(ModItemTier.TITANIUM, 5, 0f, CREATIVE_TAB));
    public static final RegistryObject<Item> TITANIUM_PICKAXE = TOOL_ITEMS.register("titanium_pickaxe", () -> new CustomPickaxeItem(ModItemTier.TITANIUM, 5, 0f, CREATIVE_TAB));
    public static final RegistryObject<Item> TITANIUM_SHOVEL = TOOL_ITEMS.register("titanium_shovel", () -> new CustomShovelItem(ModItemTier.TITANIUM, 2, 0f, CREATIVE_TAB));
    public static final RegistryObject<Item> TITANIUM_HOE = TOOL_ITEMS.register("titanium_hoe", () -> new HoeItem(ModItemTier.TITANIUM, 2, 0f, CREATIVE_TAB));

    public static final RegistryObject<Item> TUNGSTEN_AXE = TOOL_ITEMS.register("tungsten_axe", () -> new AxeItem(ModItemTier.TUNGSTEN, 10, 0f, CREATIVE_TAB));
    public static final RegistryObject<Item> TUNGSTEN_PICKAXE = TOOL_ITEMS.register("tungsten_pickaxe", () -> new CustomPickaxeItem(ModItemTier.TUNGSTEN, 10, 0f, CREATIVE_TAB));
    public static final RegistryObject<Item> TUNGSTEN_SHOVEL = TOOL_ITEMS.register("tungsten_shovel", () -> new CustomShovelItem(ModItemTier.TUNGSTEN, 5, 0f, CREATIVE_TAB));
    public static final RegistryObject<Item> TUNGSTEN_HOE = TOOL_ITEMS.register("tungsten_hoe", () -> new HoeItem(ModItemTier.TUNGSTEN, 5, 0f, CREATIVE_TAB));


    public static void  register(IEventBus eventBus) {
        TOOL_ITEMS.register(eventBus);
    }
}
