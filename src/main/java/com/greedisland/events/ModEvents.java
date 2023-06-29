package com.greedisland.events;

import com.greedisland.GreedIsland;
import com.greedisland.advancements.GreedIslandAdvancementProvider;
import com.greedisland.container.*;
import com.greedisland.packets.PacketManager;
import com.greedisland.packets.SyncBookPacket;
import com.greedisland.screens.DeathMenu;
import com.greedisland.screens.MainMenu;
import com.levelhearts.IMoreHealth;
import com.levelhearts.MoreHealth;
import com.levelhearts.MoreHealthProvider;
import com.mojang.logging.LogUtils;
import com.mojang.realmsclient.gui.screens.RealmsPlayerScreen;
import net.minecraft.client.gui.screens.DeathScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.ItemStackHandler;

import java.util.concurrent.CompletableFuture;

import static com.greedisland.GreedIsland.MODID;
import static com.greedisland.container.GreedIslandProvider.BOOK_CAPABILITY;
import static com.greedisland.registry.KeyMappings.BOOK;

@Mod.EventBusSubscriber
public class ModEvents
{
    @SubscribeEvent
    public void gatherData(GatherDataEvent event) {
        GreedIsland.LOGGER.info("Registering advancements");
        DataGenerator generator = event.getGenerator();
        PackOutput output = event.getGenerator().getPackOutput();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();
        generator.addProvider(event.includeServer(), new GreedIslandAdvancementProvider(output, provider, helper));
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        LogUtils.getLogger().info("--- Server starting ---");
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        GreedIsland.LOGGER.info("Registering Capabilities");
        event.register(IMoreHealth.class);
        event.register(BookItemStackHandler.class);
    }

    @SubscribeEvent
    public static void onAttachEntityCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            GreedIsland.LOGGER.info("Attaching Player Capabilities");
            event.addCapability(new ResourceLocation(MODID, "morehealth"), new MoreHealthProvider());
            event.addCapability(new ResourceLocation(MODID, "book"), new GreedIslandProvider());
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        // Fetch & Copy Capability
        Player playerOld = event.getOriginal();
        Player playerNew = event.getEntity();
        IMoreHealth capOld = MoreHealth.getFromPlayer(playerOld);
        IMoreHealth capNew = MoreHealth.getFromPlayer(playerNew);
        ItemStackHandler bookOld = playerOld.getCapability(BOOK_CAPABILITY, null).orElseThrow(() -> new IllegalArgumentException("Book can not be null"));
        ItemStackHandler bookNew = playerNew.getCapability(BOOK_CAPABILITY, null).orElseThrow(() -> new IllegalArgumentException("Book can not be null"));
        bookNew.deserializeNBT(bookOld.serializeNBT());
        capNew.copy(capOld);
    }

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key key) {
        if (BOOK.consumeClick()) {
            PacketManager.sendToServer(new SyncBookPacket(2, new CompoundTag()));
        }
    }

    @SubscribeEvent
    public static void onMainMenu(PlayerContainerEvent.Close event) {
        if (event.getContainer() instanceof BookMenu) {
            BookMenu menu = (BookMenu) event.getContainer();
            menu.bookItemStackHandler.triggerAdvancements((ServerPlayer) event.getEntity());
        }
    }

    @SubscribeEvent
    public static void onDeathScreen(ScreenEvent.Opening event) {
        if (event.getNewScreen() instanceof TitleScreen) {
            event.setNewScreen(new MainMenu());
        }

        if (event.getNewScreen() instanceof DeathScreen) {
            event.setNewScreen(new DeathMenu());
        }
    }

}
