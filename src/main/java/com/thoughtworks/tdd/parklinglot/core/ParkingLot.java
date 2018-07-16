package com.thoughtworks.tdd.parklinglot.core;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int size;
    private Map<Receipt, Car> parkedCars = new HashMap<>();

    public ParkingLot(int size) {
        this.size = size;
    }

    public Receipt park(Car car) {
        if (parkedCars.size() == this.size) {
            throw new ParkingLotFullException();
        }

        Receipt key = new Receipt();
        this.parkedCars.put(key, car);
        return key;
    }

    public boolean containCar(Receipt parkingTicket) {
        return this.parkedCars.containsKey(parkingTicket);
    }


    public Car unPark(Receipt receipt) {
        return this.parkedCars.remove(receipt);
    }

    public boolean isFull() {
        return this.parkedCars.size() == size;
    }
}
