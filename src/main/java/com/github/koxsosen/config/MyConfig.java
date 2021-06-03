package com.github.koxsosen.config;

import space.arim.dazzleconf.ConfigurationOptions;
import space.arim.dazzleconf.error.InvalidConfigException;
import space.arim.dazzleconf.ext.snakeyaml.SnakeYamlConfigurationFactory;
import space.arim.dazzleconf.helper.ConfigurationHelper;

import java.io.IOException;
import java.nio.file.Path;

public class MyConfig {

    static MyConfig myConfig;
    {
        SnakeYamlConfigurationFactory.create(ConfigValues.class, ConfigurationOptions.defaults()).loadDefaults();
        try {
            myConfig = new ConfigurationHelper <>(Path.of("."), "duckyconf.yml", SnakeYamlConfigurationFactory.create
                    (MyConfig.class, ConfigurationOptions.defaults())).reloadConfigData();
        } catch (IOException | InvalidConfigException e) {
            e.printStackTrace();
        }
    }

}