package com.gypsyhost.aventuria.registry;

import com.gypsyhost.aventuria.Aventuria;
import com.gypsyhost.aventuria.custom.item.CatalystItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Aventuria.MOD_ID);

    private static final Item.Properties CREATIVE_TAB = new Item.Properties().tab(ModCreativeModeTab.MOD_TAB);
    private static final Item.Properties CREATIVE_TAB_D32 = new Item.Properties().tab(ModCreativeModeTab.MOD_TAB).durability(32);
    private static final Item.Properties CREATIVE_TAB_D128 = new Item.Properties().tab(ModCreativeModeTab.MOD_TAB).durability(128);
    private static final Item.Properties CREATIVE_TAB_D256 = new Item.Properties().tab(ModCreativeModeTab.MOD_TAB).durability(256);
    private static final Item.Properties CREATIVE_TAB_D512 = new Item.Properties().tab(ModCreativeModeTab.MOD_TAB).durability(512);

    public static final RegistryObject<Item> TITANIUM_RAW_ORE = ITEMS.register("titanium_raw_ore", () -> new Item(CREATIVE_TAB));
    public static final RegistryObject<Item> TITANIUM_NUGGET = ITEMS.register("titanium_nugget", () -> new Item(CREATIVE_TAB));
    public static final RegistryObject<Item> TITANIUM_INGOT = ITEMS.register("titanium_ingot", () -> new Item(CREATIVE_TAB));

    public static final RegistryObject<Item> CATALYST = ITEMS.register("catalyst", () -> new CatalystItem(CREATIVE_TAB_D32));

    public static final RegistryObject<Item> AVENTURINE_SHARD = ITEMS.register("aventurine_shard", () -> new Item(CREATIVE_TAB));
    public static final RegistryObject<Item> PYRITE_SHARD = ITEMS.register("pyrite_shard", () -> new Item(CREATIVE_TAB));
    public static final RegistryObject<Item> RUBY_SHARD = ITEMS.register("ruby_shard", () -> new Item(CREATIVE_TAB));
    public static final RegistryObject<Item> TOPAZ_SHARD = ITEMS.register("topaz_shard", () -> new Item(CREATIVE_TAB));
    public static final RegistryObject<Item> SPECTROLITE_SHARD = ITEMS.register("spectrolite_shard", () -> new Item(CREATIVE_TAB));
    public static final RegistryObject<Item> UNAKITE_SHARD = ITEMS.register("unakite_shard", () -> new Item(CREATIVE_TAB));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
