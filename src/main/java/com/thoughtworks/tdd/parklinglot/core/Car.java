package com.thoughtworks.tdd.parklinglot.core;

public class Car {
    private String cardNum;

    public Car(String cardNum) {

        this.cardNum = cardNum;
    }

    public Car(){
        this.cardNum = "keeped";
    }

    public String getCardNum() {
        return cardNum;
    }
}
