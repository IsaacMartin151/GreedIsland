package com.levelhearts;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncHealthPacket {

    private final CompoundTag nbt;

    public SyncHealthPacket(int entityId, CompoundTag nbt) {
        // Add entity id
        nbt.putInt("entityid", entityId);
        this.nbt = nbt;
    }

    private SyncHealthPacket(CompoundTag nbt) {
        this.nbt = nbt;
    }

    public static void encode(SyncHealthPacket msg, FriendlyByteBuf buff) {
        buff.writeNbt(msg.nbt);
    }

    public static SyncHealthPacket decode(FriendlyByteBuf buff) {
        return new SyncHealthPacket(buff.readNbt());
    }

    public static void handle(SyncHealthPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            LocalPlayer player = (LocalPlayer) Minecraft.getInstance().level.getEntity(msg.nbt.getInt("entityid"));
            IMoreHealth cap = MoreHealth.getFromPlayer(player);
            cap.deserializeNBT(msg.nbt);
        });
        ctx.get().setPacketHandled(true);
    }

}

