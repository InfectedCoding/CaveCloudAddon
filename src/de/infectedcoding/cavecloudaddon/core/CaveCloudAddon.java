package de.infectedcoding.cavecloudaddon.core;

import de.infectedcoding.cavecloudaddon.listeners.PlayerJoinListener;
import de.infectedcoding.cavecloudaddon.listeners.PlayerQuitListener;
import de.infectedcoding.cavecloudaddon.util.ConfigManager;
import net.cavefire.cavecloud.bukkit.CaveCloud;
import net.cavefire.cavecloud.bukkit.api.GameAPI;
import net.cavefire.cavecloud.bukkit.api.Gamestate;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Copyright (c) 2017 by InfectedCoding
 */
public class CaveCloudAddon extends JavaPlugin {

    private ConfigManager configManager;

    @Override
    public void onEnable() {
        /*
        CONFIG
         */
        if(!getDataFolder().exists()){
            getDataFolder().mkdir();
        }
        configManager = new ConfigManager(this);

        /*
        Read gamestate from config.yml
         */
        Gamestate gamestate = Gamestate.ONLINE;
        String gamestateString = configManager.getGameState();

        if(gamestateString.equalsIgnoreCase("ONLINE")){
            gamestate = Gamestate.ONLINE;
        } else if(gamestateString.equalsIgnoreCase("FULL")){
            gamestate = Gamestate.FULL;
        } else if(gamestateString.equalsIgnoreCase("INGAME")){
            gamestate = Gamestate.INGAME;
        } else if(gamestateString.equalsIgnoreCase("END")){
            gamestate = Gamestate.END;
        } else if(gamestateString.equalsIgnoreCase("LOBBY")){
            gamestate = Gamestate.LOBBY;
        }


        /*
        CAVECLOUD API

        GameStats, you can use:
        ONLINE, FULL, INGAME, END, LOBBY
         */
        CaveCloud.gameAPI
                .setGameState(gamestate)
                .setPlayers(0)
                .setMaxplayers(configManager.getMaxPlayers())
                .setMotd(configManager.getMOTD())
                .update();

        /*
        INIT LISTENERS
         */
        new PlayerJoinListener(this);
        new PlayerQuitListener(this);

        // print
        System.out.println("CaveCloudAddon - Plugin enabled");
    }

    @Override
    public void onDisable() {
        // print
        System.out.println("CaveCloudAddon - Plugin disabled");
    }

    /*
    GETTERS
     */
    public ConfigManager getConfigManager() {
        return configManager;
    }
}
