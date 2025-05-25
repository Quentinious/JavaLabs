package org.example.factory.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.factory.model.producer_consumer.Consumer;
import org.example.factory.model.producer_consumer.Producer;
import org.example.factory.model.producer_consumer.Warehouse;
import org.example.factory.model.products.Accessory;
import org.example.factory.model.products.Bodywork;
import org.example.factory.model.products.Car;
import org.example.factory.model.products.Engine;
import org.example.threadPool.ThreadPool;

import java.util.ArrayList;

public class Factory {
    private final static Logger logger = LogManager.getLogger(Factory.class);

    public final Producer<Engine> engineProducer;
    public final Producer<Bodywork> bodyworkProducer;
    public final ArrayList<Producer<Accessory>> accessoryProducersList;

    public final Warehouse<Engine> engineWarehouse;
    public final Warehouse<Bodywork> bodyworkWarehouse;
    public final Warehouse<Accessory> accessoryWarehouse;

    public final ThreadPool assemblyShop;

    public final Warehouse<Car> carWarehouse;
    private final WarehouseController warehouseController;

    public final ArrayList<Consumer<Car>> dealers;


    private final ConfigReader configReader;


    public Factory() {
        configReader = new ConfigReader();

        engineWarehouse = new Warehouse<>(configReader.getEngineStorageVolume());
        bodyworkWarehouse = new Warehouse<>(configReader.getBodyworkStorageVolume());
        accessoryWarehouse = new Warehouse<>(configReader.getAccessoryStorageVolume());

        engineProducer = new Producer<>(engineWarehouse, configReader.getEngineProducerStartCooldown(),
                (Integer countProduced)-> new Engine(Integer.toString(countProduced+1))
        );
        bodyworkProducer = new Producer<>(bodyworkWarehouse, configReader.getBodyworkProducerStartCooldown(),
                (Integer countProduced)-> new Bodywork(Integer.toString(countProduced+1))
        );
        accessoryProducersList = new ArrayList<>();
        for (int i = 0; i < configReader.getCountAccessoryProducers(); i++) {
            accessoryProducersList.add(new Producer<>(accessoryWarehouse, configReader.getAccessoryProducerStartCooldown(),
                    (Integer countProduced)->new Accessory(Integer.toString(countProduced+1)))
            );
        }

        assemblyShop = new ThreadPool(configReader.getCountWorkers());

        carWarehouse = new Warehouse<>(configReader.getCarStorageVolume());
        warehouseController = new WarehouseController(carWarehouse,assemblyShop,engineWarehouse,bodyworkWarehouse,accessoryWarehouse);

        dealers = new ArrayList<>();
        for (int i = 0; i < configReader.getCountDealers(); i++) {
            int finalI = i;
            dealers.add(new Consumer<>(carWarehouse, configReader.getCarConsumerStartCooldown(),
                    (Car car, Integer countRecieved)->{
                        Logger lambdaLogger = LogManager.getLogger("ru.nsu.ccfit.chumak.factory");
                        lambdaLogger.info("Dealer: " + finalI + ", Auto: " + car.getId() + " ( Body: " + car.getBodywork().getId() + ", Motor: " + car.getEngine().getId() + ", Accessory: " + car.getAccessory().getId() + " )");
                    })
            );
        }
    }

    public void startWork(){
        engineProducer.start();
        bodyworkProducer.start();
        for (Producer<Accessory> producer : accessoryProducersList) {
            producer.start();
        }

        warehouseController.start();

        for (Consumer<Car> consumer : dealers) {
            consumer.start();
        }
    }

    public void stopWork() throws InterruptedException {
        /* interrupt all threads */
        engineProducer.interrupt();
        bodyworkProducer.interrupt();
        for (Producer<Accessory> producer : accessoryProducersList) {
            producer.interrupt();
        }

        for (Consumer<Car> consumer : dealers) {
            consumer.interrupt();
        }

        warehouseController.interrupt();

        assemblyShop.interruptAll();


        /*join all threads*/
        engineProducer.join();
        bodyworkProducer.join();
        for (Producer<Accessory> producer : accessoryProducersList) {
            producer.join();
        }

        for (Consumer<Car> consumer : dealers) {
            consumer.join();
        }

        warehouseController.join();

        assemblyShop.joinAll();
    }


}
