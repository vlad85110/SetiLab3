package factory;

import controller.commands.Command;
import controller.commands.arguments.CommandArgs;
import parser.ConfigParser;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class CommandFactory {
    protected final HashMap<String, String> names;
    public CommandFactory(ConfigParser parser) throws IOException, NullPointerException {
        this.names = parser.readFile("src/main/resources/configs/commands.properties");
    }

    public Command createObject(String commandName,CommandArgs args) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Class<?> productClass;
        Command object;

        try {
            productClass = Class.forName("controller.commands." + names.get(commandName));
        } catch (ClassNotFoundException e) {
            productClass = Class.forName("controller.commands.requestcommands." + names.get(commandName));
        }
        object = (Command)productClass.getConstructor(CommandArgs.class).newInstance(args);

        return object;
    }
}
