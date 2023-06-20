package com.greedisland.container;



import com.greedisland.GreedIsland;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

import java.util.List;

public class BookItemStackHandler extends ItemStackHandler {
    public List<Item> items;

    private static final int BOOK_SIZE = 50;

    public BookItemStackHandler() {
        super(BOOK_SIZE);
        items = Card.getAllCardItems();
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        if (slot < 0 || slot >= BOOK_SIZE) {
            throw new IllegalArgumentException("Invalid slot number: " + slot);
        }
        if (stack.isEmpty() || stacks.contains(stack)) {
            return false;
        }

        if (items.contains(stack.getItem())) {
            GreedIsland.LOGGER.info("Target item is a collection card");
            return true;
        }
        GreedIsland.LOGGER.info("Not a valid itemstack, not in the hundred or already there");
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