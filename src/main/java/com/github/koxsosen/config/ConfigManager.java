package com.github.koxsosen.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import space.arim.dazzleconf.ConfigurationFactory;
import space.arim.dazzleconf.ConfigurationOptions;
import space.arim.dazzleconf.error.ConfigFormatSyntaxException;
import space.arim.dazzleconf.error.InvalidConfigException;
import space.arim.dazzleconf.ext.snakeyaml.CommentMode;
import space.arim.dazzleconf.ext.snakeyaml.SnakeYamlConfigurationFactory;
import space.arim.dazzleconf.ext.snakeyaml.SnakeYamlOptions;
import space.arim.dazzleconf.helper.ConfigurationHelper;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;

// https://github.com/A248/DazzleConf/wiki/Configuration-Manager
public final class ConfigManager<C> {

    private final ConfigurationHelper <C> configHelper;
    private volatile C configData;
    private static final Logger logger = LogManager.getLogger(ConfigManager.class);

    private ConfigManager(ConfigurationHelper configHelper) {
        this.configHelper = configHelper;
    }

    public static <C> ConfigManager create(Path configFolder, String filename, Class<C> configClass) {

        SnakeYamlOptions yamlOptions = new SnakeYamlOptions.Builder().commentMode(CommentMode.alternativeWriter()).build();
        ConfigurationFactory <C> configFactory = SnakeYamlConfigurationFactory.create(configClass, ConfigurationOptions.defaults(), yamlOptions);

        return new ConfigManager(new ConfigurationHelper<>(configFolder, filename, configFactory));

    }

    public void reloadConfig() {

        try {
            configData = configHelper.reloadConfigData();
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);

        } catch (ConfigFormatSyntaxException ex) {
            configData = configHelper.getFactory().loadDefaults();
            logger.error("The yaml syntax in your configuration is invalid.");
            ex.printStackTrace();
        } catch (InvalidConfigException ex) {
            configData = configHelper.getFactory().loadDefaults();
            logger.error("One of the values in your configuration is not valid, wrong data types maybe?");
            ex.printStackTrace();
        }

    }

    public C getConfigData() {

        C configData = this.configData;
        if (configData == null) {
            throw new IllegalStateException("Configuration has not been loaded yet.");
        }

        return configData;
    }
}
