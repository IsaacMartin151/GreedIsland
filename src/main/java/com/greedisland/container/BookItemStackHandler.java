package com.greedisland.container;


import com.greedisland.GreedIsland;
import com.greedisland.advancements.BlockCardTrigger;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.server.ServerAdvancementManager;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Optional;

import static com.greedisland.advancements.GreedIslandAdvancements.BLOCK_CARD_TRIGGER;

public class BookItemStackHandler extends ItemStackHandler {
    private static final int BOOK_SIZE = 50;

    public BookItemStackHandler() {
        super(BOOK_SIZE);
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        if (slot < 0 || slot >= BOOK_SIZE) {
            throw new IllegalArgumentException("Invalid slot number: " + slot);
        }
        if (stack.isEmpty() || stacks.contains(stack)) {
            return false;
        }

        Optional<Card> card = Arrays.stream(Card.values()).filter(c -> c.getItem() == stack.getItem()).findFirst();
        if (card.isPresent() && card.get().getSlot() == slot + 1) {
            return true;
        }

        return false;
    }

    public void triggerAdvancements(ServerPlayer serverPlayer) {
        try {
//            ServerAdvancementManager sam = serverPlayer.getServer().getAdvancements();
//            Advancement nature = sam.getAdvancement(BlockCardTrigger.ID);
//            AdvancementProgress progress = serverPlayer.getAdvancements().getOrStartProgress(nature);
//            for (String s : progress.getRemainingCriteria()) {
//                serverPlayer.getAdvancements().award(nature, s);
//            }
            BLOCK_CARD_TRIGGER.trigger(serverPlayer);
        }
        catch (Exception e){
            GreedIsland.LOGGER.info("Error triggering advancements - "+e);
        }
    }

    public boolean isComplete() {
        ItemStack[] bruh = new ItemStack[50];
        for (int i = 0; i < this.getSlots(); i++) {
            ItemStack item = stacks.get(i).getItem().getDefaultInstance();
            if (isItemValid(i, item)) {
                for (int j = 0; j < this.getSlots(); j++) {
                    if (j != i) {
                        if (item.sameItem(stacks.get(j).getItem().getDefaultInstance())) {
                            GreedIsland.LOGGER.info("Returning false, book is not complete");
                            return false;
                        }
                    }
                }
                bruh[i] = item;
            }
        }
        GreedIsland.LOGGER.info("Returning true, book is complete");
        return true;
    }


}