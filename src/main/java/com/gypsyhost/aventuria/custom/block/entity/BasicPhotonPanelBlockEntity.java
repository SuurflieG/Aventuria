package com.gypsyhost.aventuria.custom.block.entity;

import com.gypsyhost.aventuria.custom.energy.CapabilityPhotonPower;
import com.gypsyhost.aventuria.custom.energy.PhotonPowerStorage;
import com.gypsyhost.aventuria.custom.gui.menu.BasicPhotonPanelMenu;
import com.gypsyhost.aventuria.registry.ModBlockEntities;
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
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Objects;


public class BasicPhotonPanelBlockEntity extends BlockEntity implements MenuProvider {


    public PhotonPowerStorage energyStorage;
    private LazyOptional<PhotonPowerStorage> lazyEnergyHandler;

    protected final ContainerData data;
    private int maxCapacity = 512;
    private int maxGenerate = 256;
    private int maxSend = 256;
    private int energy;

    public BasicPhotonPanelBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.BASIC_PHOTON_PANEL.get(), pWorldPosition, pBlockState);
        this.energyStorage = new PhotonPowerStorage(maxCapacity, 0, maxSend, energy, maxGenerate);
        this.lazyEnergyHandler = LazyOptional.of(() -> this.energyStorage);
        this.data = new ContainerData() {
            public int get(int index) {
                return switch (index) {
                    case 0 -> BasicPhotonPanelBlockEntity.this.maxCapacity;
                    case 1 -> BasicPhotonPanelBlockEntity.this.maxGenerate;
                    case 2 -> BasicPhotonPanelBlockEntity.this.maxSend;
                    case 3 -> BasicPhotonPanelBlockEntity.this.energy;
                    default -> 0;
                };
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0 -> BasicPhotonPanelBlockEntity.this.maxCapacity = value;
                    case 1 -> BasicPhotonPanelBlockEntity.this.maxGenerate = value;
                    case 2 -> BasicPhotonPanelBlockEntity.this.maxSend = value;
                    case 3 -> BasicPhotonPanelBlockEntity.this.energy = value;
                }
            }

            public int getCount() {
                return 4;
            }
        };
    }


    @Override
    public Component getDisplayName() {
        return new TextComponent("Basic Photon Panel");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new BasicPhotonPanelMenu(pContainerId, pInventory, this, this.data);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {

        if (cap == CapabilityPhotonPower.PHOTON_ENERGY) {
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
    public void load(CompoundTag tag) {
        if (tag.contains("Energy")) {
            energyStorage.deserializeNBT((CompoundTag) Objects.requireNonNull(tag.get("Energy")));
        }
        super.load(tag);
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        tag.put("Energy", energyStorage.serializeNBT());

        CompoundTag infoTag = new CompoundTag();
        tag.put("Info", infoTag);
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


    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, BasicPhotonPanelBlockEntity pBlockEntity) {
        if(canGenerate(pBlockEntity) && doesSeeSky(pLevel, pPos)) {
            pBlockEntity.energyStorage.generateEnergy(pBlockEntity.maxGenerate);
            setChanged(pLevel, pPos, pState);
        }

        if(canGenerate(pBlockEntity) && !doesSeeSky(pLevel, pPos)) {
            return;
        }

        if (pBlockEntity.energyStorage.getPowerStored() == pBlockEntity.maxCapacity) {
            return;
        }
        pBlockEntity.energyStorage.extractPower(pBlockEntity.maxSend, false);
    }

    private static boolean canGenerate(BasicPhotonPanelBlockEntity pBlockEntity) {
        return pBlockEntity.energyStorage.getPowerStored() <= pBlockEntity.energyStorage.getMaxPowerCapacity();
    }

    public static boolean doesSeeSky(Level pLevel, BlockPos pPos) {
        //return !pLevel.isClientSide && pLevel.getBrightness(LightLayer.SKY, pPos) > 0 && pLevel.canSeeSky(pPos.above());
        return true;
    }



}