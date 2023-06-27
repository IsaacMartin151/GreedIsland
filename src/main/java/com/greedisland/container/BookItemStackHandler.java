package com.greedisland.container;


import com.greedisland.GreedIsland;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Optional;

import static com.greedisland.advancements.GreedIslandAdvancements.*;

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

    public void triggerAdvancements(ServerPlayer sp) {
        triggerFirst(sp);
        triggerNature(sp);
        triggerHunter(sp);
        triggerFood(sp);
        triggerMining(sp);
        triggerBlocks(sp);
    }

    public void triggerFirst(ServerPlayer sp) {
        for (int i = 0; i < this.getSlots(); i++) {
            if (!getStackInSlot(i).isEmpty()) {
                FIRST_TRIGGER.trigger(sp);
                break;
            }
        }
    }

    public void triggerNature(ServerPlayer sp) {
        int slotsFilled = 0;
        for (int i = 0; i < 9; i++) {
            if (!getStackInSlot(i).isEmpty()) {
                slotsFilled++;
            }
        }
        if (slotsFilled >= 9) {
            NATURE_TRIGGER.trigger(sp);
        }
    }

    public void triggerHunter(ServerPlayer sp) {
        int slotsFilled = 0;
        for (int i = 9; i < 18; i++) {
            if (!getStackInSlot(i).isEmpty()) {
                slotsFilled++;
            }
        }
        if (slotsFilled >= 9) {
            HUNTER_TRIGGER.trigger(sp);
        }
    }

    public void triggerFood(ServerPlayer sp) {
        int slotsFilled = 0;
        for (int i = 18; i < 27; i++) {
            if (!getStackInSlot(i).isEmpty()) {
                slotsFilled++;
            }
        }
        if (slotsFilled >= 9) {
            FOOD_TRIGGER.trigger(sp);
        }
    }

    public void triggerMining(ServerPlayer sp) {
        int slotsFilled = 0;
        for (int i = 27; i < 36; i++) {
            if (!getStackInSlot(i).isEmpty()) {
                slotsFilled++;
            }
        }
        if (slotsFilled >= 9) {
            MINING_TRIGGER.trigger(sp);
        }
    }

    public void triggerBlocks(ServerPlayer sp) {
        int slotsFilled = 0;
        for (int i = 36; i < 45; i++) {
            if (!getStackInSlot(i).isEmpty()) {
                slotsFilled++;
            }
        }
        if (slotsFilled >= 9) {
            BLOCK_CARD_TRIGGER.trigger(sp);
        }
    }

    public boolean isComplete() {
        int emptySlots = 0;
        for (int i = 0; i < this.getSlots(); i++) {
            if (getStackInSlot(i).isEmpty()) {
                emptySlots++;
            }
        }
        if (emptySlots == 0) {
            GreedIsland.LOGGER.info("Book is complete");
            return true;
        }
        return true;
    }


}