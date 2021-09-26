package com.example.debuggingreactor.java8;


interface Fly{
    default void takeOff(){
        System.out.println("take off");
    }
   default void turn(){
        System.out.println("turn ");
    }
    default void land(){
        System.out.println("land");
    }
    default void cruise(){
        System.out.println("Fly::cruise");
    }
}
interface FastFly extends Fly{
    default void takeOff(){
        System.out.println("FastFly: take off");
    }
}
class Vehicle{
    public void land(){
        System.out.println("Vehicle:: land");
    }
}
interface Sail{
    default void cruise(){
        System.out.println("Sail::cruise");
    }
}
class SeaPlane extends Vehicle implements FastFly, Sail{

    @Override
    public void cruise() {
        FastFly.super.cruise();
    }
}
// Four rules of default methods

// 1. you get  what is in the base interface
// 2. you may override a default method
// 3. if a method is there class hierarchy, it takes precedence
// 4. if there is no method on any of the classes in the hierarchy, but two of interfaces that has default method

public class DefaultMethodExample {
    void use(){
        SeaPlane seaPlane = new SeaPlane();
        seaPlane.takeOff();
        seaPlane.turn();
        seaPlane.land();
        seaPlane.cruise();
    }

    public static void main(String[] args) {
        new DefaultMethodExample().use();
    }
}
