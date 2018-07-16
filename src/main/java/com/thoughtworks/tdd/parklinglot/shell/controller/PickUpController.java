package com.thoughtworks.tdd.parklinglot.shell.controller;

import com.thoughtworks.tdd.parklinglot.core.Car;
import com.thoughtworks.tdd.parklinglot.core.ParkingBoy;
import com.thoughtworks.tdd.parklinglot.core.Receipt;
import com.thoughtworks.tdd.parklinglot.shell.io.Request;
import com.thoughtworks.tdd.parklinglot.shell.io.Response;

import java.util.UUID;

public class PickUpController implements BaseController {

    private final Request request;
    private final Response response;
    private ParkingBoy parkingBoy;

    public PickUpController(Request request, Response response, ParkingBoy parkingBoy) {

        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }

    @Override
    public String process() {
        try {
            String reciptID = request.getCommand();

            Car car = parkingBoy.getCar(new Receipt(UUID.fromString(reciptID)));

            if (car == null) {
                response.send("非法小票，无法取出车，请查证后再输");
            } else {
                response.send("车已取出，您的车牌号是: " + car.getCardNum());
            }

        } catch (IllegalArgumentException ex) {
            response.send("非法Recipt ID，无法取出车，请查证后再输");
        } finally {
            return "forward:main";
        }

    }


}
