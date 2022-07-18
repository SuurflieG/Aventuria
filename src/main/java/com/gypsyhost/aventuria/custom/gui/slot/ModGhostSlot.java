package com.gypsyhost.aventuria.custom.gui.slot;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class ModGhostSlot extends SlotItemHandler {
    public ModGhostSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public int getMaxStackSize(@NotNull ItemStack stack) {
        return 1;
    }

    @Override
    public void set(@NotNull ItemStack stack) {
        super.set(stack);
    }

    @Override
    public boolean mayPickup(Player playerIn) {
        return false;
    }

    @Override
    public void onTake(Player pPlayer, ItemStack pStack) {

    }
}
