package de.infectedcoding.cavecloudaddon.util;

import de.infectedcoding.cavecloudaddon.core.CaveCloudAddon;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Copyright (c) 2017 by InfectedCoding
 */
public class ConfigManager {

    private final CaveCloudAddon plugin;
    private File file;
    private FileConfiguration cfg;

    public ConfigManager(CaveCloudAddon plugin){
        this.plugin = plugin;
        file = new File(plugin.getDataFolder().getPath(), "config.yml");
        checkFile();
        cfg = YamlConfiguration.loadConfiguration(file);
        addDefaults();
    }

    private void checkFile(){
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void addDefaults(){
        cfg.options().copyDefaults(true);
        cfg.options().header("CaveCloudAddon - v"+plugin.getDescription().getVersion());

        cfg.addDefault("GameState", "ONLINE");
        cfg.addDefault("MaxPlayers", Bukkit.getMaxPlayers());
        cfg.addDefault("MOTD", Bukkit.getMotd());

        save();
    }

    public String getGameState(){
        return cfg.getString("GameState");
    }

    public Integer getMaxPlayers(){
        return cfg.getInt("MaxPlayers");
    }

    public String getMOTD(){
        return cfg.getString("MOTD");
    }


    private void save(){
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
