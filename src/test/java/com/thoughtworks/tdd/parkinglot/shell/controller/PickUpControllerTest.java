package com.thoughtworks.tdd.parkinglot.shell.controller;

import com.thoughtworks.tdd.parklinglot.core.Car;
import com.thoughtworks.tdd.parklinglot.core.ParkingBoy;
import com.thoughtworks.tdd.parklinglot.core.Receipt;
import com.thoughtworks.tdd.parklinglot.shell.controller.PickUpController;
import com.thoughtworks.tdd.parklinglot.shell.io.Request;
import com.thoughtworks.tdd.parklinglot.shell.io.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PickUpControllerTest {


    private Request request;
    private Response response;
    private PickUpController controller;
    private ParkingBoy parkingBoy;

    @BeforeEach
    void setUp() {
        request = mock(Request.class);
        response = mock(Response.class);
        parkingBoy = mock(ParkingBoy.class);
        controller = new PickUpController(request, response, parkingBoy);
    }

    @Test
    public void should_print_pick_up_success_info_when_recipt_id_is_exist_in_parking_lot() {

        //given
        Receipt receipt = new Receipt();
        when(request.getCommand()).thenReturn(receipt.toString());
        Car car = new Car("abc123");
        when(parkingBoy.getCar(receipt)).thenReturn(car);

        //when
        String forwardPath = controller.process();

        //then
        verify(response).send("车已取出，您的车牌号是: " + car.getCardNum());
        assertThat(forwardPath, is("forward:main"));
    }


    @Test
    public void should_sent_wrong_msg_when_UUID_is_incorrect_format() {

        //given
        when(request.getCommand()).thenReturn("wrong uuid");

        //when
        String forwardPath = controller.process();

        //then
        verify(response).send("非法Recipt ID，无法取出车，请查证后再输");
        assertThat(forwardPath, is("forward:main"));
    }

    @Test
    public void should_sent_warong_msg_when_recipt_ID_is_not_exist_in_parking_lot() {

        //given
        when(request.getCommand()).thenReturn(UUID.randomUUID().toString());
        when(parkingBoy.getCar(any())).thenReturn(null);

        //when
        String forwardPath = controller.process();

        //then
        verify(response).send("非法小票，无法取出车，请查证后再输");
        assertThat(forwardPath, is("forward:main"));
    }
}
