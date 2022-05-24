package com.gypsyhost.socketcraft.registry;

import com.gypsyhost.socketcraft.SocketCraft;
import com.gypsyhost.socketcraft.custom.block.entity.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, SocketCraft.MOD_ID);

    public static final RegistryObject<BlockEntityType<PressBlockEntity>> PRESS = BLOCK_ENTITIES.register("press" ,
            () -> BlockEntityType.Builder.of(PressBlockEntity::new, ModBlocks.PRESS.get()).build(null));

    public static final RegistryObject<BlockEntityType<CatalyzerBlockEntity>> CATALYZER = BLOCK_ENTITIES.register("catalyzer" ,
            () -> BlockEntityType.Builder.of(CatalyzerBlockEntity::new, ModBlocks.CATALYZER.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
