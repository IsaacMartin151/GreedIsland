package com.greedisland.registry;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyMappings {
    private static String name = "key.category.greedisland.greedisland";

    private static String key_book = "key.greedisland.book";

    public static final KeyMapping BOOK = new KeyMapping(key_book, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_O, name);
}
