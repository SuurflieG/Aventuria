package com.gypsyhost.aventuria.custom.block.entity;

import com.gypsyhost.aventuria.custom.gui.menu.UpgradeStationMenu;
import com.gypsyhost.aventuria.custom.item.armor.CustomArmorItem;
import com.gypsyhost.aventuria.custom.item.tool.*;
import com.gypsyhost.aventuria.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public class UpgradeStationBlockEntity extends BlockEntity implements MenuProvider {
    public final LazyOptional<IItemHandler> handler = LazyOptional.of(this::createHandler);

    public UpgradeStationBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.UPGRADE_STATION.get(), pWorldPosition, pBlockState);
    }

    private IItemHandler createHandler() {
        return new ItemStackHandler(2) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return slot == 0 &&
                        stack.getItem() instanceof CustomPickaxeItem
                        || stack.getItem() instanceof CustomShovelItem
                        || stack.getItem() instanceof CustomAxeItem
                        || stack.getItem() instanceof CustomHoeItem
                        || stack.getItem() instanceof CustomSwordItem
                        || stack.getItem() instanceof CustomArmorItem;
            }
        };
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag);
        return tag;
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        CompoundTag invTag = tag.getCompound("inv");
        handler.ifPresent(h -> ((INBTSerializable<CompoundTag>) h).deserializeNBT(invTag));
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        handler.ifPresent(h -> {
            CompoundTag compound = ((INBTSerializable<CompoundTag>) h).serializeNBT();
            tag.put("inv", compound);
        });
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public Component getDisplayName() {
        return new TextComponent("Upgrade Station");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory pInventory, Player playerEntity) {
        return new UpgradeStationMenu(i, level, worldPosition, pInventory);
    }

    public UpgradeStationMenu getContainer(Player playerIn) {
        return new UpgradeStationMenu(0, playerIn.level, this.worldPosition, playerIn.getInventory());
    }
}
