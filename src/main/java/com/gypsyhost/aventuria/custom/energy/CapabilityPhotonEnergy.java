package com.gypsyhost.aventuria.custom.energy;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;

public class CapabilityPhotonEnergy {

    public static final Capability<IPhotonEnergyStorage> PHOTON_ENERGY = CapabilityManager.get(new CapabilityToken<>(){});;

    public static void register(RegisterCapabilitiesEvent event)
    {
        event.register(IPhotonEnergyStorage.class);
    }
}
