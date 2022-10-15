package parser;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class ConfigParser {
    private final HashMap<String, String> configPaths;

    public ConfigParser() throws IOException {
        configPaths = readFile("src/main/resources/configs/configPaths.properties");
    }

    public HashMap<String, String> readFile(String fileName) throws IOException {
        Properties properties = new Properties();
        FileReader reader;

        reader = new FileReader(fileName);
        properties.load(reader);

        var config = new HashMap<String, String>();
        for (var i : properties.stringPropertyNames()) {
            config.put(i, properties.getProperty(i));
        }

        return config;
    }

    public HashMap<String, String> getConfigPaths() {
        return configPaths;
    }

    public String getCommandConfigPath(String commandName) {
        return configPaths.get(commandName);
    }
}
