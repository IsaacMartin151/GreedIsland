package com.greedisland.advancements;

import net.minecraft.advancements.CriteriaTriggers;

public class GreedIslandAdvancements {
    public static final NatureTrigger NATURE_TRIGGER = CriteriaTriggers.register(new NatureTrigger());
    public static final FoodTrigger FOOD_TRIGGER = CriteriaTriggers.register(new FoodTrigger());
    public static final HunterTrigger HUNTER_TRIGGER = CriteriaTriggers.register(new HunterTrigger());
    public static final BlockCardTrigger BLOCK_CARD_TRIGGER = CriteriaTriggers.register(new BlockCardTrigger());
    public static final MiningTrigger MINING_TRIGGER = CriteriaTriggers.register(new MiningTrigger());

    public static void init() {}
}
