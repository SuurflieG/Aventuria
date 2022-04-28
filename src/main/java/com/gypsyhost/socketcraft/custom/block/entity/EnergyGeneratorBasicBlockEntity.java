package com.gypsyhost.socketcraft.custom.block.entity;

import com.gypsyhost.socketcraft.config.SocketCraftCommonConfigs;
import com.gypsyhost.socketcraft.custom.gui.energygeneratorbasic.EnergyGeneratorBasicMenu;
import com.gypsyhost.socketcraft.custom.util.energy.CustomEnergyStorage;
import com.gypsyhost.socketcraft.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public class EnergyGeneratorBasicBlockEntity extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemHandler = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    public CustomEnergyStorage energyStorage;
    private LazyOptional<CustomEnergyStorage> lazyEnergyHandler;

    private static final int FUEL_SLOT = 0;

    protected final ContainerData data;
    private int maxCapacity = SocketCraftCommonConfigs.ENERGY_GENERATOR_BASIC_CAPACITY.get();
    private int maxGenerate = SocketCraftCommonConfigs.ENERGY_GENERATOR_BASIC_GENERATE.get();
    private int maxSend = SocketCraftCommonConfigs.ENERGY_GENERATOR_BASIC_SEND.get();
    private int fuelProgress = 0;
    private int maxFuelProgress = 0;

    public EnergyGeneratorBasicBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.ENERGY_GENERATOR_BASIC.get(), pWorldPosition, pBlockState);
        this.energyStorage = new CustomEnergyStorage(maxCapacity, 0, maxSend, maxGenerate,0);
        this.lazyEnergyHandler = LazyOptional.of(() -> this.energyStorage);
        this.data = new ContainerData() {
            public int get(int index) {
                return switch (index) {
                    case 0 -> EnergyGeneratorBasicBlockEntity.this.maxCapacity;
                    case 1 -> EnergyGeneratorBasicBlockEntity.this.maxGenerate;
                    case 2 -> EnergyGeneratorBasicBlockEntity.this.maxSend;
                    case 3 -> EnergyGeneratorBasicBlockEntity.this.fuelProgress;
                    case 4 -> EnergyGeneratorBasicBlockEntity.this.maxFuelProgress;
                    default -> 0;
                };
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0 -> EnergyGeneratorBasicBlockEntity.this.maxCapacity = value;
                    case 1 -> EnergyGeneratorBasicBlockEntity.this.maxGenerate = value;
                    case 2 -> EnergyGeneratorBasicBlockEntity.this.maxSend = value;
                    case 3 -> EnergyGeneratorBasicBlockEntity.this.fuelProgress = value;
                    case 4 -> EnergyGeneratorBasicBlockEntity.this.maxFuelProgress = value;
                }
            }

            public int getCount() {
                return 5;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return new TextComponent("Basic Generator");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new EnergyGeneratorBasicMenu(pContainerId, pInventory, this, this.data);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }
        if (cap == CapabilityEnergy.ENERGY) {
            return lazyEnergyHandler.cast();
        }

        return super.getCapability(cap, side);
    }



    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
        lazyEnergyHandler = LazyOptional.of(() -> energyStorage);
    }

    @Override
    public void invalidateCaps()  {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
        lazyEnergyHandler.invalidate();
    }

    @Override
    public void saveAdditional(@NotNull CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        lazyEnergyHandler.ifPresent(h -> tag.put("egb.energy", h.serializeNBT()));
        tag.putInt("egb.fuelProgress", fuelProgress);
        tag.putInt("egb.maxFuelProgress", maxFuelProgress);
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        lazyEnergyHandler.ifPresent(h -> h.deserializeNBT(nbt.getCompound("egb.energy")));
        fuelProgress = nbt.getInt("egb.fuelProgress");
        maxFuelProgress = nbt.getInt("egb.maxFuelProgress");
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

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, EnergyGeneratorBasicBlockEntity pBlockEntity) {
        if(isConsumingFuel(pBlockEntity) && canGenerate(pBlockEntity)) {
            pBlockEntity.fuelProgress--;
        }
        if(hasFuelInFuelSlot(pBlockEntity) && !isConsumingFuel(pBlockEntity) && canGenerate(pBlockEntity)) {
            pBlockEntity.consumeFuel();
            setChanged(pLevel, pPos, pState);
        }
        if (canGenerate(pBlockEntity) && isConsumingFuel(pBlockEntity)){
            pBlockEntity.energyStorage.generateEnergy(pBlockEntity.energyStorage.getEnergyStored());
        }
        //isPowered(pLevel, pPos, pBlockEntity);
        if (pBlockEntity.energyStorage.getEnergyStored() == pBlockEntity.maxCapacity) {
            return;
        }
        pBlockEntity.outputEnergy();
    }



    private static void isPowered(Level pLevel, BlockPos pPos, EnergyGeneratorBasicBlockEntity pBlockEntity) {
        BlockState blockState = pLevel.getBlockState(pPos);
        if (blockState.getValue(BlockStateProperties.POWERED) != isConsumingFuel(pBlockEntity) ) {
            pLevel.setBlock(pPos, blockState.setValue(BlockStateProperties.POWERED, true), Block.UPDATE_ALL);
        }
        else if
        (blockState.getValue(BlockStateProperties.POWERED) || pBlockEntity.fuelProgress == 0 || pBlockEntity.energyStorage.getMaxEnergyStored() == pBlockEntity.maxCapacity) {
            pLevel.setBlock(pPos, blockState.setValue(BlockStateProperties.POWERED, false), Block.UPDATE_ALL);
        }
    }

    private static boolean canGenerate(EnergyGeneratorBasicBlockEntity pBlockEntity) {
        return pBlockEntity.energyStorage.getEnergyStored() <= pBlockEntity.energyStorage.getMaxEnergyStored();
    }

    private void consumeFuel() {
        if(!itemHandler.getStackInSlot(FUEL_SLOT).isEmpty()) {
            this.fuelProgress = ForgeHooks.getBurnTime(this.itemHandler.extractItem(FUEL_SLOT, 1, false), RecipeType.SMELTING);
            this.maxFuelProgress = fuelProgress;
        }
    }

    private static boolean hasFuelInFuelSlot(EnergyGeneratorBasicBlockEntity entity) {
        return !entity.itemHandler.getStackInSlot(FUEL_SLOT).isEmpty();
    }

    private static boolean isConsumingFuel(EnergyGeneratorBasicBlockEntity entity) {
        return entity.fuelProgress > 0;
    }

    public void outputEnergy() { // This is not my own don't fully understand it
        if (this.energyStorage.getEnergyStored() >= maxSend && this.energyStorage.canExtract()) {
            for (final var direction : Direction.values()) {
                final BlockEntity pBlockEntity = this.level.getBlockEntity(this.worldPosition.relative(direction));
                if (pBlockEntity == null) {
                    continue;
                }

                pBlockEntity.getCapability(CapabilityEnergy.ENERGY, direction.getOpposite()).ifPresent(storage -> {
                    if (pBlockEntity != this && storage.getEnergyStored() < storage.getMaxEnergyStored()) {
                        final int toSend = EnergyGeneratorBasicBlockEntity.this.energyStorage.extractEnergy(maxSend, false);
                        final int received = storage.receiveEnergy(toSend, false);

                        EnergyGeneratorBasicBlockEntity.this.energyStorage.setEnergy(EnergyGeneratorBasicBlockEntity.this.energyStorage.getEnergyStored() + toSend - received);
                    }
                });
            }
        }
    }
}

