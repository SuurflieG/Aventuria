package com.gypsyhost.socketcraft.custom.block.entity;

import com.gypsyhost.socketcraft.custom.gui.energystoragebasic.EnergyStorageBasicMenu;
import com.gypsyhost.socketcraft.custom.util.energy.CustomEnergyStorage;
import com.gypsyhost.socketcraft.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;


public class EnergyStorageBasicBlockEntity extends BlockEntity implements MenuProvider {


    public CustomEnergyStorage energyStorage;
    private LazyOptional<CustomEnergyStorage> lazyEnergyHandler;

    protected final ContainerData data;

    private int maxCapacity = 10000;
    private int maxExtract = 100;
    private int maxReceive = 500;

    public EnergyStorageBasicBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.ENERGY_STORAGE_BASIC.get(), pWorldPosition, pBlockState);
        this.energyStorage = new CustomEnergyStorage(maxCapacity, maxReceive, maxExtract,0);
        this.data = new ContainerData() {
            public int get(int index) {
                return switch (index) {
                    case 0 -> EnergyStorageBasicBlockEntity.this.maxCapacity;
                    case 1 -> EnergyStorageBasicBlockEntity.this.maxExtract;
                    case 2 -> EnergyStorageBasicBlockEntity.this.maxReceive;
                    default -> 0;
                };
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0 -> EnergyStorageBasicBlockEntity.this.maxCapacity = value;
                    case 1 -> EnergyStorageBasicBlockEntity.this.maxExtract = value;
                    case 2 -> EnergyStorageBasicBlockEntity.this.maxReceive = value;
                }
            }

            public int getCount() {
                return 3;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return new TextComponent("Basic Energy Storage");
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {
        if (cap == CapabilityEnergy.ENERGY) {
            return lazyEnergyHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyEnergyHandler = LazyOptional.of(() -> energyStorage);
    }

    @Override
    public void invalidateCaps()  {
        super.invalidateCaps();
        lazyEnergyHandler.invalidate();
    }

    @Override
    public void saveAdditional(@NotNull CompoundTag tag) {
        lazyEnergyHandler.ifPresent(h -> tag.put("esb.energy", h.serializeNBT()));
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        lazyEnergyHandler.ifPresent(h -> h.deserializeNBT(nbt.getCompound("esb.energy")));
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this, entity -> this.getUpdateTag());
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag compoundTag = new CompoundTag();
        saveAdditional(compoundTag);
        return compoundTag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        load(tag);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        load(pkt.getTag());
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new EnergyStorageBasicMenu(pContainerId, pInventory, this, this.data);
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, EnergyStorageBasicBlockEntity pBlockEntity) {
        if (canExtract(pBlockEntity))
            setChanged(pLevel, pPos, pState);
        pBlockEntity.outputEnergy();
    }

    private static boolean canReceive(EnergyStorageBasicBlockEntity pBlockEntity) {
        return pBlockEntity.energyStorage.getEnergyStored() <= pBlockEntity.energyStorage.getMaxEnergyStored();
    }

    private static boolean canExtract(EnergyStorageBasicBlockEntity pBlockEntity) {
        return pBlockEntity.energyStorage.getEnergyStored() > pBlockEntity.maxExtract;
    }

    public void outputEnergy() {
        if (this.energyStorage.getEnergyStored() >= maxExtract) {
            for (final var direction : Direction.values()) {
                final BlockEntity pBlockEntity = this.level.getBlockEntity(this.worldPosition.relative(direction));
                if (pBlockEntity == null) {
                    continue;
                }

                pBlockEntity.getCapability(CapabilityEnergy.ENERGY, direction.getOpposite()).ifPresent(storage -> {
                    if (pBlockEntity != this && storage.getEnergyStored() < storage.getMaxEnergyStored()) {
                        final int toSend = EnergyStorageBasicBlockEntity.this.energyStorage.extractEnergy(maxExtract, false);
                        final int received = storage.receiveEnergy(toSend, false);

                        EnergyStorageBasicBlockEntity.this.energyStorage.setEnergy(EnergyStorageBasicBlockEntity.this.energyStorage.getEnergyStored() + toSend - received);
                    }
                });
            }
        }
    }

}
