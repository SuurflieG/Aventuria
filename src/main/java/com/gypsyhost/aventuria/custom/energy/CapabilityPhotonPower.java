package com.gypsyhost.aventuria.custom.energy;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CapabilityPhotonPower implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static final Capability<IPhotonPowerStorage> PHOTON_ENERGY = CapabilityManager.get(new CapabilityToken<>(){});

    private PhotonPowerStorage power = null;
    private final LazyOptional<PhotonPowerStorage> optional = LazyOptional.of(this::createPower);

    private PhotonPowerStorage createPower() {
        if(this.power == null) {
            this.power = new PhotonPowerStorage();
        }

        return this.power;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == PHOTON_ENERGY) {
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPower().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPower().loadNBTData(nbt);
    }

    private CapabilityPhotonPower(){

    }

}
