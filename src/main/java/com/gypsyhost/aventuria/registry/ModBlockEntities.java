package com.gypsyhost.aventuria.registry;

import com.gypsyhost.aventuria.Aventuria;
import com.gypsyhost.aventuria.custom.block.entity.CatalyzerBlockEntity;
import com.gypsyhost.aventuria.custom.block.entity.PressBlockEntity;
import com.gypsyhost.aventuria.custom.block.entity.UpgradeStationBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Aventuria.MOD_ID);

    public static final RegistryObject<BlockEntityType<PressBlockEntity>> PRESS = BLOCK_ENTITIES.register("press" ,
            () -> BlockEntityType.Builder.of(PressBlockEntity::new, ModBlocks.PRESS.get()).build(null));

    public static final RegistryObject<BlockEntityType<CatalyzerBlockEntity>> CATALYZER = BLOCK_ENTITIES.register("catalyzer" ,
            () -> BlockEntityType.Builder.of(CatalyzerBlockEntity::new, ModBlocks.CATALYZER.get()).build(null));

    public static final RegistryObject<BlockEntityType<UpgradeStationBlockEntity>> UPGRADE_STATION = BLOCK_ENTITIES.register("upgrade_station" ,
            () -> BlockEntityType.Builder.of(UpgradeStationBlockEntity::new, ModBlocks.UPGRADE_STATION.get()).build(null));

/*    public static final RegistryObject<BlockEntityType<RenderBlockEntity>> RENDER_BLOCK_TILE = BLOCK_ENTITIES.register("render_block_tile" ,
            () -> BlockEntityType.Builder.of(RenderBlockEntity::new, ModBlocks.RENDER_BLOCK.get()).build(null));*/


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
