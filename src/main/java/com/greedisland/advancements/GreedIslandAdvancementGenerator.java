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
        Advancement base = Advancement.Builder.advancement().display(
                        Items.STONE_PICKAXE,
                        Component.literal("Yo"),
                        Component.literal("Hi"),
                        new ResourceLocation(GreedIsland.MODID, "title.png"), FrameType.TASK, true, true, false)
                .addCriterion("block_card", BlockCardTrigger.Instance.trigger())
//                .requirements()
                .save(saver, "greedisland:root");
    }
}
