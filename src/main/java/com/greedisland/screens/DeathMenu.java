package com.greedisland.screens;

import com.google.common.collect.Lists;
import com.greedisland.registry.DeathMusic;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.GenericDirtMessageScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class DeathMenu extends Screen {
    private int ticks = 0;
    private DeathMusic hisoka = new DeathMusic();
    private final List<Button> buttons = Lists.newArrayList();
    private ResourceLocation background = new ScreenBackgrounds().getRandom();

    public DeathMenu() {
        super(Component.literal("Hunter X Hunter Death Menu"));
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
        ticks = 0;
        this.buttons.add(this.addRenderableWidget(Button.builder(Component.literal("Respawn"), (p_275862_) -> {
            this.minecraft.player.respawn();
            p_275862_.active = false;
        }).bounds(this.width / 2 - 100, this.height / 4 + 72, 200, 20).build()));
        this.buttons.add(this.addRenderableWidget(Button.builder(Component.literal("Title Screen"), (p_262871_) -> {
            this.minecraft.getReportingContext().draftReportHandled(this.minecraft, this, this::exitToTitleScreen, true);
        }).bounds(this.width / 2 - 100, this.height / 4 + 96, 200, 20).build()));
        Minecraft.getInstance().getSoundManager().stop();
    }

    private void exitToTitleScreen() {
        if (this.minecraft.level != null) {
            this.minecraft.level.disconnect();
        }

        this.minecraft.clearLevel(new GenericDirtMessageScreen(Component.translatable("menu.savingLevel")));
        this.minecraft.setScreen(new MainMenu());
    }

    @Override
    public void tick() {
        super.tick();
        ++this.ticks;
        if (this.ticks == 20) {
            this.setButtonsActive(true);
        }

    }

    private void setButtonsActive(boolean on) {
        for(Button button : this.buttons) {
            button.active = on;
        }
    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        if (!Minecraft.getInstance().getSoundManager().isActive(hisoka)) {
            Minecraft.getInstance().getSoundManager().play(hisoka);
        }
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        RenderSystem.clearColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, background);
        blit(matrixStack, 0, 0, this.width, this.height, 0.0F, 0.0F, 16, 128, 16, 128);

        for (Button button : this.buttons) {
            button.render(matrixStack, mouseX, mouseY, partialTicks);
        }
    }
}
