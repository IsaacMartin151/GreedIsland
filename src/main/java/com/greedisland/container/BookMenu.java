package com.greedisland.container;

import com.greedisland.GreedIsland;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

import static com.greedisland.registry.MenuTypes.BOOK_MENU;

public class BookMenu extends AbstractContainerMenu {
    public final BookItemStackHandler bookItemStackHandler;

    private static final int BAG_SIZE = 50;


    public BookMenu(int containerId, Inventory playerInv, FriendlyByteBuf friendlyByteBuf) {
        this(containerId, playerInv, new BookItemStackHandler());
    }

    public BookMenu(int containerId, Inventory playerInv, BookItemStackHandler bookItemStackHandler) {
        super(BOOK_MENU.get(), containerId);
        this.bookItemStackHandler = bookItemStackHandler;

        int i = 36;

        for(int j = 0; j < 5; ++j) {
            for(int k = 0; k < 9; ++k) {
                this.addSlot(new SlotItemHandler(bookItemStackHandler, k + j * 9, 8 + k * 18, 18 + j * 18));
            }
        }
        for(int k = 0; k < 5; ++k) {
            this.addSlot(new SlotItemHandler(bookItemStackHandler, k + 45, 8 + k * 18, 108));
        }

        for(int l = 0; l < 3; ++l) {
            for(int j1 = 0; j1 < 9; ++j1) {
                this.addSlot(new Slot(playerInv, j1 + l * 9 + 9, 8 + j1 * 18, 103 + l * 18 + i));
            }
        }

        for(int i1 = 0; i1 < 9; ++i1) {
            this.addSlot(new Slot(playerInv, i1, 8 + i1 * 18, 161 + i));
        }
    }

    public ItemStack quickMoveStack(Player p_39253_, int p_39254_) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(p_39254_);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (p_39254_ < BAG_SIZE) {
                if (!this.moveItemStackTo(itemstack1, BAG_SIZE, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, BAG_SIZE, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }
}
