package com.gypsyhost.aventuria.custom.energy;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public class KilowattStorage implements IKilowattStorage, INBTSerializable<CompoundTag> {

    private static final String KEY = "kilowatt";
    private static final String KEY2 = "capacity";
    private static final String KEY3 = "maxReceive";
    private static final String KEY4 = "maxExtract";
    private static final String KEY5 = "maxGenerate";
    public int kilowatt;
    public int capacity;
    public int maxReceive;
    public int maxExtract;
    public int maxGenerate;

    public KilowattStorage(){

    }

    public KilowattStorage(int capacity, int maxReceive, int maxExtract, int kilowatt, int maxGenerate) {
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
        this.maxGenerate = maxGenerate;
        this.kilowatt = Math.max(0 , Math.min(capacity, kilowatt));
    }

    @Override
    public int receiveKilowatts(int maxReceive, boolean simulate) {
        if (!canReceive())
            return 0;

        int powerReceived = Math.min(capacity - kilowatt, Math.min(this.maxReceive, maxReceive));
        if (!simulate)
            kilowatt += powerReceived;
        return powerReceived;
    }

    @Override
    public int extractKilowatts(int maxExtract, boolean simulate) {
        if (!canExtract())
            return 0;

        int powerExtracted = Math.min(kilowatt, Math.min(this.maxExtract, maxExtract));
        if (!simulate)
            kilowatt -= powerExtracted;
        return powerExtracted;
    }

    public int generateKilowatts(int kilowatt) {
        this.kilowatt += maxGenerate;
        return kilowatt;
    }

    public void setKilowattsAmount(int kilowatt) {
        this.kilowatt = kilowatt;
    }


    @Override
    public int getKilowattsStored() {
        return kilowatt;
    }

    @Override
    public int getMaxKilowattStorage() {
        return capacity;
    }

    @Override
    public int getMaxKilowattReceive() {
        return maxReceive;
    }

    @Override
    public int getMaxKilowattExtract() {
        return maxExtract;
    }

    @Override
    public int getMaxKilowattGenerate() {
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
        tag.putInt(KEY, this.kilowatt);
        tag.putInt(KEY2, this.capacity);
        tag.putInt(KEY3, this.maxReceive);
        tag.putInt(KEY4, this.maxExtract);
        tag.putInt(KEY5, this.maxGenerate);
        return tag;
    }



    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.kilowatt = nbt.getInt(KEY);
        this.capacity = nbt.getInt(KEY2);
        this.maxReceive = nbt.getInt(KEY3);
        this.maxExtract = nbt.getInt(KEY4);
        this.maxGenerate = nbt.getInt(KEY5);
    }

    @Override
    public String toString() {
        return "CustomEnergyStorage{" +
                "kilowatt=" + kilowatt +
                ", capacity=" + capacity +
                ", maxReceive=" + maxReceive +
                ", maxSend=" + maxExtract +
                ", maxGenerate=" + maxGenerate +
                '}';
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("kilowatt", kilowatt);
        nbt.putInt("capacity", capacity);
        nbt.putInt("maxReceive", maxReceive);
        nbt.putInt("maxExtract", maxExtract);
        nbt.putInt("maxGenerate", maxGenerate);
    }

    public void loadNBTData(CompoundTag nbt) {
        kilowatt = nbt.getInt("kilowatt");
        capacity = nbt.getInt("capacity");
        maxReceive = nbt.getInt("maxReceive");
        maxExtract = nbt.getInt("maxExtract");
        maxGenerate = nbt.getInt("maxGenerate");
    }
}
