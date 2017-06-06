package de.infectedcoding.cavecloudaddon.listeners;

import de.infectedcoding.cavecloudaddon.core.CaveCloudAddon;
import net.cavefire.cavecloud.bukkit.CaveCloud;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Copyright (c) 2017 by InfectedCoding
 */
public class PlayerQuitListener implements Listener {

    private final CaveCloudAddon plugin;

    public PlayerQuitListener(CaveCloudAddon plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        /*
        CAVECLOUD API
         */
        CaveCloud.gameAPI
                .setPlayers(Bukkit.getOnlinePlayers().size()-1)
                .update();
    }
}
