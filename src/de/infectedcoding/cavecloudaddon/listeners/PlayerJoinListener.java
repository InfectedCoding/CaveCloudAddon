package de.infectedcoding.cavecloudaddon.listeners;

import de.infectedcoding.cavecloudaddon.core.CaveCloudAddon;
import net.cavefire.cavecloud.bukkit.CaveCloud;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Copyright (c) 2017 by InfectedCoding
 */
public class PlayerJoinListener implements Listener {

    private final CaveCloudAddon plugin;

    public PlayerJoinListener(CaveCloudAddon plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        /*
        CAVECLOUD API
         */
        CaveCloud.getGameAPI()
                .setPlayers(Bukkit.getOnlinePlayers().size())
                .update();
    }
}
