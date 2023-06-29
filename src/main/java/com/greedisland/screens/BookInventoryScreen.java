package com.greedisland.screens;

import com.greedisland.GreedIsland;
import com.greedisland.container.BookMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class BookInventoryScreen extends AbstractContainerScreen<BookMenu> {
    private final ResourceLocation GUI = new ResourceLocation(GreedIsland.MODID, "textures/gui/book/book_inventory.png");

    public BookInventoryScreen(BookMenu bookMenu, Inventory inv, Component title) {
        super(bookMenu, inv, title);
        this.passEvents = false;
        this.imageHeight = 114 + 6 * 18;
        this.inventoryLabelY = this.imageHeight - 94;
    }

    public void render(PoseStack p_98418_, int p_98419_, int p_98420_, float p_98421_) {
        this.renderBackground(p_98418_);
        super.render(p_98418_, p_98419_, p_98420_, p_98421_);
        this.renderTooltip(p_98418_, p_98419_, p_98420_);
    }

    protected void renderBg(PoseStack p_98413_, float p_98414_, int p_98415_, int p_98416_) {
        RenderSystem.setShaderTexture(0, GUI);
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        blit(p_98413_, i, j, 0, 0, this.imageWidth, 6 * 18 + 17);
        blit(p_98413_, i, j + 6 * 18 + 17, 0, 126, this.imageWidth, 96);
    }
}