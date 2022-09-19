package com.gypsyhost.aventuria.network.packets;


import com.gypsyhost.aventuria.custom.item.tool.CustomAxeItem;
import com.gypsyhost.aventuria.custom.item.tool.CustomHoeItem;
import com.gypsyhost.aventuria.custom.item.tool.CustomPickaxeItem;
import com.gypsyhost.aventuria.custom.item.tool.CustomShovelItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

@SuppressWarnings("InstantiationOfUtilityClass")
public class PacketChangeMiningSize {
    public PacketChangeMiningSize() {}

    public static void encode(PacketChangeMiningSize msg, FriendlyByteBuf buffer) {}
    public static PacketChangeMiningSize decode(FriendlyByteBuf buffer) { return new PacketChangeMiningSize(); }

    public static class Handler {
        public static void handle(PacketChangeMiningSize msg, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                ServerPlayer player = ctx.get().getSender();
                if (player == null)
                    return;

                ItemStack pickaxe = CustomPickaxeItem.getPickaxe(player);
                CustomPickaxeItem.changeRange(pickaxe);

                ItemStack shovel = CustomShovelItem.getShovel(player);
                CustomShovelItem.changeRange(shovel);

                ItemStack axe = CustomAxeItem.getAxe(player);
                CustomAxeItem.changeRange(axe);

                ItemStack hoe = CustomHoeItem.getHoe(player);
                CustomHoeItem.changeRange(hoe);
            });

            ctx.get().setPacketHandled(true);
        }
    }
}
