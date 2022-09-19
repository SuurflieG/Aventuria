package com.gypsyhost.aventuria.custom.item.upgradecards;

import com.gypsyhost.aventuria.Aventuria;
import net.minecraft.world.item.ItemStack;

/**
 * Credit goes to Direwolf20 and mining gadgets
 * https://github.com/Direwolf20-MC/MiningGadgets
 * With some changes from me.
 */

public enum Upgrade {

    //Blank
    BLANK("blank", false),

    SILK("silk", true),
    THREE_BY_THREE("three_by_three", false),
    MAGNET("magnet", true),
    MENDING("mending",true),
    AQUA_AFFINITY("aqua_affinity",true),

    // Tiered

    BANE_OF_ARTHROPODS_1("bane_of_arthropods_1",1,true),
    BANE_OF_ARTHROPODS_2("bane_of_arthropods_2",2,true),
    BANE_OF_ARTHROPODS_3("bane_of_arthropods_3",3,true),
    BANE_OF_ARTHROPODS_4("bane_of_arthropods_4",4,true),
    BANE_OF_ARTHROPODS_5("bane_of_arthropods_5",5,true),

    BLAST_PROTECTION_1("blast_protection_1", 1, true),
    BLAST_PROTECTION_2("blast_protection_2", 2, true),
    BLAST_PROTECTION_3("blast_protection_3", 3, true),
    BLAST_PROTECTION_4("blast_protection_4", 4, true),

    DEPTH_STRIDER_1("depth_strider_1",1,true),
    DEPTH_STRIDER_2("depth_strider_2",2,true),
    DEPTH_STRIDER_3("depth_strider_3",3,true),

    EFFICIENCY_1("efficiency_1", 1, true),
    EFFICIENCY_2("efficiency_2", 2, true),
    EFFICIENCY_3("efficiency_3", 3, true),
    EFFICIENCY_4("efficiency_4", 4, true),
    EFFICIENCY_5("efficiency_5", 5, true),

    FEATHER_FALLING_1("feather_falling_1", 1, true),
    FEATHER_FALLING_2("feather_falling_2", 2, true),
    FEATHER_FALLING_3("feather_falling_3", 3, true),
    FEATHER_FALLING_4("feather_falling_4", 4, true),

    FIRE_ASPECT_1("fire_aspect_1",1,true),
    FIRE_ASPECT_2("fire_aspect_2",2,true),

    FIRE_PROTECTION_1("fire_protection_1", 1, true),
    FIRE_PROTECTION_2("fire_protection_2", 2, true),
    FIRE_PROTECTION_3("fire_protection_3", 3, true),
    FIRE_PROTECTION_4("fire_protection_4", 4, true),

    FORTUNE_1("fortune_1", 1, true),
    FORTUNE_2("fortune_2", 2, true),
    FORTUNE_3("fortune_3", 3, true),

    FROST_WALKER_1("frost_walker_1",1,true),
    FROST_WALKER_2("frost_walker_2",2,true),

    KNOCKBACK_1("knockback_1",1,true),
    KNOCKBACK_2("knockback_2",2,true),

    MOB_LOOTING_1("mob_looting_1",1,true),
    MOB_LOOTING_2("mob_looting_2",2,true),
    MOB_LOOTING_3("mob_looting_3",3,true),

    PROJECTILE_PROTECTION_1("projectile_protection_1", 1, true),
    PROJECTILE_PROTECTION_2("projectile_protection_2", 2, true),
    PROJECTILE_PROTECTION_3("projectile_protection_3", 3, true),
    PROJECTILE_PROTECTION_4("projectile_protection_4", 4, true),

    PROTECTION_1("protection_1", 1, true),
    PROTECTION_2("protection_2", 2, true),
    PROTECTION_3("protection_3", 3, true),
    PROTECTION_4("protection_4", 4, true),

    RESPIRATION_1("respiration_1",1,true),
    RESPIRATION_2("respiration_2",2,true),
    RESPIRATION_3("respiration_3",3,true),

    SHARPNESS_1("sharpness_1",1,true),
    SHARPNESS_2("sharpness_2",2,true),
    SHARPNESS_3("sharpness_3",3,true),
    SHARPNESS_4("sharpness_4",4,true),
    SHARPNESS_5("sharpness_5",5,true),

    SMITE_1("smite_1",1,true),
    SMITE_2("smite_2",2,true),
    SMITE_3("smite_3",3,true),
    SMITE_4("smite_4",4,true),
    SMITE_5("smite_5",5,true),

    SOUL_SPEED_1("soul_speed_1",1,true),
    SOUL_SPEED_2("soul_speed_2",2,true),
    SOUL_SPEED_3("soul_speed_1",3,true),

    SWEEPING_EDGE_1("sweeping_edge_1",1,true),
    SWEEPING_EDGE_2("sweeping_edge_2",2,true),
    SWEEPING_EDGE_3("sweeping_edge_3",3,true),

    THORNS_1("thorns_1",1,true),
    THORNS_2("thorns_2",2,true),
    THORNS_3("thorns_3",3,true),

    UNBREAKING_1("unbreaking_1",1,true),
    UNBREAKING_2("unbreaking_2",2,true),
    UNBREAKING_3("unbreaking_3",3,true);

    private final String name;
    private final String baseName;
    private final UpgradeCardItem upgradeCardItem;
    private final int tier;
    private boolean active = true;
    private boolean inactive = false;

    private final boolean isToggleable;
    private final String toolTip;
    private final ItemStack upgradeStack;

    Upgrade(String name, int tier, boolean isToggleable) {
        this.name = name;
        this.tier = tier;
        this.upgradeCardItem = new UpgradeCardItem(this);
        this.upgradeStack = new ItemStack(this.upgradeCardItem);
        this.baseName = tier == -1 ? name : name.substring(0, name.lastIndexOf('_'));
        this.isToggleable = isToggleable;
        this.toolTip = "tooltip.aventuria." + this.baseName;
    }

    Upgrade(String name, boolean isToggleable) {
        this(name, -1, isToggleable);
    }

    public String getName() {
        return name;
    }

    public UpgradeCardItem getCard() {
        return upgradeCardItem;
    }

    public ItemStack getStack() {
        return upgradeStack;
    }

    public int getTier() {
        return tier;
    }

    // Try and always use base name eval
    public String getBaseName() {
        return baseName;
    }

    public String getLocal() {
        return String.format("item.aventuria.upgrade_%s", this.getName());
    }

    // Returns the translated string we can use to actively replace.
    public String getLocalReplacement() {
        return Aventuria.MOD_ID + ".upgrade.replacement";
    }

    public boolean hasTier() {
        return tier != -1;
    }

    public boolean isEnabled() {
        return active;
    }

    public boolean isDisabled() {
        return inactive;
    }

    public void setEnabled(boolean active) {
        this.active = active;
    }

    public void setDisabled(boolean inactive) {
        this.inactive = inactive;
    }

    public boolean isToggleable() {
        return isToggleable;
    }

    public String getToolTip() {
        return toolTip;
    }

    /**
     * Compares only the base name of the upgrade
     *
     * @param upgrade upgrade to compare against
     */
    public boolean lazyIs(Upgrade upgrade) {
        return this.getBaseName().equals(upgrade.getBaseName());
    }
}
