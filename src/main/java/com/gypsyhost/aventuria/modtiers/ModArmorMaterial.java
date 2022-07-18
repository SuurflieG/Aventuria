package com.gypsyhost.aventuria.modtiers;

import com.gypsyhost.aventuria.Aventuria;
import com.gypsyhost.aventuria.registry.ModItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.util.Lazy;

import java.util.function.Supplier;

public enum ModArmorMaterial implements ArmorMaterial {

    STEEL("steel", 30, new int[]{2, 5, 6, 2}, 15, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(ModItems.STEEL_INGOT.get())),
    TITANIUM("titanium", 66, new int[]{3, 6, 8, 3}, 25, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> Ingredient.of(ModItems.TITANIUM_INGOT.get())),
    TUNGSTEN("tungsten", 74, new int[]{3, 6, 8, 3}, 35, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0F, 0.1F, () -> Ingredient.of(ModItems.TUNGSTEN_INGOT.get()));


//    IRON("iron", 15, new int[]{2, 5, 6, 2}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> {return Ingredient.of(Items.IRON_INGOT);}),
//    DIAMOND("diamond", 33, new int[]{3, 6, 8, 3}, 10, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {return Ingredient.of(Items.DIAMOND); }),
//    NETHERITE("netherite", 37, new int[]{3, 6, 8, 3}, 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0F, 0.1F, () -> {return Ingredient.of(Items.NETHERITE_INGOT); });

    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final Lazy<Ingredient> repairIngredient;

    ModArmorMaterial(String name, int durabilityMultiplier, int[] slotProtections, int enchantmentValue,
                     SoundEvent sound, float toughness,
                     float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.slotProtections = slotProtections;
        this.enchantmentValue = enchantmentValue;
        this.sound = sound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = Lazy.of(repairIngredient);
    }

    public int getDurabilityForSlot(EquipmentSlot pSlot) {
        return HEALTH_PER_SLOT[pSlot.getIndex()] * this.durabilityMultiplier;
    }

    public int getDefenseForSlot(EquipmentSlot pSlot) {
        return this.slotProtections[pSlot.getIndex()];
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public SoundEvent getEquipSound() {
        return this.sound;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    public String getName() {
        return Aventuria.MOD_ID + ":" + this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
