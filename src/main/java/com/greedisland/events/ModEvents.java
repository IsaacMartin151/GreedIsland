package com.greedisland.events;

import com.greedisland.GreedIsland;
import com.greedisland.container.BookItemStackHandler;
import com.greedisland.container.GreedIslandProvider;
import com.greedisland.packets.PacketManager;
import com.greedisland.packets.SyncBookPacket;
import com.levelhearts.IMoreHealth;
import com.levelhearts.MoreHealth;
import com.levelhearts.MoreHealthProvider;
import com.mojang.logging.LogUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.ItemStackHandler;

import static com.greedisland.GreedIsland.MODID;
import static com.greedisland.container.GreedIslandProvider.BOOK_CAPABILITY;
import static com.greedisland.registry.KeyMappings.BOOK;

@Mod.EventBusSubscriber
public class ModEvents
{
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

//    @SubscribeEvent
//    public static void onMainMenu(ScreenEvent.Init event) {
//        if (event.getScreen() instanceof TitleScreen) {
//            //event.getScreen().getMinecraft().setScreen(new MainMenu());
//        }
//    }

}