package com.thoughtworks.tdd.parkinglot.shell.controller;

import com.thoughtworks.tdd.parklinglot.core.ParkingBoy;
import com.thoughtworks.tdd.parklinglot.shell.controller.GotoParkingController;
import com.thoughtworks.tdd.parklinglot.shell.io.Request;
import com.thoughtworks.tdd.parklinglot.shell.io.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GotoParkingControllerTest {


    private Request request;
    private Response response;
    private GotoParkingController controller;
    private ParkingBoy parkingBoy;

    @BeforeEach
    void setUp() {
        request = mock(Request.class);
        response = mock(Response.class);
        parkingBoy = mock(ParkingBoy.class);
        controller = new GotoParkingController(request, response, parkingBoy);
    }

    @Test
    public void should_send_correct_page_info_when_parking_lot_not_full() {

        //given
        when(parkingBoy.isFull()).thenReturn(false);

        //when
        String forwardPath = controller.process();

        //then
        verify(response).send("请输入车牌号:");
        assertThat(forwardPath, not(containsString("forward:")));
    }

    @Test
    public void should_send_wrong_info_and_forward_main_page_when_parking_lot_is_full() {
        //given
        when(parkingBoy.isFull()).thenReturn(true);

        //when
        String forwardPath = controller.process();

        //then
        verify(response).send("车库已满，无法停车！");
        assertThat(forwardPath, containsString("forward:"));
    }
}