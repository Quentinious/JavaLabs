package commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import exceptions.ArgumentsCountException;
import exceptions.IncorrectArgumentTypeException;
import management.Context;

public class CommandDefine extends Command
{
    private static final Logger logger = LogManager.getLogger(CommandDefine.class);
    @Override
    public void execute(Context appContext, String[] arguments)
    {

        if (arguments.length != 2)
        {
            logger.error("Incorrect number of arguments: {} while 2 required", arguments.length);
            throw new ArgumentsCountException("DEFINE", arguments.length, 2);
        }
        //check types of arguments
        for (int i = 0; i < arguments[0].length(); ++i)
        {
            if(!Character.isLetter(arguments[0].charAt(i)))
            {
                logger.error("Incorrect character at index: {} ", i);
                throw new IncorrectArgumentTypeException("DEFINE", 0, arguments[0]);
            }
        }

        try
        {
            if (appContext.getDefinitions().containsKey(arguments[1]))
            {
                arguments[1] = "" + appContext.getDefinitions().get(arguments[1]);
            }
            double value = Double.parseDouble(arguments[1]);
            appContext.getDefinitions().put(arguments[0], value);
        } catch (NumberFormatException e)
        {
            logger.error("Incorrect number format, argument is: {}", arguments[1]);
            throw new IncorrectArgumentTypeException("DEFINE", 1, arguments[1]);
        }

    }
}