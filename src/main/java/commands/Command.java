package commands;

import management.Context;

import java.util.ArrayList;

public abstract class Command
{
    abstract public void execute(Context appContext, String[] arguments);
}