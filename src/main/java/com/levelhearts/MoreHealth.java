package com.levelhearts;

import com.greedisland.packets.PacketManager;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundUpdateAttributesPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

import java.util.Collections;

import static com.levelhearts.MoreHealthProvider.MORE_HEALTH_CAPABILITY;


public class MoreHealth implements IMoreHealth {
    private byte heartContainers;

    public MoreHealth() {
        this.heartContainers = 6;
    }

    @Override
    public byte getHeartContainers() {
        return heartContainers;
    }

    @Override
    public void setHeartContainers(byte heartContainers) {
        this.heartContainers = heartContainers;
    }

    @Override
    public void addHeartContainer() {
        this.heartContainers += (byte) 2;
    }

    @Override
    public void copy(IMoreHealth other) {
        this.heartContainers = other.getHeartContainers();
    }

    @Override
    public void synchronise(Player player) {
        if (!player.getLevel().isClientSide) {
            AttributeInstance attribute = player.getAttribute(Attributes.MAX_HEALTH);
            ClientboundUpdateAttributesPacket packet = new ClientboundUpdateAttributesPacket(player.getId(), Collections.singleton(attribute));
            ((ServerLevel) player.getLevel()).getChunkSource().broadcastAndSend(player, packet);
        }
    }

    public static IMoreHealth getFromPlayer(Player player) {
        return player.getCapability(MORE_HEALTH_CAPABILITY, null).orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!"));
    }

    public static void updateClient(ServerPlayer player, IMoreHealth cap) {
        PacketManager.sendTo(player, new SyncHealthPacket(player.getId(), cap.serializeNBT()));
    }

    @Override
    public String toString() {
        return String.format("MoreHealth {containers=%s}", heartContainers);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putByte("heartContainers", heartContainers);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.heartContainers = nbt.getByte("heartContainers");

    }
}