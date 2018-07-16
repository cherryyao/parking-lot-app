package com.thoughtworks.tdd.parklinglot;

import com.thoughtworks.tdd.parklinglot.core.ParkingBoy;
import com.thoughtworks.tdd.parklinglot.core.ParkingLot;
import com.thoughtworks.tdd.parklinglot.shell.controller.*;
import com.thoughtworks.tdd.parklinglot.shell.Router;
import com.thoughtworks.tdd.parklinglot.shell.io.Request;
import com.thoughtworks.tdd.parklinglot.shell.io.Response;

import java.util.Scanner;

public class Main {

    public static Scanner cliInput = new Scanner(System.in);

    public static void main(String[] args) {

        String initRootPath = "main";
        Request request = new Request();
        Router router = initRouter(initRootPath, request);
        router.launch();

        try {
            while (true) {
                String command = cliInput.nextLine();
                request.setCommand(command);
                router.processRequest(request);
            }
        } catch (Exception ex) {
            System.out.println("\n App Exist, cause from route not exist!");
        } finally {
            cliInput.close();
        }
    }

    private static Router initRouter(String currentPage, Request request) {
        ParkingBoy boy = new ParkingBoy(new ParkingLot(2), new ParkingLot(2));
        Response response = new Response();

        Router router = new Router(currentPage);
        router.registerRoute("main", new MainController(request, response, boy));
        router.registerRoute("main/1", new GotoParkingController(request, response, boy));
        router.registerRoute("main/2", new GoToPickUpController(request, response, boy));
        router.registerRoute("main/1/*", new ParkingController(request, response, boy));
        router.registerRoute("main/2/*", new PickUpController(request, response, boy));

        return router;
    }

}
