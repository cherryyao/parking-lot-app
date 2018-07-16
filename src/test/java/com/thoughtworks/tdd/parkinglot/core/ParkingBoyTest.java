package com.thoughtworks.tdd.parkinglot.core;

import com.thoughtworks.tdd.parklinglot.core.*;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.fail;

public class ParkingBoyTest {


    @Test
    public void should_Car_can_be_parking_when_there_is_enough_rooms_in_any_parking_lot(){
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        Car firstCar = new Car();
        Car secondCar = new Car();

        ParkingBoy parkingBoy = new ParkingBoy(firstParkingLot, secondParkingLot);

        try {
            parkingBoy.park(firstCar);
            parkingBoy.park(secondCar);
        } catch (ParkingLotFullException e) {
            fail("should process success");
        }
    }

    @Test
    public void should_Car_can_not_be_parking_when_there_is_no_enough_rooms_in_any_parking_lot(){
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car Car = new Car();

        try {
            parkingBoy.park(Car);
            parkingBoy.park(Car);
            fail("should process fail");
        } catch (ParkingLotFullException e) {

        }
    }

    @Test
    public void should_know_parking_lot_is_not_full_given_any_parking_lot_has_enough_room() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        Car firstCar = new Car();

        ParkingBoy parkingBoy = new ParkingBoy(firstParkingLot, secondParkingLot);
        parkingBoy.park(firstCar);
        assertThat(parkingBoy.isFull(), is(false));
    }

    @Test
    public void should_know_parking_lot_is_full_given_all_parking_lots_has_no_enough_room() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        Car firstCar = new Car();
        Car secondCar = new Car();

        ParkingBoy parkingBoy = new ParkingBoy(firstParkingLot, secondParkingLot);
        parkingBoy.park(firstCar);
        parkingBoy.park(secondCar);

        assertThat(parkingBoy.isFull(), is(true));
    }

    @Test
    public void should_get_parked_Car_with_ticket_after_parking_in_any_parking_lot(){
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(firstParkingLot, secondParkingLot);
        Car firstCar = new Car();
        Car secondCar = new Car();

        parkingBoy.park(firstCar);
        Receipt parkingTicket = parkingBoy.park(secondCar);

        Car CarFromParkingLot = parkingBoy.getCar(parkingTicket);
        assertThat(CarFromParkingLot, not(firstCar));
        assertThat(CarFromParkingLot, is(secondCar));
    }

    @Test
    public void should_get_null_with_ticket_which_is_not_exist() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(firstParkingLot, secondParkingLot);

        Car firstCar = new Car();
        Car secondCar = new Car();

        parkingBoy.park(firstCar);
        parkingBoy.park(secondCar);
        Receipt parkingTicket = new Receipt();

        assertThat(parkingBoy.getCar(parkingTicket), is(nullValue()));
    }

    @Test
    public void should_one_ticket_can_only_get_once(){
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(firstParkingLot, secondParkingLot);

        Car firstCar = new Car();
        Car secondCar = new Car();

        Receipt parkingTicket = parkingBoy.park(firstCar);
        parkingBoy.park(secondCar);

        assertThat(parkingBoy.getCar(parkingTicket), is(firstCar));
        assertThat(parkingBoy.getCar(parkingTicket), is(nullValue()));
    }

    @Test
    public void should_Car_can_be_parked_when_parking_lot_which_was_full_is_gotten_a_Car() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(firstParkingLot, secondParkingLot);

        Car firstCar = new Car();
        Car secondCar = new Car();



        try {
            Receipt parkingTicket = parkingBoy.park(firstCar);
            parkingBoy.park(secondCar);
            parkingBoy.getCar(parkingTicket);
            parkingBoy.park(firstCar);
        } catch (ParkingLotFullException e) {
            fail("should not be called");
        }
    }
}
