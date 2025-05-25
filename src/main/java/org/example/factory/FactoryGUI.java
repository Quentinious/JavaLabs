package org.example.factory;



import org.example.factory.model.Factory;
import org.example.factory.model.producer_consumer.Consumer;
import org.example.factory.model.producer_consumer.Producer;
import org.example.factory.model.products.Accessory;
import org.example.factory.model.products.Car;

import javax.swing.*;
import java.awt.*;

public class FactoryGUI extends JFrame {
    private final Factory factory;
    private JLabel engineCountLabel, bodyworkCountLabel, accessoryCountLabel, carCountLabel, taskCountLabel;
    private JSlider engineSlider, bodyworkSlider, accessorySlider, dealerSlider;

    public FactoryGUI(Factory factory) {
        this.factory = factory;
        setTitle("Factory Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        add(new JLabel("Engine Cooldown (ms):"));
        engineSlider = new JSlider(100, 1000, 100);
        engineSlider.addChangeListener(e -> factory.engineProducer.setCooldown((long) engineSlider.getValue()));
        add(engineSlider);

        add(new JLabel("Bodywork Cooldown (ms):"));
        bodyworkSlider = new JSlider(100, 1000, 100);
        bodyworkSlider.addChangeListener(e -> factory.bodyworkProducer.setCooldown((long) bodyworkSlider.getValue()));
        add(bodyworkSlider);

        add(new JLabel("Accessory Cooldown (ms):"));
        accessorySlider = new JSlider(100, 1000, 100);
        accessorySlider.addChangeListener(e -> {
            for (Producer<Accessory> p : factory.accessoryProducersList) {
                p.setCooldown((long) accessorySlider.getValue());
            }
        });
        add(accessorySlider);

        add(new JLabel("Dealer Cooldown (ms):"));
        dealerSlider = new JSlider(100, 1000, 100);
        dealerSlider.addChangeListener(e -> {
            for (Consumer<Car> d : factory.dealers) {
                d.setCooldown((long) dealerSlider.getValue());
            }
        });
        add(dealerSlider);

        engineCountLabel = new JLabel("Engines: 0");
        add(engineCountLabel);
        bodyworkCountLabel = new JLabel("Bodyworks: 0");
        add(bodyworkCountLabel);
        accessoryCountLabel = new JLabel("Accessories: 0");
        add(accessoryCountLabel);
        carCountLabel = new JLabel("Cars: 0");
        add(carCountLabel);
        taskCountLabel = new JLabel("Tasks: 0");
        add(taskCountLabel);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> factory.startWork());
        add(startButton);

        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(e -> {
            try {
                factory.stopWork();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        });
        add(stopButton);

        pack();
        setLocationRelativeTo(null);
    }

    public void updateStats(int engines, int bodyworks, int accessories, int cars, int tasks) {
        SwingUtilities.invokeLater(() -> {
            engineCountLabel.setText("Engines: " + engines);
            bodyworkCountLabel.setText("Bodyworks: " + bodyworks);
            accessoryCountLabel.setText("Accessories: " + accessories);
            carCountLabel.setText("Cars: " + cars);
            taskCountLabel.setText("Tasks: " + tasks);
        });
    }
}