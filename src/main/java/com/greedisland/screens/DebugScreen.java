package com.greedisland.screens;

import com.levelhearts.MoreHealth;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

import static com.greedisland.container.GreedIslandProvider.BOOK_CAPABILITY;

@OnlyIn(Dist.CLIENT)
public class DebugScreen extends Screen {
    int boxHeight = 30;
    int boxWidth = 90;
    private List<EditBox> values = new ArrayList<>();
    private EditBox hearts;


    public DebugScreen() {
        super(Component.literal("Debug Screen"));
    }

    @Override
    public void init() {
        this.font = Minecraft.getInstance().font;
        hearts = new EditBox(font, 20, 50, boxWidth, boxHeight, Component.literal("Second zeros"));
        EditBox slotsFilled = new EditBox(font, 20, 2 * boxHeight + 50, boxWidth, boxHeight, Component.literal("First zeros"));
        hearts.insertText(String.valueOf(MoreHealth.getFromPlayer(Minecraft.getInstance().player).getHeartContainers()));
        slotsFilled.insertText(String.valueOf(Minecraft.getInstance().player.getCapability(BOOK_CAPABILITY, null).orElseThrow(() -> new IllegalArgumentException("Book can not be null")).getSlotsFilled()));
        values.add(this.addRenderableWidget(hearts));
        values.add(this.addRenderableWidget(slotsFilled));
    }



    @Override
    public void render(PoseStack ps, int x, int y, float z) {
        this.renderBackground(ps);
        for (EditBox value : values) {
            value.render(ps, x, y, z);
        }
    }
}