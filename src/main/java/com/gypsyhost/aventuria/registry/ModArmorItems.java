package com.gypsyhost.aventuria.registry;

import com.gypsyhost.aventuria.Aventuria;
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

    public static final RegistryObject<Item> STEEL_HELMET = ARMOR_ITEMS.register("steel_helmet", () -> new ArmorItem(ModArmorMaterial.STEEL, EquipmentSlot.HEAD, new Item.Properties().tab(ModCreativeModeTab.MOD_TAB)));
    public static final RegistryObject<Item> STEEL_CHESTPLATE = ARMOR_ITEMS.register("steel_chestplate", () -> new ArmorItem(ModArmorMaterial.STEEL, EquipmentSlot.CHEST, new Item.Properties().tab(ModCreativeModeTab.MOD_TAB)));
    public static final RegistryObject<Item> STEEL_LEGGINGS = ARMOR_ITEMS.register("steel_leggings", () -> new ArmorItem(ModArmorMaterial.STEEL, EquipmentSlot.LEGS, new Item.Properties().tab(ModCreativeModeTab.MOD_TAB)));
    public static final RegistryObject<Item> STEEL_BOOTS = ARMOR_ITEMS.register("steel_boots", () -> new ArmorItem(ModArmorMaterial.STEEL, EquipmentSlot.FEET, new Item.Properties().tab(ModCreativeModeTab.MOD_TAB)));

    public static final RegistryObject<Item> TITANIUM_HELMET = ARMOR_ITEMS.register("titanium_helmet", () -> new ArmorItem(ModArmorMaterial.TITANIUM, EquipmentSlot.HEAD, new Item.Properties().tab(ModCreativeModeTab.MOD_TAB)));
    public static final RegistryObject<Item> TITANIUM_CHESTPLATE = ARMOR_ITEMS.register("titanium_chestplate", () -> new ArmorItem(ModArmorMaterial.TITANIUM, EquipmentSlot.CHEST, new Item.Properties().tab(ModCreativeModeTab.MOD_TAB)));
    public static final RegistryObject<Item> TITANIUM_LEGGINGS = ARMOR_ITEMS.register("titanium_leggings", () -> new ArmorItem(ModArmorMaterial.TITANIUM, EquipmentSlot.LEGS, new Item.Properties().tab(ModCreativeModeTab.MOD_TAB)));
    public static final RegistryObject<Item> TITANIUM_BOOTS = ARMOR_ITEMS.register("titanium_boots", () -> new ArmorItem(ModArmorMaterial.TITANIUM, EquipmentSlot.FEET, new Item.Properties().tab(ModCreativeModeTab.MOD_TAB)));

    public static final RegistryObject<Item> TUNGSTEN_HELMET = ARMOR_ITEMS.register("tungsten_helmet", () -> new ArmorItem(ModArmorMaterial.TUNGSTEN, EquipmentSlot.HEAD, new Item.Properties().tab(ModCreativeModeTab.MOD_TAB)));
    public static final RegistryObject<Item> TUNGSTEN_CHESTPLATE = ARMOR_ITEMS.register("tungsten_chestplate", () -> new ArmorItem(ModArmorMaterial.TUNGSTEN, EquipmentSlot.CHEST, new Item.Properties().tab(ModCreativeModeTab.MOD_TAB)));
    public static final RegistryObject<Item> TUNGSTEN_LEGGINGS = ARMOR_ITEMS.register("tungsten_leggings", () -> new ArmorItem(ModArmorMaterial.TUNGSTEN, EquipmentSlot.LEGS, new Item.Properties().tab(ModCreativeModeTab.MOD_TAB)));
    public static final RegistryObject<Item> TUNGSTEN_BOOTS = ARMOR_ITEMS.register("tungsten_boots", () -> new ArmorItem(ModArmorMaterial.TUNGSTEN, EquipmentSlot.FEET, new Item.Properties().tab(ModCreativeModeTab.MOD_TAB)));



    public static void  register(IEventBus eventBus) {ARMOR_ITEMS.register(eventBus);}
}
