package com.gypsyhost.aventuria.custom.gui.slot;

import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import java.util.function.Consumer;

public class ModWatchedSlot extends SlotItemHandler {
    private Consumer<Integer> onPress;

    public ModWatchedSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition, Consumer<Integer> onPress) {
        super(itemHandler, index, xPosition, yPosition);
        this.onPress = onPress;
    }

    @Override
    public void setChanged() {
        super.setChanged();

        this.onPress.accept(this.getSlotIndex());
    }
}