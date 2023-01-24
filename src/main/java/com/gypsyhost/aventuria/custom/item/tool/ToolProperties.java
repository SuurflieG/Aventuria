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

public class ToolProperties {
    private ToolProperties() {}

    private static final String KEY_MINING_SIZE = "size";
    private static final String KEY_MINING_DEPTH = "depth";

    public static int setMiningSize(ItemStack tool, int size) {
        tool.getOrCreateTag().putInt(KEY_MINING_SIZE, size);
        return size;
    }

    public static int getMiningSize(ItemStack tool) {
        CompoundTag compound = tool.getOrCreateTag();
        return !compound.contains(KEY_MINING_SIZE) ? setMiningSize(tool, 1) : compound.getInt(KEY_MINING_SIZE);
    }

    public static int setMiningDepth(ItemStack tool, int depth) {
        tool.getOrCreateTag().putInt(KEY_MINING_DEPTH, depth);
        return depth;
    }

    public static int getMiningDepth(ItemStack tool) {
        CompoundTag compound = tool.getOrCreateTag();
        return !compound.contains(KEY_MINING_DEPTH) ? setMiningDepth(tool, 1) : compound.getInt(KEY_MINING_DEPTH);
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