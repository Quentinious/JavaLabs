package exceptions;

public class IncorrectCommandNameException extends RuntimeException
{
    public IncorrectCommandNameException()
    {
        super("Incorrect command name was used\n");
    }

    public IncorrectCommandNameException(String usedName)
    {
        super("Incorrect command name was used: " + usedName + "\n");
    }

}