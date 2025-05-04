package factory;

import commands.Command;
import exceptions.IncorrectCommandNameException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Factory
{
    private static final Logger logger = LogManager.getLogger(Factory.class);
    private static final Map<String, Class<? extends Command>> commandMap = new HashMap<>();
    static
    {
        logger.info("Initializing Factory and loading command mappings");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Factory.class.getResourceAsStream("/factoryConfig.txt")))))
        {
            while (reader.ready())
            {
                String[] definition = reader.readLine().split(" ");
                if (definition.length == 2)
                {
                    String commandName = definition[0];
                    String className = definition[1];

                    Class<?> clazz = Class.forName(className);
                    if (!Command.class.isAssignableFrom(clazz))
                    {
                        logger.warn("Class {} does not implement Command interface", className);
                        continue;
                    }

                    commandMap.put(commandName, clazz.asSubclass(Command.class));
                    logger.info("Mapped command '{}' to class '{}'", commandName, className);
                }
            }

        } catch (IOException | ClassNotFoundException e)
        {
            logger.fatal("Failed to initialize command mappings", e);
            throw new RuntimeException(e);
        }
    }

    public Command createCommand(String commandName)
    {
        logger.info("Creating command instance for '{}'", commandName);
        Class<? extends Command> commandClass = commandMap.get(commandName);

        if (commandClass == null)
        {
            logger.error("Command '{}' not found", commandName);
            throw new IncorrectCommandNameException(commandName);
        }

        try
        {
            return commandClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException |
                 InvocationTargetException | NoSuchMethodException e)
        {
            logger.error("Failed to create command instance for '{}': {}", commandName, e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
