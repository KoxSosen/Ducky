package com.github.koxsosen.configuration;

import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;
import org.spongepowered.configurate.serialize.SerializationException;

import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.github.koxsosen.Main.NUM_CORES;

public class LoadAndCreate {

    public static HoconConfiguration initialiseConfiguration() {

        final HoconConfigurationLoader loader = HoconConfigurationLoader.builder()
                .defaultOptions(configurationOptions -> configurationOptions.shouldCopyDefaults(true))
                .path(Path.of("duckyv2.conf"))
                .build();

        final CommentedConfigurationNode root;
        try {
            root = loader.load(); // Load from file
        } catch (IOException e) {
            System.err.println("An error occurred while loading this configuration: " + e.getMessage());
            if (e.getCause() != null) {
                e.getCause().printStackTrace();
            }
            System.exit(1);
            return null;
        }

        final HoconConfiguration configuration;
        try {
            configuration = root.get(HoconConfiguration.class); // Populate object
        } catch (SerializationException e) {
            throw new RuntimeException(e);
        }

        try {
            root.set(HoconConfiguration.class, configuration); // Update the backing node
        } catch (SerializationException e) {
            throw new RuntimeException(e);
        }

        try {
            loader.save(root); // Write to the original file
        } catch (ConfigurateException e) {
            throw new RuntimeException(e);
        }

        return configuration;
    }

    public static ExecutorService createFixedThreadPool() {
        return Executors.newFixedThreadPool(NUM_CORES, (task) -> {
            Thread thread = new Thread(task, "Ducky Thread Pool");
            thread.setDaemon(true); // mark as background thread so we don't block the JVM shutdown process
            return thread;
        });
    }

}
