package org.example.factory.model;

import org.example.factory.model.producer_consumer.Warehouse;
import org.example.factory.model.products.Accessory;
import org.example.factory.model.products.Bodywork;
import org.example.factory.model.products.Car;
import org.example.factory.model.products.Engine;
import org.example.threadPool.Task;

public class Assembly implements Task {
    private final Warehouse<Engine> enginesWarehouse;
    private final Warehouse<Bodywork> bodyworksWarehouse;
    private final Warehouse<Accessory> accessoriesWarehouse;
    private final Warehouse<Car> carWarehouse;
    private final String carId;

    public Assembly(Warehouse<Engine> enginesWarehouse, Warehouse<Bodywork> bodyworksWarehouse, Warehouse<Accessory> accessoriesWarehouse, Warehouse<Car> carWarehouse, String carId) {
        this.enginesWarehouse = enginesWarehouse;
        this.bodyworksWarehouse = bodyworksWarehouse;
        this.accessoriesWarehouse = accessoriesWarehouse;
        this.carWarehouse = carWarehouse;
        this.carId = carId;
    }

    @Override
    public String getName() {
        return "assembly_car_task";
    }

    @Override
    public void performWork() {
        Engine engine = enginesWarehouse.peek();
        Bodywork bodywork = bodyworksWarehouse.peek();
        Accessory accessories = accessoriesWarehouse.peek();
        Car car = new Car(carId, engine, bodywork, accessories);
        carWarehouse.push(car);
    }
}
