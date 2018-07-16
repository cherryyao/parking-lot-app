package com.thoughtworks.tdd.parkinglot.shell.controller;

import com.thoughtworks.tdd.parklinglot.core.Car;
import com.thoughtworks.tdd.parklinglot.core.ParkingBoy;
import com.thoughtworks.tdd.parklinglot.core.Receipt;
import com.thoughtworks.tdd.parklinglot.shell.controller.ParkingController;
import com.thoughtworks.tdd.parklinglot.shell.io.Request;
import com.thoughtworks.tdd.parklinglot.shell.io.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ParkingControllerTest {


    private Request request;
    private Response response;
    private ParkingController controller;
    private ParkingBoy parkingBoy;

    @BeforeEach
    void setUp() {
        request = mock(Request.class);
        response = mock(Response.class);
        parkingBoy = mock(ParkingBoy.class);
        controller = new ParkingController(request, response, parkingBoy);
    }

    @Test
    public void should_park_successfully(){
        //given
        Receipt ticket = new Receipt();
        when(parkingBoy.park(any(Car.class))).thenReturn(ticket);

        //when
        String forwardPath = controller.process();

        //then
        verify(response).send("停车成功，您的小票是：\n" +
                ticket.toString());
        assertThat(forwardPath, is("forward:main"));

    }
}