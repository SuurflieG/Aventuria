package com.gypsyhost.aventuria.custom.energy;

public interface IKilowattStorage {

    /**
     * Adds Kilowatts to the storage. Returns quantity of Kilowatts that was accepted.
     *
     * @param maxReceive
     *            Maximum amount of Kilowatts to be inserted.
     * @param simulate
     *            If TRUE, the insertion will only be simulated.
     * @return Amount of Kilowatts that was (or would have been, if simulated) accepted by the storage.
     */
    int receiveKilowatts(int maxReceive, boolean simulate);

    /**
     * Removes Kilowatts from the storage. Returns quantity of Kilowatts that was removed.
     *
     * @param maxExtract
     *            Maximum amount of Kilowatts to be extracted.
     * @param simulate
     *            If TRUE, the extraction will only be simulated.
     * @return Amount of Kilowatts that was (or would have been, if simulated) extracted from the storage.
     */
    int extractKilowatts(int maxExtract, boolean simulate);

    /**
     * Returns the amount of Kilowatts currently stored.
     */
    int getKilowattsStored();

    /**
     * Returns the maximum amount of Kilowatts that can be stored.
     */
    int getMaxKilowattStorage();

    /**
     * Returns the maximum amount of Kilowatts that can be received.
     */
    int getMaxKilowattReceive();

    /**
     * Returns the maximum amount of Kilowatts that can be extracted.
     */
    int getMaxKilowattExtract();

    /**
     * Returns the maximum amount of Kilowatts that can be generated.
     */
    int getMaxKilowattGenerate();

    /**
     * Returns if this storage can have Kilowatts extracted.
     * If this is false, then any calls to extractKilowatts will return 0.
     */
    boolean canExtract();

    /**
     * Used to determine if this storage can receive Kilowatts.
     * If this is false, then any calls to receiveKilowatts will return 0.
     */
    boolean canReceive();
}
