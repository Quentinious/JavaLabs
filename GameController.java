package org.example.controller;//package controller;

import org.example.model.GameModel;
//import org.example.view.*;
//import model.GameModel;
import view.GameView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameController
{

//    private boolean leftPressed = false;
//    private boolean rightPressed = false;
    private GameModel model;
    private GameView view;
    private Timer gameTimer;

    public GameController(GameModel model, GameView view)
    {
        this.model = model;
        this.view = view;

        setupKeyBindings();


        gameTimer = new Timer(16, new ActionListener()
        {
            int enemySpawnCounter = 0;

            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (!model.isGameOver())
                {
                    model.updateGame();
                    enemySpawnCounter++;
                    if (enemySpawnCounter >= 60)
                    {
                        model.spawnEnemy();
                        enemySpawnCounter = 0;
                    }

                    view.repaint();
                }
            }
        });

        gameTimer.start();
    }
//    //@Override
//    public void keyPressed(KeyEvent e)
//    {
//        if (e.getKeyCode() == KeyEvent.VK_LEFT) leftPressed = true;
//        if (e.getKeyCode() == KeyEvent.VK_RIGHT) rightPressed = true;
//    }
//
//   // @Override
//    public void keyReleased(KeyEvent e)
//    {
//        if (e.getKeyCode() == KeyEvent.VK_LEFT) leftPressed = false;
//        if (e.getKeyCode() == KeyEvent.VK_RIGHT) rightPressed = false;
//    }
//
//  //  @Override
//    public void actionPerformed(ActionEvent e)
//    {
//        if (leftPressed) model.player(-1);
//        if (rightPressed) model.player(1);
//        model.updateGame();
//        view.repaint();
//    }

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
                    case KeyEvent.VK_LEFT -> model.movePlayer(-5);
                    case KeyEvent.VK_RIGHT -> model.movePlayer(5);
                    case KeyEvent.VK_SPACE -> model.shoot();
                }
            }
        });
    }
}
