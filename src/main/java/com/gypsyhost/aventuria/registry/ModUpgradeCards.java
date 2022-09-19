package com.gypsyhost.aventuria.registry;

import com.gypsyhost.aventuria.Aventuria;
import com.gypsyhost.aventuria.custom.item.upgradecards.Upgrade;
import com.gypsyhost.aventuria.custom.item.upgradecards.UpgradeCardItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModUpgradeCards {
    public static final DeferredRegister<Item> UPGRADECARD_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Aventuria.MOD_ID);

    public static final RegistryObject<Item> BLANK = UPGRADECARD_ITEMS.register("upgrade_blank",() -> Upgrade.BLANK.getCard());

    public static final RegistryObject<Item> SILK = UPGRADECARD_ITEMS.register("upgrade_silk", () -> Upgrade.SILK.getCard());

    public static final RegistryObject<Item> MAGNET = UPGRADECARD_ITEMS.register("upgrade_magnet", () -> Upgrade.MAGNET.getCard());

    public static final RegistryObject<Item> THREE_BY_THREE = UPGRADECARD_ITEMS.register("upgrade_three_by_three", () -> Upgrade.THREE_BY_THREE.getCard());

    public static final RegistryObject<Item> AQUA_AFFINITY = UPGRADECARD_ITEMS.register("upgrade_aqua_affinity", () -> Upgrade.AQUA_AFFINITY.getCard());

    public static final RegistryObject<Item> BANE_OF_ARTHROPODS_1 = UPGRADECARD_ITEMS.register("upgrade_bane_of_arthropods_1", () -> Upgrade.BANE_OF_ARTHROPODS_1.getCard());
    public static final RegistryObject<Item> BANE_OF_ARTHROPODS_2 = UPGRADECARD_ITEMS.register("upgrade_bane_of_arthropods_2", () -> Upgrade.BANE_OF_ARTHROPODS_2.getCard());
    public static final RegistryObject<Item> BANE_OF_ARTHROPODS_3 = UPGRADECARD_ITEMS.register("upgrade_bane_of_arthropods_3", () -> Upgrade.BANE_OF_ARTHROPODS_3.getCard());
    public static final RegistryObject<Item> BANE_OF_ARTHROPODS_4 = UPGRADECARD_ITEMS.register("upgrade_bane_of_arthropods_4", () -> Upgrade.BANE_OF_ARTHROPODS_4.getCard());
    public static final RegistryObject<Item> BANE_OF_ARTHROPODS_5 = UPGRADECARD_ITEMS.register("upgrade_bane_of_arthropods_5", () -> Upgrade.BANE_OF_ARTHROPODS_5.getCard());

    public static final RegistryObject<Item> BLAST_PROTECTION_1 = UPGRADECARD_ITEMS.register("upgrade_blast_protection_1", () -> Upgrade.BLAST_PROTECTION_1.getCard());
    public static final RegistryObject<Item> BLAST_PROTECTION_2 = UPGRADECARD_ITEMS.register("upgrade_blast_protection_2", () -> Upgrade.BLAST_PROTECTION_2.getCard());
    public static final RegistryObject<Item> BLAST_PROTECTION_3 = UPGRADECARD_ITEMS.register("upgrade_blast_protection_3", () -> Upgrade.BLAST_PROTECTION_3.getCard());
    public static final RegistryObject<Item> BLAST_PROTECTION_4 = UPGRADECARD_ITEMS.register("upgrade_blast_protection_4", () -> Upgrade.BLAST_PROTECTION_4.getCard());

    public static final RegistryObject<Item> DEPTH_STRIDER_1 = UPGRADECARD_ITEMS.register("upgrade_depth_strider_1", () -> Upgrade.DEPTH_STRIDER_1.getCard());
    public static final RegistryObject<Item> DEPTH_STRIDER_2 = UPGRADECARD_ITEMS.register("upgrade_depth_strider_2", () -> Upgrade.DEPTH_STRIDER_2.getCard());
    public static final RegistryObject<Item> DEPTH_STRIDER_3 = UPGRADECARD_ITEMS.register("upgrade_depth_strider_3", () -> Upgrade.DEPTH_STRIDER_3.getCard());

    public static final RegistryObject<Item> EFFICIENCY_1 = UPGRADECARD_ITEMS.register("upgrade_efficiency_1", () -> Upgrade.EFFICIENCY_1.getCard());
    public static final RegistryObject<Item> EFFICIENCY_2 = UPGRADECARD_ITEMS.register("upgrade_efficiency_2", () -> Upgrade.EFFICIENCY_2.getCard());
    public static final RegistryObject<Item> EFFICIENCY_3 = UPGRADECARD_ITEMS.register("upgrade_efficiency_3", () -> Upgrade.EFFICIENCY_3.getCard());
    public static final RegistryObject<Item> EFFICIENCY_4 = UPGRADECARD_ITEMS.register("upgrade_efficiency_4", () -> Upgrade.EFFICIENCY_4.getCard());
    public static final RegistryObject<Item> EFFICIENCY_5 = UPGRADECARD_ITEMS.register("upgrade_efficiency_5", () -> Upgrade.EFFICIENCY_5.getCard());

    public static final RegistryObject<Item> FEATHER_FALLING_1 = UPGRADECARD_ITEMS.register("upgrade_feather_falling_1", () -> Upgrade.FEATHER_FALLING_1.getCard());
    public static final RegistryObject<Item> FEATHER_FALLING_2 = UPGRADECARD_ITEMS.register("upgrade_feather_falling_2", () -> Upgrade.FEATHER_FALLING_2.getCard());
    public static final RegistryObject<Item> FEATHER_FALLING_3 = UPGRADECARD_ITEMS.register("upgrade_feather_falling_3", () -> Upgrade.FEATHER_FALLING_3.getCard());
    public static final RegistryObject<Item> FEATHER_FALLING_4 = UPGRADECARD_ITEMS.register("upgrade_feather_falling_4", () -> Upgrade.FEATHER_FALLING_4.getCard());

    public static final RegistryObject<Item> FIRE_ASPECT_1 = UPGRADECARD_ITEMS.register("upgrade_fire_aspect_1", () -> Upgrade.FIRE_ASPECT_1.getCard());
    public static final RegistryObject<Item> FIRE_ASPECT_2 = UPGRADECARD_ITEMS.register("upgrade_fire_aspect_2", () -> Upgrade.FIRE_ASPECT_2.getCard());

    public static final RegistryObject<Item> FIRE_PROTECTION_1 = UPGRADECARD_ITEMS.register("upgrade_fire_protection_1", () -> Upgrade.FIRE_PROTECTION_1.getCard());
    public static final RegistryObject<Item> FIRE_PROTECTION_2 = UPGRADECARD_ITEMS.register("upgrade_fire_protection_2", () -> Upgrade.FIRE_PROTECTION_2.getCard());
    public static final RegistryObject<Item> FIRE_PROTECTION_3 = UPGRADECARD_ITEMS.register("upgrade_fire_protection_3", () -> Upgrade.FIRE_PROTECTION_3.getCard());
    public static final RegistryObject<Item> FIRE_PROTECTION_4 = UPGRADECARD_ITEMS.register("upgrade_fire_protection_4", () -> Upgrade.FIRE_PROTECTION_4.getCard());

    public static final RegistryObject<Item> FORTUNE_1 = UPGRADECARD_ITEMS.register("upgrade_fortune_1", () -> Upgrade.FORTUNE_1.getCard());
    public static final RegistryObject<Item> FORTUNE_2 = UPGRADECARD_ITEMS.register("upgrade_fortune_2", () -> Upgrade.FORTUNE_2.getCard());
    public static final RegistryObject<Item> FORTUNE_3 = UPGRADECARD_ITEMS.register("upgrade_fortune_3", () -> Upgrade.FORTUNE_3.getCard());

    public static final RegistryObject<Item> FROST_WALKER_1 = UPGRADECARD_ITEMS.register("upgrade_frost_walker_1", () -> Upgrade.FROST_WALKER_1.getCard());
    public static final RegistryObject<Item> FROST_WALKER_2 = UPGRADECARD_ITEMS.register("upgrade_frost_walker_2", () -> Upgrade.FROST_WALKER_2.getCard());

    public static final RegistryObject<Item> KNOCKBACK_1 = UPGRADECARD_ITEMS.register("upgrade_knockback_1", () -> Upgrade.KNOCKBACK_1.getCard());
    public static final RegistryObject<Item> KNOCKBACK_2 = UPGRADECARD_ITEMS.register("upgrade_knockback_2", () -> Upgrade.KNOCKBACK_2.getCard());

    public static final RegistryObject<Item> MOB_LOOTING_1 = UPGRADECARD_ITEMS.register("upgrade_mob_looting_1", () -> Upgrade.MOB_LOOTING_1.getCard());
    public static final RegistryObject<Item> MOB_LOOTING_2 = UPGRADECARD_ITEMS.register("upgrade_mob_looting_2", () -> Upgrade.MOB_LOOTING_2.getCard());
    public static final RegistryObject<Item> MOB_LOOTING_3 = UPGRADECARD_ITEMS.register("upgrade_mob_looting_3", () -> Upgrade.MOB_LOOTING_3.getCard());

    public static final RegistryObject<Item> MENDING = UPGRADECARD_ITEMS.register("upgrade_mending", () -> Upgrade.MENDING.getCard());

    public static final RegistryObject<Item> PROJECTILE_PROTECTION_1 = UPGRADECARD_ITEMS.register("upgrade_projectile_protection_1", () -> Upgrade.PROJECTILE_PROTECTION_1.getCard());
    public static final RegistryObject<Item> PROJECTILE_PROTECTION_2 = UPGRADECARD_ITEMS.register("upgrade_projectile_protection_2", () -> Upgrade.PROJECTILE_PROTECTION_2.getCard());
    public static final RegistryObject<Item> PROJECTILE_PROTECTION_3 = UPGRADECARD_ITEMS.register("upgrade_projectile_protection_3", () -> Upgrade.PROJECTILE_PROTECTION_3.getCard());
    public static final RegistryObject<Item> PROJECTILE_PROTECTION_4 = UPGRADECARD_ITEMS.register("upgrade_projectile_protection_4", () -> Upgrade.PROJECTILE_PROTECTION_4.getCard());

    public static final RegistryObject<Item> PROTECTION_1 = UPGRADECARD_ITEMS.register("upgrade_protection_1", () -> Upgrade.PROTECTION_1.getCard());
    public static final RegistryObject<Item> PROTECTION_2 = UPGRADECARD_ITEMS.register("upgrade_protection_2", () -> Upgrade.PROTECTION_2.getCard());
    public static final RegistryObject<Item> PROTECTION_3 = UPGRADECARD_ITEMS.register("upgrade_protection_3", () -> Upgrade.PROTECTION_3.getCard());
    public static final RegistryObject<Item> PROTECTION_4 = UPGRADECARD_ITEMS.register("upgrade_protection_4", () -> Upgrade.PROTECTION_4.getCard());

    public static final RegistryObject<Item> RESPIRATION_1 = UPGRADECARD_ITEMS.register("upgrade_respiration_1", () -> Upgrade.RESPIRATION_1.getCard());
    public static final RegistryObject<Item> RESPIRATION_2 = UPGRADECARD_ITEMS.register("upgrade_respiration_2", () -> Upgrade.RESPIRATION_2.getCard());
    public static final RegistryObject<Item> RESPIRATION_3 = UPGRADECARD_ITEMS.register("upgrade_respiration_3", () -> Upgrade.RESPIRATION_3.getCard());

    public static final RegistryObject<Item> SHARPNESS_1 = UPGRADECARD_ITEMS.register("upgrade_sharpness_1", () -> Upgrade.SHARPNESS_1.getCard());
    public static final RegistryObject<Item> SHARPNESS_2 = UPGRADECARD_ITEMS.register("upgrade_sharpness_2", () -> Upgrade.SHARPNESS_2.getCard());
    public static final RegistryObject<Item> SHARPNESS_3 = UPGRADECARD_ITEMS.register("upgrade_sharpness_3", () -> Upgrade.SHARPNESS_3.getCard());
    public static final RegistryObject<Item> SHARPNESS_4 = UPGRADECARD_ITEMS.register("upgrade_sharpness_4", () -> Upgrade.SHARPNESS_4.getCard());
    public static final RegistryObject<Item> SHARPNESS_5 = UPGRADECARD_ITEMS.register("upgrade_sharpness_5", () -> Upgrade.SHARPNESS_5.getCard());

    public static final RegistryObject<Item> SMITE_1 = UPGRADECARD_ITEMS.register("upgrade_smite_1", () -> Upgrade.SMITE_1.getCard());
    public static final RegistryObject<Item> SMITE_2 = UPGRADECARD_ITEMS.register("upgrade_smite_2", () -> Upgrade.SMITE_2.getCard());
    public static final RegistryObject<Item> SMITE_3 = UPGRADECARD_ITEMS.register("upgrade_smite_3", () -> Upgrade.SMITE_3.getCard());
    public static final RegistryObject<Item> SMITE_4 = UPGRADECARD_ITEMS.register("upgrade_smite_4", () -> Upgrade.SMITE_4.getCard());
    public static final RegistryObject<Item> SMITE_5 = UPGRADECARD_ITEMS.register("upgrade_smite_5", () -> Upgrade.SMITE_5.getCard());

    public static final RegistryObject<Item> SOUL_SPEED_1 = UPGRADECARD_ITEMS.register("upgrade_soul_speed_1", () -> Upgrade.SOUL_SPEED_1.getCard());
    public static final RegistryObject<Item> SOUL_SPEED_2 = UPGRADECARD_ITEMS.register("upgrade_soul_speed_2", () -> Upgrade.SOUL_SPEED_2.getCard());
    public static final RegistryObject<Item> SOUL_SPEED_3 = UPGRADECARD_ITEMS.register("upgrade_soul_speed_3", () -> Upgrade.SOUL_SPEED_3.getCard());

    public static final RegistryObject<Item> SWEEPING_EDGE_1 = UPGRADECARD_ITEMS.register("upgrade_sweeping_edge_1", () -> Upgrade.SWEEPING_EDGE_1.getCard());
    public static final RegistryObject<Item> SWEEPING_EDGE_2 = UPGRADECARD_ITEMS.register("upgrade_sweeping_edge_2", () -> Upgrade.SWEEPING_EDGE_2.getCard());
    public static final RegistryObject<Item> SWEEPING_EDGE_3 = UPGRADECARD_ITEMS.register("upgrade_sweeping_edge_3", () -> Upgrade.SWEEPING_EDGE_3.getCard());

    public static final RegistryObject<Item> THORNS_1 = UPGRADECARD_ITEMS.register("upgrade_thorns_1", () -> Upgrade.THORNS_1.getCard());
    public static final RegistryObject<Item> THORNS_2 = UPGRADECARD_ITEMS.register("upgrade_thorns_2", () -> Upgrade.THORNS_2.getCard());
    public static final RegistryObject<Item> THORNS_3 = UPGRADECARD_ITEMS.register("upgrade_thorns_3", () -> Upgrade.THORNS_3.getCard());

    public static final RegistryObject<Item> UNBREAKING_1 = UPGRADECARD_ITEMS.register("upgrade_unbreaking_1", () -> Upgrade.UNBREAKING_1.getCard());
    public static final RegistryObject<Item> UNBREAKING_2 = UPGRADECARD_ITEMS.register("upgrade_unbreaking_2", () -> Upgrade.UNBREAKING_2.getCard());
    public static final RegistryObject<Item> UNBREAKING_3 = UPGRADECARD_ITEMS.register("upgrade_unbreaking_3", () -> Upgrade.UNBREAKING_3.getCard());




    public static void register(IEventBus eventBus) {
        UPGRADECARD_ITEMS.register(eventBus);
    }
}
