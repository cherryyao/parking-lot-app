package com.thoughtworks.tdd.parkinglot.shell.io;

import com.thoughtworks.tdd.parklinglot.shell.io.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


class ResponseTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {

        System.setOut(new PrintStream(outContent));
    }

    @Test
    void should_print_msg_on_console() {
        //given
        String printMsg = "print me to CLI";
        Response response = new Response();

        //when
        response.send(printMsg);

        //then
        assertThat(outContent.toString(), is(printMsg + "\n"));

    }
}