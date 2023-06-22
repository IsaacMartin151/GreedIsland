package com.greedisland.container;


import com.greedisland.GreedIsland;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Optional;

public class BookItemStackHandler extends ItemStackHandler {
    private static final int BOOK_SIZE = 50;

    public BookItemStackHandler() {
        super(BOOK_SIZE);
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        GreedIsland.LOGGER.info("Stack is "+stack.getItem().toString()+" slot is "+slot);
        if (slot < 0 || slot >= BOOK_SIZE) {
            throw new IllegalArgumentException("Invalid slot number: " + slot);
        }
        if (stack.isEmpty() || stacks.contains(stack)) {
            GreedIsland.LOGGER.info("Stack is empty or already there");
            return false;
        }

        Optional<Card> card = Arrays.stream(Card.values()).filter(c -> c.getItem() == stack.getItem()).findFirst();
        GreedIsland.LOGGER.info("Got Card " + (card.isPresent() ? card.get().getName() : " sike"));

        if (card.isPresent() && card.get().getSlot() == slot + 1) {
            GreedIsland.LOGGER.info("Target item is a collection card and slot "+slot+" is right slot");
            return true;
        }
        GreedIsland.LOGGER.info("Not one of the valid items");
        return false;
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