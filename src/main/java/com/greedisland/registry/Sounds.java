package com.greedisland.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.greedisland.GreedIsland.MODID;

public class Sounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MODID);

    public static final RegistryObject<SoundEvent> OSU = SOUNDS.register("osu", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MODID, "osu")));
    public static final RegistryObject<SoundEvent> COOKIECHAN = SOUNDS.register("cookiechan", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MODID, "cookiechan")));
    public static final RegistryObject<SoundEvent> EMBARK_ADVENTURE = SOUNDS.register("embarkadventure", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MODID, "embarkadventure")));
    public static final RegistryObject<SoundEvent> WORLD_OF_ADVENTURES = SOUNDS.register("worldofadventures", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MODID, "worldofadventures")));
    public static final RegistryObject<SoundEvent> DEPARTURE = SOUNDS.register("departure", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MODID, "departure")));
    public static final RegistryObject<SoundEvent> HISOKA = SOUNDS.register("hisoka", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MODID, "hisoka")));
    public static final RegistryObject<SoundEvent> STONESLIDE = SOUNDS.register("stoneslide", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MODID, "stoneslide")));
    public static final RegistryObject<SoundEvent> OPEN_BOOK = SOUNDS.register("openbook", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MODID, "openbook")));
    public static final RegistryObject<SoundEvent> LOUNGE_MUSIC = SOUNDS.register("loungemusic", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MODID, "loungemusic")));
    public static final RegistryObject<SoundEvent> WIND = SOUNDS.register("wind", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MODID, "wind")));
}
