package com.matheoauer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matheoauer.config.sprite.AtlasLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static java.lang.System.Logger.Level;

public class GardenConfigLoader {

    public static final String CONFIG_FILENAME = "config.json";
    public static final String ATLAS_FILENAME = "atlas.png";
    // Singleton pattern
    private static GardenConfigLoader instance;
    private final System.Logger LOGGER = System.getLogger("GardenConfigLoader");
    private final GardenConfiguration gardenConfiguration;
    private final AtlasLoader atlasLoader;

    private GardenConfigLoader() {
        ObjectMapper mapper = new ObjectMapper();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(CONFIG_FILENAME);
        try (
                InputStream atlasIs = classloader.getResourceAsStream(ATLAS_FILENAME)

        ) {
            this.gardenConfiguration = mapper.readValue(is, GardenConfiguration.class);
            atlasLoader = new AtlasLoader(atlasIs, 32, 32, 2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static GardenConfigLoader getInstance() {
        if (instance == null) {
            instance = new GardenConfigLoader();
        }
        return instance;
    }

    public GardenConfiguration getGardenConfiguration() {
        return gardenConfiguration;
    }

    public AtlasLoader getAtlasLoader() {
        return atlasLoader;
    }

    public Vegetable findVegetable(String name) {
        for (Vegetable v : getGardenConfiguration().getVegetables()) {
            if (v.getName().equalsIgnoreCase(name)) {
                return v;
            }
        }
        LOGGER.log(Level.ERROR, "No vegetable found for name " + name);
        return null;
    }

    public List<Vegetable> getVegetables() {
        return getGardenConfiguration().getVegetables();
    }
}
