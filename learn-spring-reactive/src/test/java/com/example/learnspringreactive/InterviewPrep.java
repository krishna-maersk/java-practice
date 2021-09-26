package com.example.learnspringreactive;


import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class InterviewPrep {

    @Test
    void test1(){
        List<String> list = Arrays.asList("Krishna", "Reddy","Abc", " ", "",null );
        list.stream().filter(Objects::nonNull).sorted().map(String::toUpperCase).forEach(System.out::println);
    }

    @Test
    void test2(){
        List<Person> list = List.of(new Person("Krishna",28), new Person("Krishna2",40) );
        list.stream().filter(Objects::nonNull).sorted().forEach(System.out::println);
    }


    @Test
    void optionalTest(){
        List<String> list = Arrays.asList("Krishna", "Reddy","Abc", " ", "",null );
//        list.stream().map(Optional::ofNullable)

        Optional<String> sample = Optional.ofNullable(null);
        Optional<String> sample1 = Optional.ofNullable("Krishna");
         String s = sample.map(String::toUpperCase).orElse("NA");
         String s2= sample1.map(String::toUpperCase).orElse("NA");
        System.out.println(s);
        System.out.println(2);
    }
}


class Person implements Comparable{
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Person){
            return this.age- ((Person) o).getAge();
        }
        return 0;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}