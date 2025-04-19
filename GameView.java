package view;

import org.example.model.GameModel;
import org.example.model.Entity;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameView extends JPanel
{

    private GameModel model;

    public GameView(GameModel model)
    {
        this.model = model;
        setPreferredSize(new Dimension(400, 600));
        setBackground(Color.PINK);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        drawPlayer(g, model.getPlayer());
        drawEntities(g, model.getBullets(), Color.YELLOW);
        drawEntities(g, model.getEnemies(), Color.RED);
        drawScore(g, model.getScore());

        if (model.isGameOver())
        {
            drawGameOver(g);
        }
    }

    private void drawPlayer(Graphics g, Entity player)
    {
        g.setColor(Color.GREEN);
        g.fillRect(player.getX(), player.getY(), player.getWidth(), player.getHeight());
    }

    private void drawEntities(Graphics g, List<Entity> entities, Color color)
    {
        g.setColor(color);
        for (Entity e : entities)
        {
            g.fillRect(e.getX(), e.getY(), e.getWidth(), e.getHeight());
        }
    }

    private void drawScore(Graphics g, int score)
    {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        g.drawString("Score: " + score, 10, 20);
    }

    private void drawGameOver(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.drawString("GAME OVER", 100, 300);
    }
}
