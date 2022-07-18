package com.gypsyhost.aventuria.custom.item.tool;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Credit goes to Direwolf20 and mining gadgets
 * https://github.com/Direwolf20-MC/MiningGadgets
 * With some changes from me.
 */

public class ToolMiningProperties {
    private ToolMiningProperties() {}

    private static final String KEY_MINING_SIZE = "range";

    public static int setRange(ItemStack gadget, int range) {
        gadget.getOrCreateTag().putInt(KEY_MINING_SIZE, range);
        return range;
    }

    public static int getRange(ItemStack gadget) {
        CompoundTag compound = gadget.getOrCreateTag();
        return !compound.contains(KEY_MINING_SIZE) ? setRange(gadget, 1) : compound.getInt(KEY_MINING_SIZE);
    }

    // mostly stolen from ItemStackHandler
    public static List<ItemStack> deserializeItemStackList(CompoundTag nbt) {
        List<ItemStack> stacks = new ArrayList<>();
        ListTag tagList = nbt.getList("Items", Tag.TAG_COMPOUND);

        for (int i = 0; i < tagList.size(); i++) {
            CompoundTag itemTags = tagList.getCompound(i);
            stacks.add(ItemStack.of(itemTags));
        }

        return stacks;
    }

    public static CompoundTag serializeItemStackList(List<ItemStack> stacks) {
        ListTag nbtTagList = new ListTag();
        for (int i = 0; i < stacks.size(); i++)
        {
            if (stacks.get(i).isEmpty())
                continue;

            CompoundTag itemTag = new CompoundTag();
            stacks.get(i).save(itemTag);
            nbtTagList.add(itemTag);
        }

        CompoundTag nbt = new CompoundTag();
        nbt.put("Items", nbtTagList);
        return nbt;
    }
}