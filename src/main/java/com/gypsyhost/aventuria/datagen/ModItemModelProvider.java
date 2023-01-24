package com.gypsyhost.aventuria.datagen;

import com.gypsyhost.aventuria.Aventuria;
import com.gypsyhost.aventuria.registry.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Aventuria.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        withExistingParent("titanium_block", modLoc("block/titanium_block"));
        withExistingParent("titanium_ore", modLoc("block/titanium_ore"));
        withExistingParent("deepslate_titanium_ore", modLoc("block/deepslate_titanium_ore"));

        withExistingParent("upgrade_station", modLoc("block/upgrade_station"));
        withExistingParent("catalyzer", modLoc("block/catalyzer"));

        withExistingParent("aventurine_block", modLoc("block/aventurine_block"));
        withExistingParent("aventurine_cluster", modLoc("block/aventurine_cluster"));
        withExistingParent("budding_aventurine", modLoc("block/budding_aventurine"));
        withExistingParent("large_aventurine_bud", modLoc("block/large_aventurine_bud"));
        withExistingParent("medium_aventurine_bud", modLoc("block/medium_aventurine_bud"));
        withExistingParent("small_aventurine_bud", modLoc("block/small_aventurine_bud"));

        withExistingParent("pyrite_block", modLoc("block/pyrite_block"));
        withExistingParent("pyrite_cluster", modLoc("block/pyrite_cluster"));
        withExistingParent("budding_pyrite", modLoc("block/budding_pyrite"));
        withExistingParent("large_pyrite_bud", modLoc("block/large_pyrite_bud"));
        withExistingParent("medium_pyrite_bud", modLoc("block/medium_pyrite_bud"));
        withExistingParent("small_pyrite_bud", modLoc("block/small_pyrite_bud"));

        withExistingParent("ruby_block", modLoc("block/ruby_block"));
        withExistingParent("ruby_cluster", modLoc("block/ruby_cluster"));
        withExistingParent("budding_ruby", modLoc("block/budding_ruby"));
        withExistingParent("large_ruby_bud", modLoc("block/large_ruby_bud"));
        withExistingParent("medium_ruby_bud", modLoc("block/medium_ruby_bud"));
        withExistingParent("small_ruby_bud", modLoc("block/small_ruby_bud"));
        
        withExistingParent("topaz_block", modLoc("block/topaz_block"));
        withExistingParent("topaz_cluster", modLoc("block/topaz_cluster"));
        withExistingParent("budding_topaz", modLoc("block/budding_topaz"));
        withExistingParent("large_topaz_bud", modLoc("block/large_topaz_bud"));
        withExistingParent("medium_topaz_bud", modLoc("block/medium_topaz_bud"));
        withExistingParent("small_topaz_bud", modLoc("block/small_topaz_bud"));

        withExistingParent("spectrolite_block", modLoc("block/spectrolite_block"));
        withExistingParent("spectrolite_cluster", modLoc("block/spectrolite_cluster"));
        withExistingParent("budding_spectrolite", modLoc("block/budding_spectrolite"));
        withExistingParent("large_spectrolite_bud", modLoc("block/large_spectrolite_bud"));
        withExistingParent("medium_spectrolite_bud", modLoc("block/medium_spectrolite_bud"));
        withExistingParent("small_spectrolite_bud", modLoc("block/small_spectrolite_bud"));

        withExistingParent("unakite_block", modLoc("block/unakite_block"));
        withExistingParent("unakite_cluster", modLoc("block/unakite_cluster"));
        withExistingParent("budding_unakite", modLoc("block/budding_unakite"));
        withExistingParent("large_unakite_bud", modLoc("block/large_unakite_bud"));
        withExistingParent("medium_unakite_bud", modLoc("block/medium_unakite_bud"));
        withExistingParent("small_unakite_bud", modLoc("block/small_unakite_bud"));

        simpleItem(ModItems.TITANIUM_NUGGET.get());
        simpleItem(ModItems.TITANIUM_INGOT.get());
        simpleItem(ModItems.TITANIUM_RAW_ORE.get());

        simpleItem(ModArmorItems.TITANIUM_HELMET.get());
        simpleItem(ModArmorItems.TITANIUM_CHESTPLATE.get());
        simpleItem(ModArmorItems.TITANIUM_LEGGINGS.get());
        simpleItem(ModArmorItems.TITANIUM_BOOTS.get());

        handheldItem(ModToolItems.TITANIUM_AXE.get());
        handheldItem(ModToolItems.TITANIUM_SHOVEL.get());
        handheldItem(ModToolItems.TITANIUM_PICKAXE.get());
        handheldItem(ModToolItems.TITANIUM_HOE.get());

        handheldItem(ModWeaponItems.TITANIUM_SWORD.get());

        simpleItem(ModUpgradeCards.BLANK.get());
        simpleItem(ModUpgradeCards.MAGNET.get());
        simpleItem(ModUpgradeCards.SILK.get());
        simpleItem(ModUpgradeCards.EXPANDER.get());
        simpleItem(ModUpgradeCards.DEPTH.get());

        simpleItem(ModUpgradeCards.AQUA_AFFINITY.get());

        simpleItem(ModUpgradeCards.BANE_OF_ARTHROPODS_1.get());
        simpleItem(ModUpgradeCards.BANE_OF_ARTHROPODS_2.get());
        simpleItem(ModUpgradeCards.BANE_OF_ARTHROPODS_3.get());
        simpleItem(ModUpgradeCards.BANE_OF_ARTHROPODS_4.get());
        simpleItem(ModUpgradeCards.BANE_OF_ARTHROPODS_5.get());

        simpleItem(ModUpgradeCards.BLAST_PROTECTION_1.get());
        simpleItem(ModUpgradeCards.BLAST_PROTECTION_2.get());
        simpleItem(ModUpgradeCards.BLAST_PROTECTION_3.get());
        simpleItem(ModUpgradeCards.BLAST_PROTECTION_4.get());

        simpleItem(ModUpgradeCards.DEPTH_STRIDER_1.get());
        simpleItem(ModUpgradeCards.DEPTH_STRIDER_2.get());
        simpleItem(ModUpgradeCards.DEPTH_STRIDER_3.get());

        simpleItem(ModUpgradeCards.EFFICIENCY_1.get());
        simpleItem(ModUpgradeCards.EFFICIENCY_2.get());
        simpleItem(ModUpgradeCards.EFFICIENCY_3.get());
        simpleItem(ModUpgradeCards.EFFICIENCY_4.get());
        simpleItem(ModUpgradeCards.EFFICIENCY_5.get());

        simpleItem(ModUpgradeCards.FEATHER_FALLING_1.get());
        simpleItem(ModUpgradeCards.FEATHER_FALLING_2.get());
        simpleItem(ModUpgradeCards.FEATHER_FALLING_3.get());
        simpleItem(ModUpgradeCards.FEATHER_FALLING_4.get());

        simpleItem(ModUpgradeCards.FIRE_ASPECT_1.get());
        simpleItem(ModUpgradeCards.FIRE_ASPECT_2.get());

        simpleItem(ModUpgradeCards.FIRE_PROTECTION_1.get());
        simpleItem(ModUpgradeCards.FIRE_PROTECTION_2.get());
        simpleItem(ModUpgradeCards.FIRE_PROTECTION_3.get());
        simpleItem(ModUpgradeCards.FIRE_PROTECTION_4.get());

        simpleItem(ModUpgradeCards.FORTUNE_1.get());
        simpleItem(ModUpgradeCards.FORTUNE_2.get());
        simpleItem(ModUpgradeCards.FORTUNE_3.get());

        simpleItem(ModUpgradeCards.FROST_WALKER_1.get());
        simpleItem(ModUpgradeCards.FROST_WALKER_2.get());

        simpleItem(ModUpgradeCards.KNOCKBACK_1.get());
        simpleItem(ModUpgradeCards.KNOCKBACK_2.get());

        simpleItem(ModUpgradeCards.MOB_LOOTING_1.get());
        simpleItem(ModUpgradeCards.MOB_LOOTING_2.get());
        simpleItem(ModUpgradeCards.MOB_LOOTING_3.get());

        simpleItem(ModUpgradeCards.MENDING.get());

        simpleItem(ModUpgradeCards.PROJECTILE_PROTECTION_1.get());
        simpleItem(ModUpgradeCards.PROJECTILE_PROTECTION_2.get());
        simpleItem(ModUpgradeCards.PROJECTILE_PROTECTION_3.get());
        simpleItem(ModUpgradeCards.PROJECTILE_PROTECTION_4.get());

        simpleItem(ModUpgradeCards.PROTECTION_1.get());
        simpleItem(ModUpgradeCards.PROTECTION_2.get());
        simpleItem(ModUpgradeCards.PROTECTION_3.get());
        simpleItem(ModUpgradeCards.PROTECTION_4.get());

        simpleItem(ModUpgradeCards.RESPIRATION_1.get());
        simpleItem(ModUpgradeCards.RESPIRATION_2.get());
        simpleItem(ModUpgradeCards.RESPIRATION_3.get());

        simpleItem(ModUpgradeCards.SHARPNESS_1.get());
        simpleItem(ModUpgradeCards.SHARPNESS_2.get());
        simpleItem(ModUpgradeCards.SHARPNESS_3.get());
        simpleItem(ModUpgradeCards.SHARPNESS_4.get());
        simpleItem(ModUpgradeCards.SHARPNESS_5.get());

        simpleItem(ModUpgradeCards.SMITE_1.get());
        simpleItem(ModUpgradeCards.SMITE_2.get());
        simpleItem(ModUpgradeCards.SMITE_3.get());
        simpleItem(ModUpgradeCards.SMITE_4.get());
        simpleItem(ModUpgradeCards.SMITE_5.get());

        simpleItem(ModUpgradeCards.SOUL_SPEED_1.get());
        simpleItem(ModUpgradeCards.SOUL_SPEED_2.get());
        simpleItem(ModUpgradeCards.SOUL_SPEED_3.get());

        simpleItem(ModUpgradeCards.SWEEPING_EDGE_1.get());
        simpleItem(ModUpgradeCards.SWEEPING_EDGE_2.get());
        simpleItem(ModUpgradeCards.SWEEPING_EDGE_3.get());

        simpleItem(ModUpgradeCards.THORNS_1.get());
        simpleItem(ModUpgradeCards.THORNS_2.get());
        simpleItem(ModUpgradeCards.THORNS_3.get());

        simpleItem(ModUpgradeCards.UNBREAKING_1.get());
        simpleItem(ModUpgradeCards.UNBREAKING_2.get());
        simpleItem(ModUpgradeCards.UNBREAKING_3.get());

        simpleItem(ModItems.AVENTURINE_SHARD.get());
        simpleItem(ModItems.PYRITE_SHARD.get());
        simpleItem(ModItems.RUBY_SHARD.get());
        simpleItem(ModItems.TOPAZ_SHARD.get());
        simpleItem(ModItems.SPECTROLITE_SHARD.get());
        simpleItem(ModItems.UNAKITE_SHARD.get());




    }

    private ItemModelBuilder simpleItem(Item item) {
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Aventuria.MOD_ID,"item/" + item.getRegistryName().getPath()));
    }

    private ItemModelBuilder simpleItemEnchant(@NotNull Enchantment item) {
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Aventuria.MOD_ID,"item/" + item.getRegistryName().getPath()));
    }

    private ItemModelBuilder handheldItem(Item item) {
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(Aventuria.MOD_ID,"item/" + item.getRegistryName().getPath()));
    }


}
