package com.gypsyhost.aventuria.network.packets;


import com.gypsyhost.aventuria.custom.item.tool.CustomShovelItem;
import com.gypsyhost.aventuria.custom.item.upgradecards.Upgrade;
import com.gypsyhost.aventuria.custom.item.upgradecards.UpgradeTools;
import com.gypsyhost.aventuria.custom.item.tool.CustomPickaxeItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketUpdateUpgrade {
    private final String upgrade;

    public PacketUpdateUpgrade(String sockets) {
        this.upgrade = sockets;
    }

    public static void encode(PacketUpdateUpgrade msg, FriendlyByteBuf buffer) {
        buffer.writeUtf(msg.upgrade);
    }

    public static PacketUpdateUpgrade decode(FriendlyByteBuf buffer) {
        return new PacketUpdateUpgrade(buffer.readUtf(100));
    }

    public static class Handler {
        public static void handle(PacketUpdateUpgrade msg, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                ServerPlayer player = ctx.get().getSender();
                if (player == null)
                    return;

                Upgrade upgrade = UpgradeTools.getUpgradesByName(msg.upgrade);
                if( upgrade == null )
                    return;

                ItemStack stack = CustomPickaxeItem.getPickaxe(player);
                UpgradeTools.updateUpgrade(stack, upgrade);
                ItemStack stack1 = CustomShovelItem.getShovel(player);
                UpgradeTools.updateUpgrade(stack1, upgrade);
            });

            ctx.get().setPacketHandled(true);
        }
    }
}
