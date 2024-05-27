package com.nosiphus.furniture.network;

import com.mrcrayfish.furniture.network.message.IMessage;
import com.nosiphus.furniture.NosiphusFurnitureMod;
import com.nosiphus.furniture.network.message.C2SMessageEmptyBin;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import javax.annotation.Nullable;
import java.util.Optional;

public class PacketHandler {

    public static final String PROTOCOL_VERSION = "1";

    private static SimpleChannel instance;
    private static int nextId = 0;

    public static void init() {
        instance = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(NosiphusFurnitureMod.MOD_ID, "network"))
                .networkProtocolVersion(() -> PROTOCOL_VERSION)
                .clientAcceptedVersions(PROTOCOL_VERSION::equals)
                .serverAcceptedVersions(PROTOCOL_VERSION::equals)
                .simpleChannel();
        register(C2SMessageEmptyBin.class, new C2SMessageEmptyBin(), NetworkDirection.PLAY_TO_SERVER);
    }

    private static <T> void register(Class<T> clazz, IMessage<T> message, @Nullable NetworkDirection direction) {
        instance.registerMessage(nextId++, clazz, message::encode, message::decode, message::handle, Optional.ofNullable(direction));
    }

    public static SimpleChannel getPlayChannel() {
        return instance;
    }

}
