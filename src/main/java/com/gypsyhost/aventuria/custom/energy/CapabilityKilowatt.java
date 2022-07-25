package com.gypsyhost.aventuria.custom.energy;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CapabilityKilowatt implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static final Capability<IKilowattStorage> KILOWATT = CapabilityManager.get(new CapabilityToken<>(){});

    private KilowattStorage kilowattStorage = null;
    private final LazyOptional<KilowattStorage> optional = LazyOptional.of(this::createKilowattEnergy);

    private KilowattStorage createKilowattEnergy() {
        if(this.kilowattStorage == null) {
            this.kilowattStorage = new KilowattStorage();
        }

        return this.kilowattStorage;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == KILOWATT) {
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createKilowattEnergy().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createKilowattEnergy().loadNBTData(nbt);
    }

    private CapabilityKilowatt(){

    }

}
