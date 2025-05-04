package org.example.controller;

import org.example.model.GameModel;
import org.example.view.GameView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameController
{
    private  GameModel model;
    private  GameView view;
    private  Timer gameTimer;

    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean spacePressed = false;

    private int shootCooldown = 0;
    private static final int SHOOT_INTERVAL = 15;

    private int enemySpawnCooldown = 0;
    private static final int ENEMY_SPAWN_INTERVAL = 60;

    public GameController(GameModel model, GameView view)
    {
        this.model = model;
        this.view = view;

        setupKeyBindings();

        gameTimer = new Timer(16, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (!model.isGameOver())
                {

                    if (leftPressed)
                        model.movePlayer(-5);
                    if (rightPressed)
                        model.movePlayer(5);


                    if (spacePressed && shootCooldown <= 0)
                    {
                        model.shoot();
                        shootCooldown = SHOOT_INTERVAL;
                    }

                    if (shootCooldown > 0)
                        shootCooldown--;


                    enemySpawnCooldown--;
                    if (enemySpawnCooldown <= 0)
                    {
                        model.spawnEnemy();
                        enemySpawnCooldown = ENEMY_SPAWN_INTERVAL;
                    }


                    model.updateGame();
                    view.repaint();
                }
            }
        });

        gameTimer.start();
    }

    private void setupKeyBindings()
    {
        view.setFocusable(true);
        view.requestFocusInWindow();
        view.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if (model.isGameOver()) return;

                switch (e.getKeyCode())
                {
                    case KeyEvent.VK_LEFT -> leftPressed = true;
                    case KeyEvent.VK_RIGHT -> rightPressed = true;
                    case KeyEvent.VK_SPACE -> spacePressed = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e)
            {
                if (model.isGameOver()) return;

                switch (e.getKeyCode())
                {
                    case KeyEvent.VK_LEFT -> leftPressed = false;
                    case KeyEvent.VK_RIGHT -> rightPressed = false;
                    case KeyEvent.VK_SPACE -> spacePressed = false;
                }
            }
        });
    }
}
