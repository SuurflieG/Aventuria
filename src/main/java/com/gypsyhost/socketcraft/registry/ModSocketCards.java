package com.gypsyhost.socketcraft.registry;

import com.gypsyhost.socketcraft.SocketCraft;
import com.gypsyhost.socketcraft.custom.item.socketcards.Sockets;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSocketCards {
    public static final DeferredRegister<Item> SOCKETCARD_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SocketCraft.MOD_ID);

    public static final RegistryObject<Item> BLANK = SOCKETCARD_ITEMS.register("socket_card_blank", () -> Sockets.BLANK.getCard());
    public static final RegistryObject<Item> SILK = SOCKETCARD_ITEMS.register("socket_card_silk", () -> Sockets.SILK.getCard());
    public static final RegistryObject<Item> VOID_JUNK = SOCKETCARD_ITEMS.register("socket_card_void_junk", () -> Sockets.VOID_JUNK.getCard());
    public static final RegistryObject<Item> MAGNET = SOCKETCARD_ITEMS.register("socket_card_magnet", () -> Sockets.MAGNET.getCard());
    public static final RegistryObject<Item> THREE_BY_THREE = SOCKETCARD_ITEMS.register("socket_card_three_by_three", () -> Sockets.THREE_BY_THREE.getCard());
    public static final RegistryObject<Item> FORTUNE_1 = SOCKETCARD_ITEMS.register("socket_card_fortune_1", () -> Sockets.FORTUNE_1.getCard());
    public static final RegistryObject<Item> FORTUNE_2 = SOCKETCARD_ITEMS.register("socket_card_fortune_2", () -> Sockets.FORTUNE_2.getCard());
    public static final RegistryObject<Item> FORTUNE_3 = SOCKETCARD_ITEMS.register("socket_card_fortune_3", () -> Sockets.FORTUNE_3.getCard());
    public static final RegistryObject<Item> EFFICIENCY_1 = SOCKETCARD_ITEMS.register("socket_card_efficiency_1", () -> Sockets.EFFICIENCY_1.getCard());
    public static final RegistryObject<Item> EFFICIENCY_2 = SOCKETCARD_ITEMS.register("socket_card_efficiency_2", () -> Sockets.EFFICIENCY_2.getCard());
    public static final RegistryObject<Item> EFFICIENCY_3 = SOCKETCARD_ITEMS.register("socket_card_efficiency_3", () -> Sockets.EFFICIENCY_3.getCard());
    public static final RegistryObject<Item> EFFICIENCY_4 = SOCKETCARD_ITEMS.register("socket_card_efficiency_4", () -> Sockets.EFFICIENCY_4.getCard());
    public static final RegistryObject<Item> EFFICIENCY_5 = SOCKETCARD_ITEMS.register("socket_card_efficiency_5", () -> Sockets.EFFICIENCY_5.getCard());

    public static void register(IEventBus eventBus) {
        SOCKETCARD_ITEMS.register(eventBus);
    }
}
