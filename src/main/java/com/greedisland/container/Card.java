package com.greedisland.container;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Card {
    public enum Nature {
        APPLE(Items.APPLE),
        STICK(Items.STICK),
        DANDELION(Items.DANDELION),
        SNOWBALL(Items.SNOWBALL),
        PRISMARINE(Items.PRISMARINE_SHARD),
        MELON(Items.MELON_SLICE),
        CARROT(Items.CARROT),
        EGG(Items.EGG),
        VINE(Items.VINE),
        SCUTE(Items.SCUTE);

        public Item item;

        Nature(Item item) {
            this.item = item;
        }

        public Item getItem() {
            return this.item;
        }
    }

    public enum Mining {
        AMETHYST(Items.AMETHYST_SHARD),
        COAL(Items.COAL),
        COPPER(Items.COPPER_INGOT),
        IRON(Items.IRON_INGOT),
        GOLD(Items.GOLD_INGOT),
        LAPIS(Items.LAPIS_LAZULI),
        REDSTONE(Items.REDSTONE),
        DIAMOND(Items.DIAMOND),
        ECHO_SHARD(Items.ECHO_SHARD),
        NETHERITE(Items.NETHERITE_SCRAP);

        public Item item;

        Mining(Item item) {
            this.item = item;
        }

        public Item getItem() {
            return this.item;
        }
    }

    public enum Hunting {
        PORKCHOP(Items.PORKCHOP),
        LEATHER(Items.LEATHER),
        BONE(Items.BONE),
        FISH(Items.TROPICAL_FISH),
        INK(Items.INK_SAC),
        GOAT_HORN(Items.GOAT_HORN),
        GUNPOWDER(Items.GUNPOWDER),
        SPIDER_EYE(Items.SPIDER_EYE),
        BLAZE_POWDER(Items.BLAZE_POWDER),
        ENDER_PEARL(Items.ENDER_PEARL);

        public Item item;

        Hunting(Item item) {
            this.item = item;
        }

        public Item getItem() {
            return this.item;
        }
    }

    public enum Food {
        BREAD(Items.BREAD),
        COOKIE(Items.COOKIE),
        PUMPKING_PIE(Items.PUMPKIN_PIE),
        RABBIT_STEW(Items.RABBIT_STEW),
        BEEDROOT(Items.BEETROOT),
        KELP(Items.DRIED_KELP),
        GOLDEN_APPLE(Items.GOLDEN_APPLE),
        CAKE(Items.CAKE),
        GLOW_BERRIES(Items.GLOW_BERRIES),
        CHORUS_FRUIT(Items.CHORUS_FRUIT);

        public Item item;

        Food(Item item) {
            this.item = item;
        }

        public Item getItem() {
            return this.item;
        }
    }

    public enum BlocksCard {
        DIRT(Items.DIRT),
        WOOD(Items.OAK_WOOD),
        STONE(Items.STONE),
        GRAVEL(Items.GRAVEL),
        GLASS(Items.GLASS),
        TERRACOTTA(Items.CYAN_TERRACOTTA),
        HONEY_BLOCK(Items.HONEYCOMB_BLOCK),
        MAGMA_BLOCK(Items.MAGMA_BLOCK),
        BLACKSTONE(Items.BLACKSTONE),
        OBSIDIAN(Items.OBSIDIAN);

        public Item item;

        BlocksCard(Item item) {
            this.item = item;
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
