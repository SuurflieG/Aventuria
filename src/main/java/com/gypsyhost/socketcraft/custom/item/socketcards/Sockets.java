package com.gypsyhost.socketcraft.custom.item.socketcards;

import com.gypsyhost.socketcraft.SocketCraft;
import net.minecraft.world.item.ItemStack;


public enum Sockets {

    //Blank
    BLANK("blank", false),

    SILK("silk", true),
    VOID_JUNK("void_junk"),
    MAGNET("magnet"),
    THREE_BY_THREE("three_by_three", false),

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
    private final SocketCard socketCard;
    private final int tier;
    private boolean active = true;
    private final boolean isToggleable;
    private final String toolTip;
    private final ItemStack upgradeStack;

    Sockets(String name, int tier, boolean isToggleable) {
        this.name = name;
        this.tier = tier;
        this.socketCard = new SocketCard(this);
        this.upgradeStack = new ItemStack(this.socketCard);
        this.baseName = tier == -1 ? name : name.substring(0, name.lastIndexOf('_'));
        this.isToggleable = isToggleable;
        this.toolTip = "tooltip.socketcraft." + this.baseName;
    }

    Sockets(String name, int tier) {
        this(name, tier, false);
    }

    Sockets(String name) {
        this(name, -1, true);
    }

    Sockets(String name, boolean isToggleable) {
        this(name, -1, isToggleable);
    }

    public String getName() {
        return name;
    }

    public SocketCard getCard() {
        return socketCard;
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
        return String.format("item.mininggadgets.upgrade_%s", this.getName());
    }

    // Returns the translated string we can use to actively replace.
    public String getLocalReplacement() {
        return SocketCraft.MOD_ID + ".upgrade.replacement";
    }

    public boolean hasTier() {
        return tier != -1;
    }

    public boolean isEnabled() {
        return active;
    }

    public void setEnabled(boolean active) {
        this.active = active;
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
    public boolean lazyIs(Sockets upgrade) {
        return this.getBaseName().equals(upgrade.getBaseName());
    }
}
