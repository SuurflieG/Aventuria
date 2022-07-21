package com.gypsyhost.aventuria.custom.gui.menu;

import com.gypsyhost.aventuria.custom.gui.slot.ModGhostSlot;
import com.gypsyhost.aventuria.custom.item.tool.CustomPickaxeItem;
import com.gypsyhost.aventuria.custom.item.tool.CustomShovelItem;
import com.gypsyhost.aventuria.registry.ModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class CustomToolMenu extends AbstractContainerMenu {

    public CustomToolMenu(int windowId, Inventory playerInventory, FriendlyByteBuf buf) {
        this(windowId, playerInventory, new ItemStackHandler(30));
    }

    public CustomToolMenu(int windowId, Inventory playerInventory, IItemHandler ghostInventory) {
        super(ModMenuTypes.CUSTOM_TOOL_MENU.get(), windowId);
        this.setup(new InvWrapper(playerInventory), ghostInventory);
    }

    private void setup(InvWrapper playerInventory, IItemHandler ghostInventory) {
        int index = 0, x = 8, y = 143;

        // Build the players inventory first, building from bottom to top, right to left. The (i>0) magic handles the
        // space between the hotbar inventory and the players remaining inventory.
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 9; j++) {
                addSlot(new SlotItemHandler(playerInventory, index, x + (j * 18), (y - (i > 0 ? (4 * 18) + 4 : 0)) + (i * 18)));
                index++;
            }
        }

        // Build the filter slots, this time building from top to bottom, left to right. Starting at index 0
        index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlot(new ModGhostSlot(ghostInventory, index, 8 + (18 * j), 18 + (i * 18)));
                index++;
            }
        }
    }

    @Override
    public void clicked(int slotId, int dragType, ClickType clickTypeIn, Player player) {
        if ((slotId < this.slots.size() && slotId >= 0 && this.slots.get(slotId).getItem().getItem() instanceof CustomPickaxeItem)
                || (slotId < this.slots.size() && slotId >= 0 && this.slots.get(slotId).getItem().getItem() instanceof CustomShovelItem)
/*                || (slotId < this.slots.size() && slotId >= 0 && this.slots.get(slotId).getItem().getItem() instanceof CustomAxeItem)
                || (slotId < this.slots.size() && slotId >= 0 && this.slots.get(slotId).getItem().getItem() instanceof CustomHoeItem)*/ // For later use when all tools ect are custom and can access GUI
                || clickTypeIn == ClickType.SWAP) {
            return ;
        }

        super.clicked(slotId, dragType, clickTypeIn, player);
    }

    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots and the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 7;  // must be the number of slots you have!



    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + index);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }

}
