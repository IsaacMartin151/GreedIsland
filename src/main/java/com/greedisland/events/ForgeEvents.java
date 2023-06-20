package com.greedisland.events;

import com.greedisland.GreedIsland;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.greedisland.registry.KeyMappings.BOOK;

@Mod.EventBusSubscriber(modid = GreedIsland.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ForgeEvents {
    @SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent event) {
        // Dev tools
        event.register(BOOK);
    }
}
