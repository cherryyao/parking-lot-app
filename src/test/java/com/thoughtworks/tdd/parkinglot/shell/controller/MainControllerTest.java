package com.thoughtworks.tdd.parkinglot.shell.controller;

import com.thoughtworks.tdd.parklinglot.core.ParkingBoy;
import com.thoughtworks.tdd.parklinglot.shell.controller.MainController;
import com.thoughtworks.tdd.parklinglot.shell.io.Request;
import com.thoughtworks.tdd.parklinglot.shell.io.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class MainControllerTest {


    private Request request;
    private Response response;
    private MainController controller;
    private ParkingBoy parkingBoy;

    @BeforeEach
    void setUp() {
        request = mock(Request.class);
        response = mock(Response.class);
        parkingBoy = mock(ParkingBoy.class);
        controller = new MainController(request, response, parkingBoy);
    }

        @Test
        public void should_print_main_page(){
            //given
            //when
            controller.process();

            //then
            verify(response).send("1. 停车\n" +
                    "2. 取车 \n" +
                    "请输入您要进行的操作：");
        }
}