package com.gypsyhost.aventuria.network.packets;

import com.gypsyhost.aventuria.custom.block.entity.UpgradeStationBlockEntity;
import com.gypsyhost.aventuria.custom.gui.UpgradeStationCommands;
import com.gypsyhost.aventuria.custom.gui.menu.UpgradeStationMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketExtractUpgrade {

    private final BlockPos pos;
    private final String upgrade;
    private final int nameLength;

    public PacketExtractUpgrade(BlockPos blockPos, String upgrade, int nameLength) {
        this.pos = blockPos;
        this.upgrade = upgrade;
        this.nameLength = nameLength;
    }

    public static void encode(PacketExtractUpgrade msg, FriendlyByteBuf buffer) {
        buffer.writeInt(msg.nameLength);
        buffer.writeBlockPos(msg.pos);
        buffer.writeUtf(msg.upgrade);
    }

    public static PacketExtractUpgrade decode(FriendlyByteBuf buffer) {
        int strLength = buffer.readInt();
        return new PacketExtractUpgrade(buffer.readBlockPos(), buffer.readUtf(strLength), strLength);
    }

    public static class Handler {
        public static void handle(PacketExtractUpgrade msg, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                ServerPlayer player = ctx.get().getSender();
                if (player == null) return;

                Level world = player.level;
                BlockPos pos = msg.pos;

                BlockEntity te = world.getBlockEntity(pos);
                if (!(te instanceof UpgradeStationBlockEntity)) return;
                UpgradeStationMenu container = ((UpgradeStationBlockEntity) te).getContainer(player);

                UpgradeStationCommands.extractButton(container, player, msg.upgrade);
            });

            ctx.get().setPacketHandled(true);
        }
    }
}
