package org.example.model;

public class Entity
{
    protected int x,y;
    protected int width,height;
    public  Entity(int p_x,int p_y,int p_width,int p_height)
    {
        this.x = p_x;
        this.y = p_y;
        this.width = p_width;
        this.height = p_height;
    }


    public boolean intersections(Entity other)
    {
        return x < other.x + other.width && x + width > other.x &&
                y < other.y + other.height && y + height > other.y;
    }




    public void move(int dx,int dy)
    {
        x +=dx;
        y +=dy;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }


    //public

    public void setWidth(int width)
    {
        this.width = width;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }


    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getHeight()
    {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
