package com.gypsyhost.aventuria.custom.energy;

public interface IPhotonPowerStorage {

    /**
     * Adds energy to the storage. Returns quantity of energy that was accepted.
     *
     * @param maxReceive
     *            Maximum amount of energy to be inserted.
     * @param simulate
     *            If TRUE, the insertion will only be simulated.
     * @return Amount of energy that was (or would have been, if simulated) accepted by the storage.
     */
    int receivePower(int maxReceive, boolean simulate);

    /**
     * Removes energy from the storage. Returns quantity of energy that was removed.
     *
     * @param maxExtract
     *            Maximum amount of energy to be extracted.
     * @param simulate
     *            If TRUE, the extraction will only be simulated.
     * @return Amount of energy that was (or would have been, if simulated) extracted from the storage.
     */
    int extractPower(int maxExtract, boolean simulate);

    /**
     * Returns the amount of energy currently stored.
     */
    int getPowerStored();

    /**
     * Returns the maximum amount of energy that can be stored.
     */
    int getMaxPowerCapacity();

    /**
     * Returns the maximum amount of energy that can be received.
     */
    int getMaxPowerReceive();

    /**
     * Returns the maximum amount of energy that can be extracted.
     */
    int getMaxPowerExtract();

    /**
     * Returns the maximum amount of energy that can be generated.
     */
    int getMaxPowerGenerate();

    /**
     * Returns if this storage can have energy extracted.
     * If this is false, then any calls to extractPower will return 0.
     */
    boolean canExtract();

    /**
     * Used to determine if this storage can receive energy.
     * If this is false, then any calls to receivePower will return 0.
     */
    boolean canReceive();
}
