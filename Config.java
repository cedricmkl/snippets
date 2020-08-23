package de.coolepizza.system.file;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {
    private FileConfiguration configuration;
    private File file;
    public Config(String name , File path){
        file = new File(path, name);
        if (!file.exists())
            path.mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        configuration = new YamlConfiguration();
        try {
            configuration.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public File getFile() {
        return file;
    }

    public FileConfiguration getConfiguration() {
        return configuration;
    }
    public void save(){
        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void reload(){
        try {
            configuration.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }

    }
    public Object get(String path){
        return configuration.get(path);
    }
    public void set(String path , Object obj){
        configuration.set(path , obj);
    }
    public boolean contains(String path){
        return configuration.contains(path);
    }
    public boolean isClear(){
        return configuration.getKeys(false).size() == 0;
    }
}
