package com.gypsyhost.socketcraft.registry;

import com.gypsyhost.socketcraft.SocketCraft;
import com.gypsyhost.socketcraft.custom.item.socketcards.Sockets;
import com.gypsyhost.socketcraft.custom.item.toolhandle.HandleItem;
import com.gypsyhost.socketcraft.custom.item.toolhandle.Handles;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModHandleItems {
    public static final DeferredRegister<Item> TOOL_HANDLES = DeferredRegister.create(ForgeRegistries.ITEMS, SocketCraft.MOD_ID);

    public static final RegistryObject<Item> WOOD = TOOL_HANDLES.register("wood_handle", () -> Handles.WOOD.getHandle());

    public static void register(IEventBus eventBus) {
        TOOL_HANDLES.register(eventBus);
    }
}
