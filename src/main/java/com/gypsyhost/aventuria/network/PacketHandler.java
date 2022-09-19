package com.gypsyhost.aventuria.network;

import com.gypsyhost.aventuria.Aventuria;
import com.gypsyhost.aventuria.network.packets.*;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

import javax.annotation.Nullable;

public class PacketHandler {

    private static PacketHandler instance = null;

    private static final String PROTOCOL_VERSION = Integer.toString(2);
    private static final short index = 0;

    public static final SimpleChannel HANDLER = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(Aventuria.MOD_ID, "main_network_channel"))
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .simpleChannel();

    /** Gets the instance of the network */
    public static PacketHandler getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Attempt to call network getInstance before network is setup");
        }
        return instance;
    }



    public static void register() {
        int id = 0;

        // Server side
        HANDLER.registerMessage(id++, PacketExtractUpgrade.class,     PacketExtractUpgrade::encode,       PacketExtractUpgrade::decode,       PacketExtractUpgrade.Handler::handle);
        HANDLER.registerMessage(id++, PacketUpdateUpgrade.class,      PacketUpdateUpgrade::encode,        PacketUpdateUpgrade::decode,        PacketUpdateUpgrade.Handler::handle);
        HANDLER.registerMessage(id++, PacketChangeMiningSize.class,   PacketChangeMiningSize::encode,     PacketChangeMiningSize::decode,     PacketChangeMiningSize.Handler::handle);
        HANDLER.registerMessage(id++, PacketGhostSlot.class,          PacketGhostSlot::encode,            PacketGhostSlot::decode,            PacketGhostSlot.Handler::handle);

        //region Server side packets for all Menu's

        //endregion

        //Client Side
        HANDLER.registerMessage(id++, PacketInsertUpgrade.class,      PacketInsertUpgrade::encode,        PacketInsertUpgrade::decode,        PacketInsertUpgrade::handler);
    }

    public static void sendTo(Object msg, ServerPlayer player) {
        if (!(player instanceof FakePlayer))
            HANDLER.sendTo(msg, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
    }

    public static void sendToAll(Object msg, Level world) {
        for (Player player : world.players()) {
            if (!(player instanceof FakePlayer))
                HANDLER.sendTo(msg, ((ServerPlayer) player).connection.connection, NetworkDirection.PLAY_TO_CLIENT);
        }
    }

    public static void sendToServer(Object msg) {
        HANDLER.sendToServer(msg);
    }

    /**
     * Sends a vanilla packet to the given player
     * @param player  Player
     * @param packet  Packet
     */
    public void sendVanillaPacket(Entity player, Packet<?> packet) {
        if (player instanceof ServerPlayer serverPlayer) {
            serverPlayer.connection.send(packet);
        }
    }

    /**
     * Same as , but checks that the world is a serverworld
     * @param msg       Packet to send
     * @param world     World instance
     * @param position  Target position
     */
    public void sendToClientsAround(Object msg, @Nullable LevelAccessor world, BlockPos position) {
        if (world instanceof ServerLevel server) {
            sendToClientsAround(msg, server, position);
        }
    }

    /**
     * Sends a packet to all entities tracking the given entity
     * @param msg     Packet
     * @param entity  Entity to check
     */

    public void sendToTrackingAndSelf(Object msg, Entity entity) {
        HANDLER.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity), msg);
    }

    /**
     * Sends a packet to all entities tracking the given entity
     * @param msg     Packet
     * @param entity  Entity to check
     */

    public void sendToTracking(Object msg, Entity entity) {
        HANDLER.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), msg);
    }

    /**
     * Sends a packet to the whole player list
     * @param targetedPlayer  Main player to target, if null uses whole list
     * @param playerList      Player lists to use if main player is null
     * @param msg             Message to send
     */
    public void sendToPlayerList(@Nullable ServerPlayer targetedPlayer, PlayerList playerList, Object msg) {
        if (targetedPlayer != null) {
            sendTo(msg, targetedPlayer);
        } else {
            for (ServerPlayer player : playerList.getPlayers()) {
                sendTo(msg, player);
            }
        }
    }

    /**
     * Sends a packet to the given packet distributor
     * @param target   Packet target
     * @param message  Packet to send
     */
    public void send(PacketDistributor.PacketTarget target, Object message) {
        HANDLER.send(target, message);
    }

}
