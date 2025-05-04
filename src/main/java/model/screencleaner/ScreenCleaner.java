package model.screencleaner;

public class ScreenCleaner implements IScreenCleaner {

    public void clearScreen()
    {
        System.out.println("asddhthdghghfgfgjfgjfgjfgjfgjfgj");
        try
        {
            if (System.getProperty("os.name").contains("Windows"))
            {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
        } catch (Exception e)
        {
            System.out.println("Error [console cleaning]: " + e.getMessage());
        }
    }
}