package com.gypsyhost.aventuria.custom.gui.menu;

import com.gypsyhost.aventuria.custom.gui.slot.ModWatchedSlot;
import com.gypsyhost.aventuria.custom.gui.UpgradeStationCommands;
import com.gypsyhost.aventuria.custom.item.armor.CustomArmorItem;
import com.gypsyhost.aventuria.custom.item.tool.*;
import com.gypsyhost.aventuria.custom.item.upgradecards.Upgrade;
import com.gypsyhost.aventuria.custom.item.upgradecards.UpgradeCardItem;
import com.gypsyhost.aventuria.custom.item.upgradecards.UpgradeTools;
import com.gypsyhost.aventuria.registry.ModBlocks;
import com.gypsyhost.aventuria.registry.ModMenuTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import java.util.ArrayList;
import java.util.List;

public class UpgradeStationMenu extends AbstractContainerMenu {

    private final BlockEntity blockEntity;
    private final IItemHandler playerInventory;
    private List<Upgrade> upgradeCache = new ArrayList<>();


    public UpgradeStationMenu(int windowId, Inventory playerInventory, FriendlyByteBuf extraData) {
        super(ModMenuTypes.UPGRADE_STATION_MENU.get(), windowId);

        this.blockEntity = Minecraft.getInstance().level.getBlockEntity(extraData.readBlockPos());
        this.playerInventory = new InvWrapper(playerInventory);

        setupContainerSlots();
        layoutPlayerInventorySlots(8, 84);
    }

    public UpgradeStationMenu(int windowId, Level world, BlockPos pos, Inventory playerInventory) {
        super(ModMenuTypes.UPGRADE_STATION_MENU.get(), windowId);
        this.blockEntity = world.getBlockEntity(pos);
        this.playerInventory = new InvWrapper(playerInventory);

        setupContainerSlots();
        layoutPlayerInventorySlots(10, 70);
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(getBE().getLevel(), blockEntity.getBlockPos()), pPlayer, ModBlocks.UPGRADE_STATION.get());
    }

    private void setupContainerSlots() {
        this.getBE().getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
            addSlot(new ModWatchedSlot(h, 0,  7, 61, this::updateUpgradeCache));
        });
    }


    // Need to add the other custom tools, weapon and armor in this check in order for it to show the installed upgrades in the bench
    private void updateUpgradeCache(int index) {
        ItemStack stack = this.getSlot(index).getItem();
        if((stack.isEmpty() && !upgradeCache.isEmpty())
                || !(stack.getItem() instanceof CustomPickaxeItem
                || stack.getItem() instanceof CustomShovelItem
                || stack.getItem() instanceof CustomAxeItem
                || stack.getItem() instanceof CustomHoeItem
                || stack.getItem() instanceof CustomSwordItem
                || stack.getItem() instanceof CustomArmorItem)) {
            upgradeCache.clear();
            return;
        }

        // Purge and set cache
        upgradeCache.clear();
        upgradeCache = UpgradeTools.getUpgrades(stack);
    }

    public List<Upgrade> getUpgradesCache() {
        return upgradeCache;
    }

    public BlockEntity getBE() {
        return this.blockEntity;
    }

    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        // Player inventory
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);

        // Hotbar
        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            itemstack = stack.copy();
            if (index == 0) {
                if (!this.moveItemStackTo(stack, 1, this.getItems().size(), true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stack, itemstack);
            } else {
                if (stack.getItem() instanceof CustomPickaxeItem
                        || stack.getItem() instanceof CustomShovelItem
                        || stack.getItem() instanceof CustomAxeItem
                        || stack.getItem() instanceof CustomHoeItem
                        || stack.getItem() instanceof CustomSwordItem
                        || stack.getItem() instanceof CustomArmorItem) {
                    if (!this.moveItemStackTo(stack, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (stack.getItem() instanceof UpgradeCardItem) {
                    // Push the item right into the modification table.
                    if( UpgradeStationCommands.insertButton(this, stack) ) {
                        int maxSize = Math.min(slot.getMaxStackSize(), stack.getMaxStackSize());
                        int remove = maxSize - itemstack.getCount();
                        stack.shrink(remove == 0 ? 1 : remove);
                        updateUpgradeCache(0);
                    }
                    else
                        return ItemStack.EMPTY;
                } else if (index < 29) {
                    if (!this.moveItemStackTo(stack, 29, 37, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 38 && !this.moveItemStackTo(stack, 1, 29, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, stack);
        }

        return itemstack;
    }
}
