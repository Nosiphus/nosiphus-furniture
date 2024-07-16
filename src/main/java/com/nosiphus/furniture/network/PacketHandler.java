package com.nosiphus.furniture.network;

import com.mrcrayfish.furniture.network.message.IMessage;
import com.nosiphus.furniture.Reference;
import com.nosiphus.furniture.network.message.C2SMessageEmptyBin;
import com.nosiphus.furniture.network.message.S2CMessageFluidSync;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

import javax.annotation.Nullable;
import java.util.Optional;

public class PacketHandler {

    public static final String PROTOCOL_VERSION = "1";

    private static SimpleChannel instance;
    private static int nextId = 0;

    public static void init() {
        instance = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(Reference.MOD_ID, "network"))
                .networkProtocolVersion(() -> PROTOCOL_VERSION)
                .clientAcceptedVersions(PROTOCOL_VERSION::equals)
                .serverAcceptedVersions(PROTOCOL_VERSION::equals)
                .simpleChannel();
        register(C2SMessageEmptyBin.class, new C2SMessageEmptyBin(), NetworkDirection.PLAY_TO_SERVER);
        register(S2CMessageFluidSync.class, new S2CMessageFluidSync(), NetworkDirection.PLAY_TO_CLIENT);
    }

    private static <T> void register(Class<T> clazz, IMessage<T> message, @Nullable NetworkDirection direction) {
        instance.registerMessage(nextId++, clazz, message::encode, message::decode, message::handle, Optional.ofNullable(direction));
    }

    public static SimpleChannel getPlayChannel() {
        return instance;
    }

    public static <MSG> void sendToServer(MSG message) {
        instance.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        instance.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public static <MSG> void sendToClients(MSG message) {
        instance.send(PacketDistributor.ALL.noArg(), message);
    }

}