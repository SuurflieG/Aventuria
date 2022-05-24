package com.gypsyhost.socketcraft.registry;

import com.gypsyhost.socketcraft.SocketCraft;
import com.gypsyhost.socketcraft.custom.item.toolparts.pickaxe.PickaxeHead;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPickaxeHeadItems {
    public static final DeferredRegister<Item> PICKAXE_HEAD_ITEM = DeferredRegister.create(ForgeRegistries.ITEMS, SocketCraft.MOD_ID);

    public static final RegistryObject<Item> WOOD = PICKAXE_HEAD_ITEM.register("wood_pickaxe_head", () -> PickaxeHead.WOOD.getPickaxeHead());

    public static void register(IEventBus eventBus) {
        PICKAXE_HEAD_ITEM.register(eventBus);
    }
}
