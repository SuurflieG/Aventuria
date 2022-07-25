package com.gypsyhost.aventuria.custom.block.entity;

import com.gypsyhost.aventuria.custom.energy.CapabilityKilowatt;
import com.gypsyhost.aventuria.custom.energy.KilowattStorage;
import com.gypsyhost.aventuria.custom.gui.menu.BasicSolarPanelMenu;
import com.gypsyhost.aventuria.custom.util.CustomBlockStateProperties;
import com.gypsyhost.aventuria.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Objects;


public class BasicSolarPanelBlockEntity extends BlockEntity implements MenuProvider {


    public KilowattStorage kilowattStorage;
    private LazyOptional<KilowattStorage> lazyEnergyHandler;

    protected final ContainerData data;
    private int maxCapacity = 10000;
    private int maxGenerate = 20;
    private int maxSend = 256;
    private int maxReceive = 256;
    private int kilowatt;

    public BasicSolarPanelBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.BASIC_SOLAR_PANEL.get(), pWorldPosition, pBlockState);
        this.kilowattStorage = new KilowattStorage(maxCapacity, maxReceive, maxSend, kilowatt, maxGenerate);
        this.lazyEnergyHandler = LazyOptional.of(() -> this.kilowattStorage);
        this.data = new ContainerData() {
            public int get(int index) {
                return switch (index) {
                    case 0 -> maxCapacity;
                    case 1 -> maxGenerate;
                    case 2 -> maxSend;
                    case 3 -> kilowatt;
                    default -> 0;
                };
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0 -> maxCapacity = value;
                    case 1 -> maxGenerate = value;
                    case 2 -> maxSend = value;
                    case 3 -> kilowatt = value;
                }
            }

            public int getCount() {
                return 4;
            }
        };
    }


    @Override
    public Component getDisplayName() {
        return new TextComponent("Basic Solar Panel");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new BasicSolarPanelMenu(pContainerId, pInventory, this, this.data);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {

        if (cap == CapabilityKilowatt.KILOWATT) {
            return lazyEnergyHandler.cast();
        }

        return super.getCapability(cap, side);
    }



    @Override
    public void onLoad() {
        super.onLoad();
        lazyEnergyHandler = LazyOptional.of(() -> kilowattStorage);
    }

    @Override
    public void invalidateCaps()  {
        super.invalidateCaps();
        lazyEnergyHandler.invalidate();
    }

    @Override
    public void load(CompoundTag tag) {
        if (tag.contains("kilowatt")) {
            kilowattStorage.deserializeNBT((CompoundTag) Objects.requireNonNull(tag.get("kilowatt")));
        }
        super.load(tag);
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        tag.put("kilowatt", kilowattStorage.serializeNBT());

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


    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, BasicSolarPanelBlockEntity pBlockEntity) {
        if(!pLevel.isClientSide){
            if (pBlockEntity.kilowattStorage.getKilowattsStored() == pBlockEntity.maxCapacity) {
                return;
            }

            if(canGenerate(pBlockEntity) && !doesSeeSky(pLevel, pPos)) {
                return;
            }

            if(canGenerate(pBlockEntity) && doesSeeSky(pLevel, pPos)) {
                pBlockEntity.kilowattStorage.generateKilowatts(pBlockEntity.maxGenerate);
                setChanged(pLevel, pPos, pState);
            }

            blockstateHasEnergy(pLevel, pPos, pBlockEntity);
            //setChanged(pLevel, pPos, pState);
            //pBlockEntity.energyStorage.extractKilowatts(pBlockEntity.maxSend, false);
        }
    }

    private static boolean canGenerate(BasicSolarPanelBlockEntity pBlockEntity) {
        return pBlockEntity.kilowattStorage.getKilowattsStored() <= pBlockEntity.kilowattStorage.getMaxKilowattStorage();
    }

    public static boolean doesSeeSky(Level pLevel, BlockPos pPos) {
        return !pLevel.isClientSide && lightLevel(pLevel, pPos) > 9 && pLevel.canSeeSky(pPos.above());
    }

    private static int lightLevel(Level pLevel, BlockPos pPos) {
        int lightLevel = pLevel.getBrightness(LightLayer.SKY, pPos) - pLevel.getSkyDarken();
        float sunAngle = pLevel.getSunAngle(1.0F);
        if (lightLevel > 0) {
            float f1 = sunAngle < 3.1415927F ? 0.0F : 6.2831855F;
            sunAngle += (f1 - sunAngle) * 0.2F;
            lightLevel = Math.round((float)lightLevel * Mth.cos(sunAngle));
        }
        return lightLevel;
    }

    private static void blockstateHasEnergy(Level pLevel, BlockPos pPos, BasicSolarPanelBlockEntity pBlockEntity) {
        BlockState blockState = pLevel.getBlockState(pPos);
        if (blockState.getValue(CustomBlockStateProperties.HAS_ENERGY) != pBlockEntity.kilowatt > 0) {
            pLevel.setBlock(pPos, blockState.setValue(CustomBlockStateProperties.HAS_ENERGY, pBlockEntity.kilowatt > 0), Block.UPDATE_ALL);
            setChanged(pLevel, pPos, blockState);
        }
    }



}