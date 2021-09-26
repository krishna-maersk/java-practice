package com.example.debuggingreactor.java8;

import java.util.function.Predicate;

public class predicateCombineExample {

    public static void print(int number, Predicate<Integer> predicate, String msg){
        System.out.println(number + " " + msg + ":" + predicate.test(number));
    }

    public static void main(String[] args) {
        Predicate<Integer> isEven = e -> e%2 == 0;
        Predicate<Integer> isGT4 = e -> e>4;

        print(5,isEven,"is even?");
        print(10,isEven,"is even?");

        print(5,isGT4,"is > 4?");
        print(10,isGT4,"is > 4?");

        print(5, isGT4.and(isEven), "is > 4 and is even?");
        print(5, isGT4.or(isEven), "is > 4 or is even?");

    }
}
