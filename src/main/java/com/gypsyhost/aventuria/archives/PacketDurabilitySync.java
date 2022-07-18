/*
package com.gypsyhost.aventuria.network.packets;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.Tuple;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class PacketDurabilitySync {
    private final List<Tuple<BlockPos, Integer>> updateList;
    //private final int priorDurability;

    public PacketDurabilitySync(List<Tuple<BlockPos, Integer>> updateList) {
        this.updateList = updateList;
    }

    public static void encode(PacketDurabilitySync msg, FriendlyByteBuf buffer) {
        List<Tuple<BlockPos, Integer>> thisList = msg.updateList;
        CompoundTag tag = new CompoundTag();
        ListTag nbtList = new ListTag();
        for (int i = 0; i < thisList.size(); i++) {
            CompoundTag nbt = new CompoundTag();
            nbt.put("pos", NbtUtils.writeBlockPos(thisList.get(i).getA()));
            nbt.putInt("dur", thisList.get(i).getB());
            nbtList.add(i, nbt);
        }
        tag.put("list", nbtList);
        buffer.writeNbt(tag);
    }

    public static PacketDurabilitySync decode(FriendlyByteBuf buffer) {
        CompoundTag tag = buffer.readNbt();
        ListTag nbtList = tag.getList("list", Tag.TAG_COMPOUND);
        List<Tuple<BlockPos, Integer>> thisList = new ArrayList<>();
        for (int i = 0; i < nbtList.size(); i++) {
            CompoundTag nbt = nbtList.getCompound(i);
            thisList.add(new Tuple<>(NbtUtils.readBlockPos(nbt.getCompound("pos")), nbt.getInt("dur")));
        }
        return new PacketDurabilitySync(thisList);
    }

    public static class Handler {
        public static void handle(PacketDurabilitySync msg, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> clientPacketHandler(msg)));
            ctx.get().setPacketHandled(true);
        }
    }

    public static void clientPacketHandler(PacketDurabilitySync msg) {
        List<Tuple<BlockPos, Integer>> thisList = msg.updateList;

        for (int i = 0; i < thisList.size(); i++) {
            BlockPos pos = thisList.get(i).getA();
            int durability = thisList.get(i).getB();
            BlockEntity clientTE = Minecraft.getInstance().level.getBlockEntity(pos);
            if (!(clientTE instanceof RenderBlockEntity)) return;
            ((RenderBlockEntity) clientTE).setClientDurability(durability);
        }
    }
}
*/
