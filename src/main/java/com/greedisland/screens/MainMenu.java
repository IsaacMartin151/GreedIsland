package com.greedisland.screens;

import com.google.common.collect.Lists;
import com.greedisland.GreedIsland;
import com.greedisland.registry.MenuMusic;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.OptionsScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.gui.ModListScreen;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class MainMenu extends Screen {
    private MenuMusic departure = new MenuMusic();
    private final ResourceLocation TITLE = new ResourceLocation(GreedIsland.MODID, "textures/gui/title/title.png");
    private ResourceLocation background = new ScreenBackgrounds().getRandom();
    private final List<Button> buttons = Lists.newArrayList();

    public MainMenu() {
        super(Component.literal("Hunter X Hunter Main Menu"));
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    @Override
    protected void init() {
        int verticalSpacing = 20;
        int width = 75;
        this.buttons.add(this.addRenderableWidget(Button.builder(Component.literal("Singleplayer"), (p_213089_1_) -> {
            this.minecraft.setScreen(new SelectWorldScreen(this));
        }).pos(0, 0).size(width, verticalSpacing).build()));
        this.buttons.add(this.addRenderableWidget(Button.builder(Component.literal("Multiplayer"), (p_213089_1_) -> {
            this.minecraft.setScreen(new JoinMultiplayerScreen(this));
        }).pos(0, verticalSpacing).size(width, verticalSpacing).build()));
        this.buttons.add(this.addRenderableWidget(Button.builder(Component.literal("Options"), (p_213089_1_) -> {
            this.minecraft.setScreen(new OptionsScreen(this, this.minecraft.options));
        }).pos(0, verticalSpacing * 2).size(width, verticalSpacing).build()));
        this.buttons.add(this.addRenderableWidget(Button.builder(Component.literal("Mods"), (p_213089_1_) -> {
            this.minecraft.setScreen(new ModListScreen(this));
        }).pos(0, verticalSpacing * 3).size(width, verticalSpacing).build()));
        Minecraft.getInstance().getSoundManager().stop();
        setButtonsActive(true);
    }

    private void setButtonsActive(boolean on) {
        for(Button button : this.buttons) {
            button.active = on;
        }
    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        if (!Minecraft.getInstance().getSoundManager().isActive(departure)) {
            Minecraft.getInstance().getSoundManager().play(departure);
        }
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, background);
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        blit(matrixStack, 0, 0, this.width, this.height, 0.0F, 0.0F, 16, 128, 16, 128);

        RenderSystem.setShaderTexture(0, TITLE);
        // X pos on screen, y pos on screen, wi, x repetitions, y repititions
        int width = this.width/4;
        int height = width * 108/384;
        blit(matrixStack, this.width - width, this.height - height, Util.getMillis() / 20, 0, width, height, width, height);
        for (Button button : this.buttons) {
            button.render(matrixStack, mouseX, mouseY, partialTicks);
        }
    }
}
