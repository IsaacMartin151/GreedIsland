package com.greedisland.container;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;

public enum Card {
    APPLE(Items.APPLE, 1, Rarity.COMMON, "Nature"),
    STICK(Items.STICK, 2, Rarity.COMMON, "Nature"),
    DANDELION(Items.DANDELION, 3, Rarity.COMMON, "Nature"),
    SNOWBALL(Items.SNOWBALL, 4, Rarity.COMMON, "Nature"),
    PRISMARINE(Items.PRISMARINE_SHARD, 5, Rarity.UNCOMMON, "Nature"),
    MELON(Items.MELON_SLICE, 6, Rarity.UNCOMMON, "Nature"),
    CARROT(Items.CARROT, 7, Rarity.UNCOMMON, "Nature"),
    EGG(Items.EGG, 8, Rarity.COMMON, "Nature"),
    VINE(Items.VINE, 9, Rarity.COMMON, "Nature"),
    SCUTE(Items.SCUTE, 46, Rarity.RARE, "Nature"),

    AMETHYST(Items.AMETHYST_SHARD, 10, Rarity.COMMON, "Mining"),
    COAL(Items.COAL, 11, Rarity.COMMON, "Mining"),
    COPPER(Items.COPPER_INGOT, 12, Rarity.COMMON, "Mining"),
    IRON(Items.IRON_INGOT, 13, Rarity.UNCOMMON, "Mining"),
    LAPIS(Items.LAPIS_LAZULI, 14, Rarity.RARE, "Mining"),
    GOLD(Items.GOLD_INGOT, 15, Rarity.RARE, "Mining"),
    REDSTONE(Items.REDSTONE, 16, Rarity.RARE, "Mining"),
    DIAMOND(Items.DIAMOND, 17, Rarity.EPIC, "Mining"),
    ECHO_SHARD(Items.ECHO_SHARD, 18, Rarity.EPIC, "Mining"),
    NETHERITE(Items.NETHERITE_SCRAP, 47, Rarity.EPIC, "Mining"),

    LEATHER(Items.LEATHER, 19, Rarity.COMMON, "Hunting"),
    BONE(Items.BONE, 20, Rarity.COMMON, "Hunting"),
    FISH(Items.TROPICAL_FISH, 21, Rarity.COMMON, "Hunting"),
    INK(Items.INK_SAC, 22, Rarity.COMMON, "Hunting"),
    GUNPOWDER(Items.GUNPOWDER, 23, Rarity.UNCOMMON, "Hunting"),
    SPIDER_EYE(Items.SPIDER_EYE, 24, Rarity.UNCOMMON, "Hunting"),
    GOAT_HORN(Items.GOAT_HORN, 25, Rarity.UNCOMMON, "Hunting"),
    BLAZE_POWDER(Items.BLAZE_POWDER, 26, Rarity.RARE, "Hunting"),
    ENDER_PEARL(Items.ENDER_PEARL, 27, Rarity.RARE, "Hunting"),
    GHAST_TEAR(Items.GHAST_TEAR, 48, Rarity.EPIC, "Hunting"),

    BREAD(Items.BREAD, 28, Rarity.COMMON, "Food"),
    COOKIE(Items.COOKIE, 29, Rarity.COMMON, "Food"),
    PUMPKING_PIE(Items.PUMPKIN_PIE, 30, Rarity.COMMON, "Food"),
    RABBIT_STEW(Items.RABBIT_STEW, 31, Rarity.UNCOMMON, "Food"),
    BEEDROOT(Items.BEETROOT, 32, Rarity.UNCOMMON, "Food"),
    KELP(Items.DRIED_KELP, 33, Rarity.UNCOMMON, "Food"),
    GOLDEN_APPLE(Items.GOLDEN_APPLE, 34, Rarity.RARE, "Food"),
    CAKE(Items.CAKE, 35, Rarity.RARE, "Food"),
    GLOW_BERRIES(Items.GLOW_BERRIES, 36, Rarity.RARE, "Food"),
    CHORUS_FRUIT(Items.CHORUS_FRUIT, 49, Rarity.EPIC, "Food"),

    DIRT(Items.DIRT, 37, Rarity.COMMON, "BlocksCard"),
    WOOD(Items.OAK_LOG, 38, Rarity.COMMON, "BlocksCard"),
    STONE(Items.STONE, 39, Rarity.COMMON, "BlocksCard"),
    GRAVEL(Items.GRAVEL, 40, Rarity.COMMON, "BlocksCard"),
    GLASS(Items.GLASS, 41, Rarity.COMMON, "BlocksCard"),
    TERRACOTTA(Items.CYAN_TERRACOTTA, 42, Rarity.UNCOMMON, "BlocksCard"),
    HONEY_BLOCK(Items.HONEYCOMB_BLOCK, 43, Rarity.UNCOMMON, "BlocksCard"),
    MAGMA_BLOCK(Items.MAGMA_BLOCK, 44, Rarity.UNCOMMON, "BlocksCard"),
    BLACKSTONE(Items.BLACKSTONE, 45, Rarity.RARE, "BlocksCard"),
    OBSIDIAN(Items.OBSIDIAN, 50, Rarity.EPIC, "BlocksCard");

    public Item item;
    public int slot;
    public Rarity rarity;
    public String categoryName;

    Card(Item item, int slot, Rarity rarity, String categoryName) {
        this.item = item;
        this.slot = slot;
        this.rarity = rarity;
        this.categoryName = categoryName;
    }

    public int getSlot() {
        return this.slot;
    }

    public Item getItem() {
        return this.item;
    }

    public String getName() {
        return this.categoryName;
    }

    public Rarity getRarity() {
        return this.rarity;
    }
}
