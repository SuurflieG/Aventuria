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

    // Tiered
    FORTUNE_1("fortune_1", 1, true),
    FORTUNE_2("fortune_2", 2, true),
    FORTUNE_3("fortune_3", 3, true),

    EFFICIENCY_1("efficiency_1", 1, true),
    EFFICIENCY_2("efficiency_2", 2, true),
    EFFICIENCY_3("efficiency_3", 3, true),
    EFFICIENCY_4("efficiency_4", 4, true),
    EFFICIENCY_5("efficiency_5", 5, true);

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
