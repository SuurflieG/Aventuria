package com.gypsyhost.aventuria.world.poi;

import com.gypsyhost.aventuria.Aventuria;
import com.gypsyhost.aventuria.registry.ModBlocks;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPOIs {
    public static final DeferredRegister<PoiType> POI = DeferredRegister.create(ForgeRegistries.POI_TYPES, Aventuria.MOD_ID);

    public static final RegistryObject<PoiType> AVENTURIA_PORTAL =
            POI.register("aventuria_portal", () -> new PoiType("aventuria_portal",
                    PoiType.getBlockStates(ModBlocks.AVENTURIA_PORTAL.get()), 0, 1));


    public static void register(IEventBus eventBus) {
        POI.register(eventBus);
    }
}