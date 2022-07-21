package com.gypsyhost.aventuria.custom.block.entity;

import com.gypsyhost.aventuria.custom.gui.menu.CatalyzerMenu;
import com.gypsyhost.aventuria.custom.recipe.CatalyzerRecipe;
import com.gypsyhost.aventuria.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.Random;

public class CatalyzerBlockEntity extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemHandler = new ItemStackHandler(7) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    private static final int FUEL_SLOT = 0;
    private static final int INPUT_SLOT_A = 1;
    private static final int INPUT_SLOT_B = 2;
    private static final int INPUT_SLOT_C = 3;
    private static final int INPUT_SLOT_D = 4;
    private static final int CATALYZER_SLOT = 5;
    private static final int RESULT_SLOT = 6;

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress;
    private int fuelTime = 0;
    private int maxFuelTime = 0;

    public CatalyzerBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.CATALYZER.get(), pWorldPosition, pBlockState);
        this.data = new ContainerData() {
            public int get(int index) {
                return switch (index) {
                    case 0 -> CatalyzerBlockEntity.this.progress;
                    case 1 -> CatalyzerBlockEntity.this.maxProgress;
                    case 2 -> CatalyzerBlockEntity.this.fuelTime;
                    case 3 -> CatalyzerBlockEntity.this.maxFuelTime;
                    default -> 0;
                };
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0 -> CatalyzerBlockEntity.this.progress = value;
                    case 1 -> CatalyzerBlockEntity.this.maxProgress = value;
                    case 2 -> CatalyzerBlockEntity.this.fuelTime = value;
                    case 3 -> CatalyzerBlockEntity.this.maxFuelTime = value;
                }
            }

            public int getCount() {
                return 4;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return new TextComponent("Catalyzer");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new CatalyzerMenu(pContainerId, pInventory, this, this.data);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps()  {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        tag.putInt("catalyzer.progress", progress);
        tag.putInt("catalyzer.fuelTime", fuelTime);
        tag.putInt("catalyzer.maxFuelTime", maxFuelTime);
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("catalyzer.progress");
        fuelTime = nbt.getInt("catalyzer.fuelTime");
        maxFuelTime = nbt.getInt("catalyzer.maxFuelTime");
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    private void consumeFuel() {
        if(!itemHandler.getStackInSlot(FUEL_SLOT).isEmpty()) {
            this.fuelTime = ForgeHooks.getBurnTime(this.itemHandler.extractItem(FUEL_SLOT, 1, false), RecipeType.SMELTING);
            this.maxFuelTime = this.fuelTime;
        }
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, CatalyzerBlockEntity pBlockEntity) {
        if(isConsumingFuel(pBlockEntity)) {
            pBlockEntity.fuelTime--;
        }

        if(hasRecipe(pBlockEntity)) {
            if(hasFuelInFuelSlot(pBlockEntity) && !isConsumingFuel(pBlockEntity)) {
                pBlockEntity.consumeFuel();
                setChanged(pLevel, pPos, pState);
            }
            if(isConsumingFuel(pBlockEntity)) {
                pBlockEntity.progress++;
                pBlockEntity.maxProgress = pBlockEntity.getMaxProgress();
                setChanged(pLevel, pPos, pState);
                if(pBlockEntity.progress >= pBlockEntity.maxProgress) {
                    craftItem(pBlockEntity);
                }
            }
        } else {
            pBlockEntity.resetProgress();
            setChanged(pLevel, pPos, pState);
        }
    }

    private static boolean hasFuelInFuelSlot(CatalyzerBlockEntity entity) {
        return !entity.itemHandler.getStackInSlot(FUEL_SLOT).isEmpty();
    }

    private static boolean isConsumingFuel(CatalyzerBlockEntity entity) {
        return entity.fuelTime > 0;
    }

    private static boolean hasRecipe(CatalyzerBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }
        //This checks the inventory on the gui and if any of the input slot items match a recipe from the recipe type defined then it returns true which means it can start crafting
        Optional<CatalyzerRecipe> match = level.getRecipeManager().getRecipeFor(CatalyzerRecipe.Type.INSTANCE, inventory, level);

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory) && canInsertItemIntoOutputSlot(inventory, match.get().getResultItem());
    }

    private static void craftItem(CatalyzerBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<CatalyzerRecipe> match = level.getRecipeManager().getRecipeFor(CatalyzerRecipe.Type.INSTANCE, inventory, level);

        if(match.isPresent()) {
            entity.itemHandler.extractItem(INPUT_SLOT_A,1, false);
            entity.itemHandler.extractItem(INPUT_SLOT_B,1, false);
            entity.itemHandler.extractItem(INPUT_SLOT_C,1, false);
            entity.itemHandler.extractItem(INPUT_SLOT_D,1, false);
            if(entity.itemHandler.getStackInSlot(CATALYZER_SLOT).hurt(1, new Random(), null)){
                entity.itemHandler.extractItem(CATALYZER_SLOT,1, false);
            }
            entity.itemHandler.setStackInSlot(RESULT_SLOT, new ItemStack(match.get().getResultItem().getItem(),entity.itemHandler.getStackInSlot(RESULT_SLOT).getCount() + 1));
            
            entity.resetProgress();
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack output) {
        return inventory.getItem(RESULT_SLOT).getItem() == output.getItem() || inventory.getItem(RESULT_SLOT).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(RESULT_SLOT).getMaxStackSize() > inventory.getItem(RESULT_SLOT).getCount();
    }

    public int getMaxProgress() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Optional<CatalyzerRecipe> recipe = level.getRecipeManager().getRecipeFor(CatalyzerRecipe.Type.INSTANCE, inventory, level);
        return recipe.map(CatalyzerRecipe::getMaxProgress).orElse(200);
    }

}