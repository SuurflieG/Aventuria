package com.gypsyhost.aventuria.registry;

import com.gypsyhost.aventuria.Aventuria;
import com.gypsyhost.aventuria.modtiers.ModItemTier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModWeaponItems {

    public static final DeferredRegister<Item> WEAPON_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Aventuria.MOD_ID);

    private static final Item.Properties CREATIVE_TAB = new Item.Properties().tab(ModCreativeModeTab.MOD_TAB);

    public static final RegistryObject<Item> STEEL_SWORD = WEAPON_ITEMS.register("steel_sword", () -> new SwordItem(ModItemTier.STEEL, 4, 0f, CREATIVE_TAB));
    public static final RegistryObject<Item> TITANIUM_SWORD = WEAPON_ITEMS.register("titanium_sword", () -> new SwordItem(ModItemTier.TITANIUM, 7, 0f, CREATIVE_TAB));
    public static final RegistryObject<Item> TUNGSTEN_SWORD = WEAPON_ITEMS.register("tungsten_sword", () -> new SwordItem(ModItemTier.TUNGSTEN, 15, 0f, CREATIVE_TAB));


    public static void  register(IEventBus eventBus) {
        WEAPON_ITEMS.register(eventBus);
    }
}
