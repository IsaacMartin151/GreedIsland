package com.greedisland.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.List;

public class DebugScreen extends Screen {
    private List<EditBox> values;
    int boxHeight = 30;
    int boxWidth = 90;


    public DebugScreen() {
        super(Component.literal("Debug Screen"));
        values.add(this.addRenderableWidget(new EditBox(font, boxHeight, boxWidth, 0, 0, Component.literal("Second zeros"))));
        values.add(this.addRenderableWidget(new EditBox(font, 0, 0, boxHeight, boxWidth, Component.literal("First zeros"))));

    }

    @Override
    public void render(PoseStack ps, int x, int y, float z) {
        this.renderBackground(ps);
        super.render(ps, x, y, z);
        for (EditBox value : values) {
            value.render(ps, x, y, z);
        }
    }


    @Override
    public void onClose() {

    }
}