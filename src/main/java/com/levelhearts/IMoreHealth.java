package com.levelhearts;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.INBTSerializable;

public interface IMoreHealth extends INBTSerializable<CompoundTag> {
    byte getHeartContainers();
    void setHeartContainers(byte heartContainers);
    void addHeartContainer();
    void copy(IMoreHealth other);
    void synchronise(Player player);

}
