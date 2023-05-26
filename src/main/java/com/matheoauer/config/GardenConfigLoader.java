package com.matheoauer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matheoauer.config.sprite.AtlasLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

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

    public Optional<VegetableConf> findVegetable(String name) {
        return getVegetables().stream().filter(v -> v.getName().equalsIgnoreCase(name)).findFirst();
    }

    public List<VegetableConf> getVegetables() {
        return getGardenConfiguration().getVegetables();
    }
}
