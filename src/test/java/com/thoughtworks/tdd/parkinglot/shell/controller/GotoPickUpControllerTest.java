package com.thoughtworks.tdd.parkinglot.shell.controller;

import com.thoughtworks.tdd.parklinglot.core.ParkingBoy;
import com.thoughtworks.tdd.parklinglot.shell.controller.GoToPickUpController;
import com.thoughtworks.tdd.parklinglot.shell.io.Request;
import com.thoughtworks.tdd.parklinglot.shell.io.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.containsString;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class GotoPickUpControllerTest {


    private Request request;
    private Response response;
    private GoToPickUpController controller;
    private ParkingBoy parkingBoy;

    @BeforeEach
    void setUp() {
        request = mock(Request.class);
        response = mock(Response.class);
        parkingBoy = mock(ParkingBoy.class);
        controller = new GoToPickUpController(request, response, parkingBoy);
    }

    @Test
    public void should_print_unpark_page() {
        //given
        //when
        String forwardPath = controller.process();

        //then
        verify(response).send("请输入小票编号：");
        assertThat(forwardPath, not(containsString("forward:")));
    }

}