package com.example.debuggingreactor.java8;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

public class SortingExample {

    public static List<Person> createPeople() {
        return Arrays.asList(
                new Person("Sara", Gender.FEMALE, 20),
                new Person("Sara", Gender.FEMALE, 22),
                new Person("Bob", Gender.MALE, 20),
                new Person("Paula", Gender.FEMALE, 32),
                new Person("Paul", Gender.MALE, 32),
                new Person("Jack", Gender.MALE, 2),
                new Person("Jack", Gender.MALE, 72),
                new Person("Jill", Gender.FEMALE, 12)
        );
    }

    public static void printSorted(List<Person> people, Comparator<Person> comparator) {
        people.stream()
                .sorted(comparator)
                .forEach(System.out::println);
    }

    public static void main(String[] args) {

        List<Person> people = createPeople();
//        Collections.sort(people);
//        printSorted(people, Comparator.comparing(Person::getName));
//        printSorted(people, Comparator.comparing(Person::getAge));

//        printSorted(people, Comparator.comparing(Person::getAge).thenComparing(Person::getName));
//        printSorted(people, Comparator.comparing(Person::getAge).thenComparing(Person::getName).reversed());
//


//        Groping

//        System.out.println(people.stream()
//                .collect(groupingBy(Person::getAge)));

        System.out.println(people.stream()
                .collect(groupingBy(Person::getAge, mapping(Person::getName, toList()))));


    }


}

@AllArgsConstructor
@Data
class Person {
    String name;
    Gender gender;
    int age;
}

enum Gender {
    MALE, FEMALE
}
