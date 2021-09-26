package com.example.debuggingreactor.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
//
//public class StringJoin {
//    public static void main(String[] args) {
//        List<String> names = Arrays.asList("Krishna","Ram", "Arun");
//        // print names in upper case, comma separated
//        for(String name: names){
//            System.out.print(name.toUpperCase() + ","); // will give comma at end
//        }
//    }
//}

public class StringJoin {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Krishna","Ram", "Arun");
        System.out.println(names.stream()
                .map(String:: toUpperCase)
                .collect(Collectors.joining(",")));

    }
}
