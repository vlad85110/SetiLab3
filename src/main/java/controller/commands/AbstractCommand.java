package controller.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import controller.commands.config.CommandConfig;
import parser.ConfigParser;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public abstract class AbstractCommand implements Command {
    protected final ConfigParser configParser;

    protected final ObjectMapper mapper = new ObjectMapper();

    public AbstractCommand(ConfigParser configParser) {
        this.configParser = configParser;
    }

    protected File getConfigFile() {
        return new File(configParser.getCommandConfigPath(this.getClass().getSimpleName()));
    }

    protected Optional<? extends CommandConfig> getConfig() {
        File file = getConfigFile();

        try {
            return Optional.of(mapper.readValue(file, getConfigClass()));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    protected abstract Class<? extends CommandConfig> getConfigClass();
}
