package com.gypsyhost.aventuria.network.packets;


import com.gypsyhost.aventuria.custom.block.entity.UpgradeStationBlockEntity;
import com.gypsyhost.aventuria.custom.gui.UpgradeStationCommands;
import com.gypsyhost.aventuria.custom.gui.menu.UpgradeStationMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public final class PacketInsertUpgrade {
    public static PacketInsertUpgrade decode(FriendlyByteBuf buffer) {
        return new PacketInsertUpgrade(buffer.readBlockPos(), buffer.readItem());
    }

    private final BlockPos pos;
    private final ItemStack upgrade;

    public PacketInsertUpgrade(BlockPos blockPos, ItemStack stack) {
        this.pos = blockPos;
        this.upgrade = stack;
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeBlockPos(pos);
        buffer.writeItem(upgrade);
    }

    public void handler(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player == null) return;

            Level world = player.level;
            BlockPos pos = this.pos;

            BlockEntity te = world.getBlockEntity(pos);
            if (!(te instanceof UpgradeStationBlockEntity)) return;
            UpgradeStationMenu container = ((UpgradeStationBlockEntity) te).getContainer(player);

            ItemStack stack = player.containerMenu.getCarried();
            if (!stack.sameItem(upgrade)) {
                return;
            }

            if (UpgradeStationCommands.insertButton(container, this.upgrade)) {
                player.containerMenu.setCarried(ItemStack.EMPTY);
            }
        });

        ctx.get().setPacketHandled(true);
    }
}
