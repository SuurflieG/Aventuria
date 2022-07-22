package com.gypsyhost.aventuria.custom.energy;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.util.INBTSerializable;

public class PhotonPowerStorage implements IPhotonPowerStorage , INBTSerializable<CompoundTag> {

    private static final String KEY = "energy";
    public int power;
    public int capacity;
    public int maxReceive;
    public int maxExtract;
    public int maxGenerate;

    public PhotonPowerStorage(){

    }

    public PhotonPowerStorage(int capacity, int maxReceive, int maxExtract, int energy, int maxGenerate) {
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
        this.power = Math.max(0 , Math.min(capacity, energy));
    }

    @Override
    public int receivePower(int maxReceive, boolean simulate) {
        if (!canReceive())
            return 0;

        int energyReceived = Math.min(capacity - power, Math.min(this.maxReceive, maxReceive));
        if (!simulate)
            power += energyReceived;
        return energyReceived;
    }

    @Override
    public int extractPower(int maxExtract, boolean simulate) {
        if (!canExtract())
            return 0;

        int energyExtracted = Math.min(power, Math.min(this.maxExtract, maxExtract));
        if (!simulate)
            power -= energyExtracted;
        return energyExtracted;
    }

    public int generateEnergy(int energy) {
        this.power += maxGenerate;
        return energy;
    }

    public void setPower(int power) {
        this.power = power;
    }


    @Override
    public int getPowerStored() {
        return power;
    }

    @Override
    public int getMaxPowerCapacity() {
        return capacity;
    }

    @Override
    public int getMaxPowerReceive() {
        return maxReceive;
    }

    @Override
    public int getMaxPowerExtract() {
        return maxExtract;
    }

    @Override
    public int getMaxPowerGenerate() {
        return maxReceive;
    }

    @Override
    public boolean canExtract() {
        return this.maxExtract > 0;
    }

    @Override
    public boolean canReceive() {
        return this.maxReceive > 0;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt(KEY, this.power);
        return tag;
    }



    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.power = nbt.getInt(KEY);
    }

    @Override
    public String toString() {
        return "CustomEnergyStorage{" +
                "energy=" + power +
                ", capacity=" + capacity +
                ", maxReceive=" + maxReceive +
                ", maxSend=" + maxExtract +
                ", maxGenerate=" + maxGenerate +
                '}';
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("power", power);
        nbt.putInt("capacity", capacity);
        nbt.putInt("maxReceive", maxReceive);
        nbt.putInt("maxExtract", maxExtract);
        nbt.putInt("maxGenerate", maxGenerate);
    }

    public void loadNBTData(CompoundTag nbt) {
        power = nbt.getInt("power");
        capacity = nbt.getInt("capacity");
        maxReceive = nbt.getInt("maxReceive");
        maxExtract = nbt.getInt("maxExtract");
        maxGenerate = nbt.getInt("maxGenerate");
    }
}
