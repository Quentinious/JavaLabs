package org.example.factory;

import org.example.factory.model.Factory;

public class Main {
    public static void main(String[] args) {
        Factory factory = new Factory();
        FactoryGUI gui = new FactoryGUI(factory);
        gui.setVisible(true);

        // Обновление статистики в отдельном потоке
        new Thread(() -> {
            while (true) {
                gui.updateStats(
                        factory.engineWarehouse.size(),
                        factory.bodyworkWarehouse.size(),
                        factory.accessoryWarehouse.size(),
                        factory.carWarehouse.size(),
                        factory.assemblyShop.getTaskCount()
                );
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }).start();
    }
}