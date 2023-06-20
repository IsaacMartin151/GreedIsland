package com.greedisland;

import com.greedisland.container.BookInventoryScreen;
import com.greedisland.packets.PacketManager;
import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import static com.greedisland.registry.MenuTypes.BOOK_MENU;
import static com.greedisland.registry.MenuTypes.MENU_TYPES;
import static com.greedisland.registry.Sounds.SOUNDS;

@Mod(GreedIsland.MODID)
public class GreedIsland
{
    public static final String MODID = "greedisland";
    public static final Logger LOGGER = LogUtils.getLogger();

    public GreedIsland()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::commonSetup);

        SOUNDS.register(modEventBus);
        MENU_TYPES.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    public void clientSetup(FMLClientSetupEvent event)
    {
        GreedIsland.LOGGER.info("Client setup");
        // Not thread safe
        event.enqueueWork(
                () -> MenuScreens.register(BOOK_MENU.get(), BookInventoryScreen::new)
        );
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("Common setup");
        PacketManager.register();
    }
}
