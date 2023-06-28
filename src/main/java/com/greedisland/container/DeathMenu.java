package com.greedisland.container;

import com.google.common.collect.Lists;
import com.greedisland.GreedIsland;
import com.greedisland.registry.DeathMusic;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.GenericDirtMessageScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;

import java.io.File;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class DeathMenu extends Screen {
    public FrameGrabber frameGrabber;
    public DynamicTexture dynamicTexture = new DynamicTexture(640, 358, true);
    private boolean needstorestart = false;
    private int ticks = 0;
    private boolean start = false;
    private float framerate = 30f;
    private float lastpartialticks = -100;
    private DeathMusic hisoka = new DeathMusic();
    private TextureManager textureManager;
    private long firstRenderTime;
    private final List<Button> buttons = Lists.newArrayList();

    public DeathMenu() {
        super(Component.literal("Hunter X Hunter Death Menu"));
        this.firstRenderTime = 0L;
        this.textureManager = Minecraft.getInstance().textureManager;
        GreedIsland.LOGGER.info("Trying to get file at"+Minecraft.getInstance().getResourcePackDirectory().toAbsolutePath()+"/hisoka.mp4");
        File departure = new File(Minecraft.getInstance().getResourcePackDirectory().toAbsolutePath()+"/hisoka.mp4");
        frameGrabber = new FFmpegFrameGrabber(departure);
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
        Component component = Component.literal("Respawn");
        this.buttons.add(this.addRenderableWidget(Button.builder(component, (p_275862_) -> {
            this.minecraft.player.respawn();
            p_275862_.active = false;
        }).bounds(this.width / 2 - 100, this.height / 4 + 72, 200, 20).build()));
        this.buttons.add(this.addRenderableWidget(Button.builder(Component.literal("Title Screen"), (p_262871_) -> {
            this.minecraft.getReportingContext().draftReportHandled(this.minecraft, this, this::exitToTitleScreen, true);
        }).bounds(this.width / 2 - 100, this.height / 4 + 96, 200, 20).build()));
        frameGrabber.setAudioChannels(0);
        try {
            frameGrabber.start();
        }
        catch (FrameGrabber.Exception e) {
            GreedIsland.LOGGER.info("Unable to start frame grabber");
        }
    }

    private void exitToTitleScreen() {
        if (this.minecraft.level != null) {
            this.minecraft.level.disconnect();
        }

        this.minecraft.clearLevel(new GenericDirtMessageScreen(Component.translatable("menu.savingLevel")));
        this.minecraft.setScreen(new TitleScreen());
    }

    @Override
    public void tick() {
        super.tick();
        ++this.ticks;
        if (this.ticks == 20) {
            this.setButtonsActive(true);
        }

    }

    private void setButtonsActive(boolean p_273413_) {
        for(Button button : this.buttons) {
            button.active = p_273413_;
        }

    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        Frame currentFrame = null;
        if (this.firstRenderTime == 0L) {
            Minecraft.getInstance().getSoundManager().stop();
            this.firstRenderTime = Util.getEpochMillis();
        }
        if (lastpartialticks < 0) {
            lastpartialticks += partialTicks;
            return;
        }
        if (needstorestart) {
            Minecraft.getInstance().getSoundManager().play(hisoka);
            try {
                frameGrabber.restart();
            } catch (Exception e) {
                e.printStackTrace();
            }
            lastpartialticks = 0;
            needstorestart = false;
        }

        if (lastpartialticks > (20f / framerate)) {
            if (!Minecraft.getInstance().getSoundManager().isActive(hisoka)) {
                Minecraft.getInstance().getSoundManager().play(hisoka);
            }
            try {
                currentFrame = frameGrabber.grabFrame();
                if (frameGrabber.getFrameNumber() > frameGrabber.getLengthInFrames() - 3) {
                    needstorestart = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            lastpartialticks -= (20f/framerate);
        }

        if (currentFrame != null) {
            if (currentFrame.image != null) {
                if (currentFrame.imageHeight != 0) {
                    dynamicTexture.setPixels(dynamicTexture.getPixels());
                }
            }
        }
        ResourceLocation image = new ResourceLocation(dynamicTexture.toString());
        textureManager.bindForSetup(image);
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        RenderSystem.clearColor(1.0F, 1.0F, 1.0F, 1.0F);
        blit(matrixStack, 0, 0, this.width, this.height, 0.0F, 0.0F, 16, 128, 16, 128);

        for (Button button : this.buttons) {
            button.render(matrixStack, mouseX, mouseY, partialTicks);
        }
        lastpartialticks += partialTicks;
    }

    @Override
    public void onClose() {
//        try {
//            frameGrabber.stop();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        needstorestart = true;
//        Minecraft.getInstance().getSoundManager().stop();
    }
}
