package com.gypsyhost.aventuria.registry;

import com.gypsyhost.aventuria.Aventuria;
import com.gypsyhost.aventuria.custom.item.armor.CustomArmorItem;
import com.gypsyhost.aventuria.modtiers.ModArmorMaterial;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModArmorItems {

    public static final DeferredRegister<Item> ARMOR_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Aventuria.MOD_ID);

    public static final RegistryObject<Item> TITANIUM_HELMET = ARMOR_ITEMS.register("titanium_helmet", () -> new CustomArmorItem(ModArmorMaterial.TITANIUM, EquipmentSlot.HEAD, new Item.Properties().tab(ModCreativeModeTab.MOD_TAB)));
    public static final RegistryObject<Item> TITANIUM_CHESTPLATE = ARMOR_ITEMS.register("titanium_chestplate", () -> new CustomArmorItem(ModArmorMaterial.TITANIUM, EquipmentSlot.CHEST, new Item.Properties().tab(ModCreativeModeTab.MOD_TAB)));
    public static final RegistryObject<Item> TITANIUM_LEGGINGS = ARMOR_ITEMS.register("titanium_leggings", () -> new CustomArmorItem(ModArmorMaterial.TITANIUM, EquipmentSlot.LEGS, new Item.Properties().tab(ModCreativeModeTab.MOD_TAB)));
    public static final RegistryObject<Item> TITANIUM_BOOTS = ARMOR_ITEMS.register("titanium_boots", () -> new CustomArmorItem(ModArmorMaterial.TITANIUM, EquipmentSlot.FEET, new Item.Properties().tab(ModCreativeModeTab.MOD_TAB)));

    public static void  register(IEventBus eventBus) {ARMOR_ITEMS.register(eventBus);}
}
