package com.gypsyhost.socketcraft.registry;

import com.gypsyhost.socketcraft.SocketCraft;
import com.gypsyhost.socketcraft.modtiers.ModItemTier;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModToolItems {

    public static final DeferredRegister<Item> TOOL_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SocketCraft.MOD_ID);

    public static final RegistryObject<Item> STEEL_AXE = TOOL_ITEMS.register("steel_axe", () -> new AxeItem(ModItemTier.STEEL, 2, 0f, new Item.Properties().tab(ModCreativeModeTab.MOD_TAB)));
    public static final RegistryObject<Item> STEEL_PICKAXE = TOOL_ITEMS.register("steel_pickaxe", () -> new PickaxeItem(ModItemTier.STEEL, 2, 0f, new Item.Properties().tab(ModCreativeModeTab.MOD_TAB)));
    public static final RegistryObject<Item> STEEL_SHOVEL = TOOL_ITEMS.register("steel_shovel", () -> new ShovelItem(ModItemTier.STEEL, 0, 0f, new Item.Properties().tab(ModCreativeModeTab.MOD_TAB)));
    public static final RegistryObject<Item> STEEL_HOE = TOOL_ITEMS.register("steel_hoe", () -> new HoeItem(ModItemTier.STEEL, 0, 0f, new Item.Properties().tab(ModCreativeModeTab.MOD_TAB)));

    public static final RegistryObject<Item> TITANIUM_AXE = TOOL_ITEMS.register("titanium_axe", () -> new AxeItem(ModItemTier.TITANIUM, 5, 0f, new Item.Properties().tab(ModCreativeModeTab.MOD_TAB)));
    public static final RegistryObject<Item> TITANIUM_PICKAXE = TOOL_ITEMS.register("titanium_pickaxe", () -> new PickaxeItem(ModItemTier.TITANIUM, 5, 0f, new Item.Properties().tab(ModCreativeModeTab.MOD_TAB)));
    public static final RegistryObject<Item> TITANIUM_SHOVEL = TOOL_ITEMS.register("titanium_shovel", () -> new ShovelItem(ModItemTier.TITANIUM, 2, 0f, new Item.Properties().tab(ModCreativeModeTab.MOD_TAB)));
    public static final RegistryObject<Item> TITANIUM_HOE = TOOL_ITEMS.register("titanium_hoe", () -> new HoeItem(ModItemTier.TITANIUM, 2, 0f, new Item.Properties().tab(ModCreativeModeTab.MOD_TAB)));

    public static final RegistryObject<Item> TUNGSTEN_AXE = TOOL_ITEMS.register("tungsten_axe", () -> new AxeItem(ModItemTier.TUNGSTEN, 10, 0f, new Item.Properties().tab(ModCreativeModeTab.MOD_TAB)));
    public static final RegistryObject<Item> TUNGSTEN_PICKAXE = TOOL_ITEMS.register("tungsten_pickaxe", () -> new PickaxeItem(ModItemTier.TUNGSTEN, 10, 0f, new Item.Properties().tab(ModCreativeModeTab.MOD_TAB)));
    public static final RegistryObject<Item> TUNGSTEN_SHOVEL = TOOL_ITEMS.register("tungsten_shovel", () -> new ShovelItem(ModItemTier.TUNGSTEN, 5, 0f, new Item.Properties().tab(ModCreativeModeTab.MOD_TAB)));
    public static final RegistryObject<Item> TUNGSTEN_HOE = TOOL_ITEMS.register("tungsten_hoe", () -> new HoeItem(ModItemTier.TUNGSTEN, 5, 0f, new Item.Properties().tab(ModCreativeModeTab.MOD_TAB)));



    public static void  register(IEventBus eventBus) {
        TOOL_ITEMS.register(eventBus);
    }
}
