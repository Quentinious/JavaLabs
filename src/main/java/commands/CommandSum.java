package commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import exceptions.ArgumentsCountException;
import exceptions.SmallStackSizeException;
import management.Context;
import java.util.EmptyStackException;

public class CommandSum extends Command
{
    private static final Logger logger = LogManager.getLogger(CommandSum.class);
    @Override
    public void execute(Context appContext, String[] arguments)
    {
        //check the number of arguments
        if (arguments.length != 0)
        {
            logger.error("Wrong number of arguments: {} while 0 required",arguments.length);
            throw new ArgumentsCountException("+", arguments.length, 0);
        }

        int countUsedArguments = 0;
        double[] components = {0,0};
        try
        {
            components[countUsedArguments] = appContext.getArgumentStack().pop();
            ++countUsedArguments;
            components[countUsedArguments] = appContext.getArgumentStack().pop();
            ++countUsedArguments;
            appContext.getArgumentStack().push(components[0]+components[1]);
        } catch(EmptyStackException e)
        {
            logger.error("Stack had got {} elements when 2 were needed", countUsedArguments);
            for(int i = countUsedArguments - 1; i >=0; --i){
                appContext.getArgumentStack().push(components[i]);
            }
            throw new SmallStackSizeException("+", countUsedArguments, 2);
        }
    }
}