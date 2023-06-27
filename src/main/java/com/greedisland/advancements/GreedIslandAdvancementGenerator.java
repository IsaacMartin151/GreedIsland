package com.greedisland.advancements;

import com.greedisland.GreedIsland;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import java.util.function.Consumer;

public class GreedIslandAdvancementGenerator implements ForgeAdvancementProvider.AdvancementGenerator {
    @Override
    public void generate(HolderLookup.Provider registries, Consumer<Advancement> saver, ExistingFileHelper existingFileHelper) {
        Advancement firstCard = Advancement.Builder.advancement().display(
                        Items.STONE_PICKAXE,
                        Component.literal("Departure V1"),
                        Component.literal("Collect your first card"),
                        new ResourceLocation(GreedIsland.MODID, "textures/gui/title/title.png"), FrameType.TASK, true, true, false)
                .addCriterion("first_card_criteria", FirstTrigger.Instance.trigger())
                .save(saver, "greedisland:root");

        Advancement natural = Advancement.Builder.advancement().display(
                        Items.OAK_SAPLING,
                        Component.literal("Natural"),
                        Component.literal("Collect all Nature Cards"),
                        null, FrameType.TASK, true, true, false)
                .addCriterion("nature_criteria", NatureTrigger.Instance.trigger())
                .parent(firstCard)
                .save(saver, "greedisland:root");

        Advancement hunter = Advancement.Builder.advancement().display(
                        Items.BOW,
                        Component.literal("Hunter X Hunter"),
                        Component.literal("Collect all Hunter Cards"),
                        null, FrameType.TASK, true, true, false)
                .addCriterion("hunter_criteria", NatureTrigger.Instance.trigger())
                .parent(firstCard)
                .save(saver, "greedisland:root");

        Advancement foodie = Advancement.Builder.advancement().display(
                        Items.COOKED_PORKCHOP,
                        Component.literal("Chungus in Training"),
                        Component.literal("Collect all Food Cards"),
                        null, FrameType.TASK, true, true, false)
                .addCriterion("food_criteria", FoodTrigger.Instance.trigger())
                .parent(firstCard)
                .save(saver, "greedisland:root");

        Advancement mining = Advancement.Builder.advancement().display(
                        Items.DIAMOND_PICKAXE,
                        Component.literal("Mined your Business"),
                        Component.literal("Collect all Mining Cards"),
                        null, FrameType.TASK, true, true, false)
                .addCriterion("mining_criteria", MiningTrigger.Instance.trigger())
                .parent(firstCard)
                .save(saver, "greedisland:root");

        Advancement blocks = Advancement.Builder.advancement().display(
                        Items.STONE,
                        Component.literal("Blockhead"),
                        Component.literal("Collect all Block Cards"),
                        null, FrameType.TASK, true, true, false)
                .addCriterion("block_card_criteria", BlockCardTrigger.Instance.trigger())
                .parent(firstCard)
                .save(saver, "greedisland:root");
    }
}
