package com.gypsyhost.aventuria.custom.item.upgradecards;

import com.gypsyhost.aventuria.custom.gui.screen.CustomToolScreen;
import com.gypsyhost.aventuria.custom.item.tool.UpgradeHelper;
import net.minecraft.client.MouseHandler;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeI18n;

import javax.annotation.Nullable;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Credit goes to Direwolf20 and mining gadgets
 * https://github.com/Direwolf20-MC/MiningGadgets
 * With some changes from me.
 */

public class UpgradeTools {
    private static final String KEY_UPGRADES = "upgrades";
    private static final String KEY_UPGRADE = "upgrade";
    private static final String KEY_ENABLED = "enabled";

    private static void setUpgradeNBT(CompoundTag nbt, UpgradeCardItem upgrade) {
        ListTag list = nbt.getList(KEY_UPGRADES, Tag.TAG_COMPOUND);

        CompoundTag compound = new CompoundTag();
        compound.putString(KEY_UPGRADE, upgrade.getCard().getName());
        compound.putBoolean(KEY_ENABLED, upgrade.getCard().isEnabled());

        list.add(compound);
        nbt.put(KEY_UPGRADES, list);
    }

    public static CompoundTag setUpgradesNBT(List<Upgrade> laserUpgrades) {
        CompoundTag listCompound = new CompoundTag();
        ListTag list = new ListTag();

        laserUpgrades.forEach( upgrade -> {
            CompoundTag compound = new CompoundTag();
            compound.putString(KEY_UPGRADE, upgrade.getName());
            compound.putBoolean(KEY_ENABLED, upgrade.isEnabled());
            list.add(compound);
        });

        listCompound.put(KEY_UPGRADES, list);
        return listCompound;
    }

    public static void setUpgrade(ItemStack tool, UpgradeCardItem upgrade) {
        CompoundTag tagCompound = tool.getOrCreateTag();
        setUpgradeNBT(tagCompound, upgrade);
    }

    public static void updateUpgrade(ItemStack tool, Upgrade upgrade) {
        CompoundTag tagCompound = tool.getOrCreateTag();
        ListTag list = tagCompound.getList(KEY_UPGRADES, Tag.TAG_COMPOUND);

        list.forEach(e -> {
            CompoundTag compound = (CompoundTag) e;
            String name = compound.getString(KEY_UPGRADE);
            boolean enabled = compound.getBoolean(KEY_ENABLED);

            if ((name.contains(Upgrade.FORTUNE_1.getBaseName()) && enabled && upgrade.lazyIs(Upgrade.SILK))
                    || (name.equals(Upgrade.SILK.getBaseName()) && enabled && upgrade.lazyIs(Upgrade.FORTUNE_1)))
                compound.putBoolean(KEY_ENABLED, false);

            else if ((name.contains(Upgrade.BANE_OF_ARTHROPODS_1.getBaseName()) && enabled && upgrade.lazyIs(Upgrade.SHARPNESS_1))
                    || ((name.contains(Upgrade.BANE_OF_ARTHROPODS_1.getBaseName()) && enabled && upgrade.lazyIs(Upgrade.SMITE_1)))
                    || ((name.contains(Upgrade.SHARPNESS_1.getBaseName()) && enabled && upgrade.lazyIs(Upgrade.BANE_OF_ARTHROPODS_1)))
                    || ((name.contains(Upgrade.SHARPNESS_1.getBaseName()) && enabled && upgrade.lazyIs(Upgrade.SMITE_1)))
                    || ((name.contains(Upgrade.SMITE_1.getBaseName()) && enabled && upgrade.lazyIs(Upgrade.BANE_OF_ARTHROPODS_1)))
                    || ((name.contains(Upgrade.SMITE_1.getBaseName()) && enabled && upgrade.lazyIs(Upgrade.SHARPNESS_1))))
                compound.putBoolean(KEY_ENABLED, false);

            else if ((name.contains(Upgrade.BLAST_PROTECTION_1.getBaseName()) && enabled && upgrade.lazyIs(Upgrade.FIRE_PROTECTION_1))
                    || ((name.contains(Upgrade.BLAST_PROTECTION_1.getBaseName()) && enabled && upgrade.lazyIs(Upgrade.PROJECTILE_PROTECTION_1)))
                    || ((name.contains(Upgrade.BLAST_PROTECTION_1.getBaseName()) && enabled && upgrade.lazyIs(Upgrade.PROTECTION_1)))
                    || ((name.contains(Upgrade.FIRE_PROTECTION_1.getBaseName()) && enabled && upgrade.lazyIs(Upgrade.BLAST_PROTECTION_1)))
                    || ((name.contains(Upgrade.FIRE_PROTECTION_1.getBaseName()) && enabled && upgrade.lazyIs(Upgrade.PROJECTILE_PROTECTION_1)))
                    || ((name.contains(Upgrade.FIRE_PROTECTION_1.getBaseName()) && enabled && upgrade.lazyIs(Upgrade.PROTECTION_1)))
                    || ((name.contains(Upgrade.PROJECTILE_PROTECTION_1.getBaseName()) && enabled && upgrade.lazyIs(Upgrade.BLAST_PROTECTION_1)))
                    || ((name.contains(Upgrade.PROJECTILE_PROTECTION_1.getBaseName()) && enabled && upgrade.lazyIs(Upgrade.FIRE_PROTECTION_1)))
                    || ((name.contains(Upgrade.PROJECTILE_PROTECTION_1.getBaseName()) && enabled && upgrade.lazyIs(Upgrade.PROTECTION_1)))
                    || ((name.contains(Upgrade.PROTECTION_1.getBaseName()) && enabled && upgrade.lazyIs(Upgrade.BLAST_PROTECTION_1)))
                    || ((name.contains(Upgrade.PROTECTION_1.getBaseName()) && enabled && upgrade.lazyIs(Upgrade.FIRE_PROTECTION_1)))
                    || ((name.contains(Upgrade.PROTECTION_1.getBaseName()) && enabled && upgrade.lazyIs(Upgrade.PROJECTILE_PROTECTION_1))))
                compound.putBoolean(KEY_ENABLED, false);

            if (name.equals(upgrade.getName()))
                compound.putBoolean(KEY_ENABLED, !compound.getBoolean(KEY_ENABLED));
        });
    }


    // Return all upgrades in the item.
    public static List<Upgrade> getUpgradesFromTag(CompoundTag tagCompound) {
        ListTag upgrades = tagCompound.getList(KEY_UPGRADES, Tag.TAG_COMPOUND);

        List<Upgrade> functionalUpgrades = new ArrayList<>();
        if (upgrades.isEmpty())
            return functionalUpgrades;

        for (int i = 0; i < upgrades.size(); i++) {
            CompoundTag tag = upgrades.getCompound(i);

            // Skip unknowns
            Upgrade type = getUpgradesByName(tag.getString(KEY_UPGRADE));
            if( type == null )
                continue;

            type.setEnabled(!tag.contains(KEY_ENABLED) || tag.getBoolean(KEY_ENABLED));
            functionalUpgrades.add(type);
        }

        return functionalUpgrades;
    }

    public static List<Upgrade> getActiveUpgradesFromTag(CompoundTag tagCompound) {
        ListTag upgrades = tagCompound.getList(KEY_UPGRADES, Tag.TAG_COMPOUND);

        List<Upgrade> functionalUpgrades = new ArrayList<>();
        if (upgrades.isEmpty())
            return functionalUpgrades;

        for (int i = 0; i < upgrades.size(); i++) {
            CompoundTag tag = upgrades.getCompound(i);

            Upgrade type = getUpgradesByName(tag.getString(KEY_UPGRADE));
            if (type == null)
                continue;

            type.setEnabled(!tag.contains(KEY_ENABLED) || tag.getBoolean(KEY_ENABLED));
            if (type.isEnabled())
                functionalUpgrades.add(type);
        }

        return functionalUpgrades;
    }

    @Nullable
    public static Upgrade getUpgradesByName(String name) {
        // If the name doesn't exist then move on
        try {
            Upgrade type = Upgrade.valueOf(name.toUpperCase());
            return type;
        } catch (IllegalArgumentException ignored) {
            return null;
        }
    }

    // Return all upgrades in the item.
    public static List<Upgrade> getUpgrades(ItemStack tool) {
        CompoundTag tagCompound = tool.getOrCreateTag();
        return getUpgradesFromTag(tagCompound);
    }

    public static List<Upgrade> getActiveUpgrades(ItemStack tool) {
        CompoundTag tagCompound = tool.getOrCreateTag();
        return getActiveUpgradesFromTag(tagCompound);
    }

    public static boolean containsUpgrades(ItemStack tool) {
        return tool.getOrCreateTag().contains(KEY_UPGRADES);
    }

    /**
     * Get a single upgrade and it's tier
     */
    public static Optional<Upgrade> getUpgradeFromList(List<Upgrade> upgrades, Upgrade type) {
        if( upgrades == null || upgrades.isEmpty() )
            return Optional.empty();

        return upgrades.stream()
                .filter(upgrade -> upgrade.getBaseName().equals(type.getBaseName()))
                .findFirst();
    }

    // Get a single upgrade and it's tier
    public static Optional<Upgrade> getUpgradeFromTool(ItemStack tool, Upgrade type) {
        List<Upgrade> upgrades = getUpgrades(tool);
        return getUpgradeFromList(upgrades, type);
    }

    /**
     * @implNote note that this is the only instance we use getName for non-eval uses
     * as the tool stores the full name and not it's base name
     */
    public static void removeUpgrade(ItemStack tool, Upgrade upgrade) {
        CompoundTag tagCompound = tool.getOrCreateTag();
        ListTag upgrades = tagCompound.getList(KEY_UPGRADES, Tag.TAG_COMPOUND);

        // Slightly completed, but basically it just makes a new list and collects that back to an ListNBT
        tagCompound.put(KEY_UPGRADES, upgrades.stream()
                .filter(e -> !((CompoundTag) e).getString(KEY_UPGRADE).equals(upgrade.getName()))
                .collect(Collectors.toCollection(ListTag::new)));
    }

    public static boolean containsUpgrade(ItemStack tool, Upgrade type) {
        return getUpgradeFromTool(tool, type).isPresent();
    }


    public static boolean containsActiveUpgrade(ItemStack tool, Upgrade type) {
        Optional<Upgrade> upgrade = getUpgradeFromTool(tool, type);
        return upgrade.isPresent() && upgrade.get().isEnabled();
    }

    public static boolean containsInactiveUpgrade(ItemStack tool, Upgrade type) {
        Optional<Upgrade> upgrade = getUpgradeFromTool(tool, type);
        return upgrade.isPresent() && !upgrade.get().isEnabled();
    }

    public static boolean containsActiveUpgradeFromList(List<Upgrade> upgrades, Upgrade type) {
        Optional<Upgrade> upgrade = getUpgradeFromList(upgrades, type);
        return upgrade.isPresent() && upgrade.get().isEnabled();
    }

    public static boolean containsUpgradeFromList(List<Upgrade> upgrades, Upgrade type) {
        return getUpgradeFromList(upgrades, type).isPresent();
    }

    /**
     * @param upgrade the upgrade Enum
     * @return A formatted string of the Upgrade without it's `Upgrade:` prefix
     */
    public static Component getName(Upgrade upgrade) {
        return new TextComponent(ForgeI18n.parseMessage(upgrade.getLocal()).replace(ForgeI18n.parseMessage(upgrade.getLocalReplacement()), ""));
    }
}
