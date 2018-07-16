package com.thoughtworks.tdd.parkinglot.shell;

import com.thoughtworks.tdd.parklinglot.shell.Router;
import com.thoughtworks.tdd.parklinglot.shell.controller.*;
import com.thoughtworks.tdd.parklinglot.shell.io.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.mockito.Mockito.*;


public class RouterTest {


    MainController mainController;

    GotoParkingController gotoParkingController;

    GoToPickUpController goToPickUpController;

    ParkingController parkingController;

    PickUpController pickUpController;

    @BeforeEach
    void setUp() {
        mainController = mock(MainController.class);
        gotoParkingController = mock(GotoParkingController.class);
        goToPickUpController = mock(GoToPickUpController.class);
        parkingController = mock(ParkingController.class);
        pickUpController = mock(PickUpController.class);

    }


    @Test
    void should_register_route_and_lunch_main_page_when_init_main_to_root_path() {
        //given
        Router router = new Router("main");
        router.registerRoute("main", mainController);

        //when
        router.launch();

        //then
        verify(mainController, times(1)).process();
    }

    @Test
    void should_process_main_slash_1_route_when_process_1_command_after_router_launch() {
        //given
        Router router = new Router("main");
        router.registerRoute("main", mainController);
        router.registerRoute("main/1", gotoParkingController);
        router.launch();

        //when
        Request request = new Request("1");
        router.processRequest(request);

        //then
        verify(gotoParkingController, times(1)).process();
    }

    @Test
    void should_process_forward_main_route_when_process_goto_park_controller_return_forward_main_after_router_launch() {
        //given
        Router router = new Router("main");
        router.registerRoute("main", mainController);
        router.registerRoute("main/1", gotoParkingController);
        router.launch();
        when(gotoParkingController.process()).thenReturn("forward:main");

        //when
        Request request = new Request("1");
        router.processRequest(request);

        //then
        verify(gotoParkingController, times(1)).process();
        verify(mainController, times(2)).process();
    }

    @Test
    void should_process_parking_controller_when_process_car_num_and_current_route_is_main_slash_1() {
        //given
        Router router = new Router("main");
        router.registerRoute("main", mainController);
        router.registerRoute("main/1", gotoParkingController);
        router.registerRoute("main/1/*", parkingController);
        router.launch();
        Request request = new Request("1");
        router.processRequest(request);

        //when
        router.processRequest(new Request("abc123"));

        //then
        verify(parkingController, times(1)).process();
    }
}
