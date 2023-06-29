package com.greedisland.screens;

import com.greedisland.GreedIsland;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ScreenBackgrounds {
    private ResourceLocation BISKY = new ResourceLocation(GreedIsland.MODID, "textures/gui/background/bisky.jpg");
    private ResourceLocation COUNCIL = new ResourceLocation(GreedIsland.MODID, "textures/gui/background/council.jpg");
    private ResourceLocation EXAM = new ResourceLocation(GreedIsland.MODID, "textures/gui/background/exam.jpg");
    private ResourceLocation GING = new ResourceLocation(GreedIsland.MODID, "textures/gui/background/ging.jpg");
    private ResourceLocation GON = new ResourceLocation(GreedIsland.MODID, "textures/gui/background/gon.jpg");
    private ResourceLocation GOTOH = new ResourceLocation(GreedIsland.MODID, "textures/gui/background/gotoh.png");
    private ResourceLocation HISOKA = new ResourceLocation(GreedIsland.MODID, "textures/gui/background/hisoka.jpg");
    private ResourceLocation KILLUA = new ResourceLocation(GreedIsland.MODID, "textures/gui/background/killua.jpg");
    private ResourceLocation NETERO = new ResourceLocation(GreedIsland.MODID, "textures/gui/background/netero.jpg");
    private ResourceLocation OLDLADY = new ResourceLocation(GreedIsland.MODID, "textures/gui/background/oldlady.jpg");
    private ResourceLocation SATOTZ = new ResourceLocation(GreedIsland.MODID, "textures/gui/background/satotz.jpg");

    private Random rand = new Random();

    private List<ResourceLocation> screenBackgrounds = new ArrayList<>();

    public ScreenBackgrounds() {
        screenBackgrounds.add(BISKY);
        screenBackgrounds.add(COUNCIL);
        screenBackgrounds.add(EXAM);
        screenBackgrounds.add(GING);
        screenBackgrounds.add(GON);
        screenBackgrounds.add(GOTOH);
        screenBackgrounds.add(HISOKA);
        screenBackgrounds.add(KILLUA);
        screenBackgrounds.add(NETERO);
        screenBackgrounds.add(OLDLADY);
        screenBackgrounds.add(SATOTZ);
    }

    public ResourceLocation getRandom() {
        int i = Math.abs(rand.nextInt()) % screenBackgrounds.size();
        GreedIsland.LOGGER.info("Getting random int "+i);
        return screenBackgrounds.get(i);
    }
}
