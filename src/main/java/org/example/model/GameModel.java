package org.example.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameModel
{
    private Entity player;
    private List<Entity> bullets = new ArrayList<>();
    private List<Entity> enemies = new ArrayList<>();

    private int score = 0;
    private boolean gameOver = false;

    private final int WIDTH = 400;
    private final int HEIGHT = 600;


    public GameModel()
    {
        int playerWidth = 40;
        int playerHeight = 20;
        player = new Entity(WIDTH / 2 - playerWidth / 2, HEIGHT - 60, playerWidth, playerHeight);
    }

    public Entity getPlayer() { return player; }
    public List<Entity> getBullets() { return bullets; }
    public List<Entity> getEnemies() { return enemies; }
    public int getScore() { return score; }
    public boolean isGameOver() { return gameOver; }

    public void movePlayer(int dx)
    {
        player.move(dx, 0);
        if (player.getX() < 0) player.move(-player.getX(), 0);
        if (player.getX() + player.getWidth() > WIDTH)
            player.move(WIDTH - (player.getX() + player.getWidth()), 0);
    }

    public void shoot()
    {
        bullets.add(new Entity(player.getX() + player.getWidth() / 2 - 2, player.getY(), 4, 10));
    }

    public void spawnEnemy()
    {
        int x = (int)(Math.random() * (WIDTH - 30));
        enemies.add(new Entity(x, 0, 30, 20));
    }


    public void updateGame()
    {
        for (Entity bullet : bullets)
        {
            bullet.move(0, -5);
        }

        for (Entity enemy : enemies)
        {
            enemy.move(0, 2);
        }

        Iterator<Entity> bulletIter = bullets.iterator();
        while (bulletIter.hasNext())
        {
            Entity bullet = bulletIter.next();
            Iterator<Entity> enemyIter = enemies.iterator();
            while (enemyIter.hasNext())
            {
                Entity enemy = enemyIter.next();
                if (bullet.intersections(enemy))
                {
                    bulletIter.remove();
                    enemyIter.remove();
                    score += 10;
                    break;
                }
            }
        }

        bullets.removeIf(b -> b.getY() < 0);

        for (Entity enemy : enemies)
        {
            if (enemy.getY() > HEIGHT)
            {
                gameOver = true;
                break;
            }
        }

        for (Entity enemy : enemies)
        {
            if (player.intersections(enemy))
            {
                gameOver = true;
                break;
            }
        }
    }
}


