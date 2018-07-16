package com.thoughtworks.tdd.parklinglot.core;

public class ParkingLotFullException extends RuntimeException {

    public ParkingLotFullException(RuntimeException e) {
        super(e);
    }

    public ParkingLotFullException() {

    }
}
