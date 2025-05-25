package org.example.factory.model.producer_consumer;

@FunctionalInterface
public interface Produce<T> {
    T produce(Integer countProduced);
}
