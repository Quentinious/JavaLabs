package commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import exceptions.ArgumentsCountException;
import exceptions.SmallStackSizeException;
import management.Context;
import java.util.EmptyStackException;

public class CommandPrint extends Command
{
    private static final Logger logger = LogManager.getLogger(CommandPrint.class);

    @Override
    public void execute(Context appContext, String[] arguments)
    {
        //check the number of arguments
        if (arguments.length != 0)
        {
            logger.error("Wrong number of arguments: {} while 0 required",arguments.length);
            throw new ArgumentsCountException("PRINT", arguments.length, 0);
        }

        try
        {
            System.out.println(appContext.getArgumentStack().peek());
        }catch (EmptyStackException e)
         {
            logger.error("Stack had got {} elements when 1 were needed", 0);
            throw new SmallStackSizeException("PRINT", 0, 1);
        }
    }
}