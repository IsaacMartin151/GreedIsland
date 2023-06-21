package com.greedisland.container;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Card {
    public enum Nature {
        APPLE(Items.APPLE, 1, Rarity.COMMON),
        STICK(Items.STICK, 2, Rarity.COMMON),
        DANDELION(Items.DANDELION, 3, Rarity.COMMON),
        SNOWBALL(Items.SNOWBALL, 4, Rarity.COMMON),
        PRISMARINE(Items.PRISMARINE_SHARD, 5, Rarity.UNCOMMON),
        MELON(Items.MELON_SLICE, 6, Rarity.UNCOMMON),
        CARROT(Items.CARROT, 7, Rarity.UNCOMMON),
        EGG(Items.EGG, 8, Rarity.COMMON),
        VINE(Items.VINE, 9, Rarity.COMMON),
        SCUTE(Items.SCUTE, 46, Rarity.RARE);

        public Item item;
        public int slot;
        public Rarity rarity;

        Nature(Item item, int slot, Rarity rarity) {
            this.item = item;
            this.slot = slot;
            this.rarity = rarity;
        }

        public Rarity getRarity() {
            return this.rarity;
        }

        public int getSlot() {
            return this.slot;
        }

        public Item getItem() {
            return this.item;
        }
    }

    public enum Mining {
        AMETHYST(Items.AMETHYST_SHARD, 10, Rarity.COMMON),
        COAL(Items.COAL, 11, Rarity.COMMON),
        COPPER(Items.COPPER_INGOT, 12, Rarity.COMMON),
        IRON(Items.IRON_INGOT, 13, Rarity.UNCOMMON),
        LAPIS(Items.LAPIS_LAZULI, 14, Rarity.RARE),
        GOLD(Items.GOLD_INGOT, 15, Rarity.RARE),
        REDSTONE(Items.REDSTONE, 16, Rarity.RARE),
        DIAMOND(Items.DIAMOND, 17, Rarity.EPIC),
        ECHO_SHARD(Items.ECHO_SHARD, 18, Rarity.EPIC),
        NETHERITE(Items.NETHERITE_SCRAP, 47, Rarity.EPIC);

        public Item item;
        public int slot;
        public Rarity rarity;

        Mining(Item item, int slot, Rarity rarity) {
            this.item = item;
            this.slot = slot;
            this.rarity = rarity;
        }

        public Rarity getRarity() {
            return this.rarity;
        }

        public int getSlot() {
            return this.slot;
        }

        public Item getItem() {
            return this.item;
        }
    }

    public enum Hunting {
        LEATHER(Items.LEATHER, 19, Rarity.COMMON),
        BONE(Items.BONE, 20, Rarity.COMMON),
        FISH(Items.TROPICAL_FISH, 21, Rarity.COMMON),
        INK(Items.INK_SAC, 22, Rarity.COMMON),
        GUNPOWDER(Items.GUNPOWDER, 23, Rarity.UNCOMMON),
        SPIDER_EYE(Items.SPIDER_EYE, 24, Rarity.UNCOMMON),
        GOAT_HORN(Items.GOAT_HORN, 25, Rarity.UNCOMMON),
        BLAZE_POWDER(Items.BLAZE_POWDER, 26, Rarity.RARE),
        ENDER_PEARL(Items.ENDER_PEARL, 27, Rarity.RARE),
        GHAST_TEAR(Items.GHAST_TEAR, 48, Rarity.EPIC);

        public Item item;
        public int slot;
        public Rarity rarity;

        Hunting(Item item, int slot, Rarity rarity) {
            this.item = item;
            this.slot = slot;
            this.rarity = rarity;
        }

        public Rarity getRarity() {
            return this.rarity;
        }

        public int getSlot() {
            return this.slot;
        }

        public Item getItem() {
            return this.item;
        }
    }

    public enum Food {
        BREAD(Items.BREAD, 28, Rarity.COMMON),
        COOKIE(Items.COOKIE, 29, Rarity.COMMON),
        PUMPKING_PIE(Items.PUMPKIN_PIE, 30, Rarity.COMMON),
        RABBIT_STEW(Items.RABBIT_STEW, 31, Rarity.UNCOMMON),
        BEEDROOT(Items.BEETROOT, 32, Rarity.UNCOMMON),
        KELP(Items.DRIED_KELP, 33, Rarity.UNCOMMON),
        GOLDEN_APPLE(Items.GOLDEN_APPLE, 34, Rarity.RARE),
        CAKE(Items.CAKE, 35, Rarity.RARE),
        GLOW_BERRIES(Items.GLOW_BERRIES, 36, Rarity.RARE),
        CHORUS_FRUIT(Items.CHORUS_FRUIT, 49, Rarity.EPIC);

        public Item item;
        public int slot;
        public Rarity rarity;

        Food(Item item, int slot, Rarity rarity) {
            this.item = item;
            this.slot = slot;
            this.rarity = rarity;
        }

        public Rarity getRarity() {
            return this.rarity;
        }

        public int getSlot() {
            return this.slot;
        }

        public Item getItem() {
            return this.item;
        }
    }

    public enum BlocksCard {
        DIRT(Items.DIRT, 37, Rarity.COMMON),
        WOOD(Items.OAK_WOOD, 38, Rarity.COMMON),
        STONE(Items.STONE, 39, Rarity.COMMON),
        GRAVEL(Items.GRAVEL, 40, Rarity.COMMON),
        GLASS(Items.GLASS, 41, Rarity.COMMON),
        TERRACOTTA(Items.CYAN_TERRACOTTA, 42, Rarity.UNCOMMON),
        HONEY_BLOCK(Items.HONEYCOMB_BLOCK, 43, Rarity.UNCOMMON),
        MAGMA_BLOCK(Items.MAGMA_BLOCK, 44, Rarity.UNCOMMON),
        BLACKSTONE(Items.BLACKSTONE, 45, Rarity.RARE),
        OBSIDIAN(Items.OBSIDIAN, 50, Rarity.EPIC);

        public Item item;
        public int slot;
        public Rarity rarity;

        BlocksCard(Item item, int slot, Rarity rarity) {
            this.item = item;
            this.slot = slot;
            this.rarity = rarity;
        }

        public Rarity getRarity() {
            return this.rarity;
        }

        public int getSlot() {
            return this.slot;
        }

        public Item getItem() {
            return this.item;
        }
    }

    public static List<Item> getNatureItems() {
        return Arrays.stream(Nature.values()).map(Nature::getItem).collect(Collectors.toList());
    }

    public static List<Item> getMiningItems() {
        return Arrays.stream(Mining.values()).map(Mining::getItem).collect(Collectors.toList());
    }

    public static List<Item> getFoodItems() {
        return Arrays.stream(Food.values()).map(Food::getItem).collect(Collectors.toList());
    }

    public static List<Item> getHuntingItems() {
        return Arrays.stream(Hunting.values()).map(Hunting::getItem).collect(Collectors.toList());
    }

    public static List<Item> getBlocksCardItems() {
        return Arrays.stream(BlocksCard.values()).map(BlocksCard::getItem).collect(Collectors.toList());
    }

    public static List<Item> getAllCardItems() {
        List<Item> items = getNatureItems();
        items.addAll(getMiningItems());
        items.addAll(getFoodItems());
        items.addAll(getHuntingItems());
        items.addAll(getBlocksCardItems());

        return items;
    }
}
