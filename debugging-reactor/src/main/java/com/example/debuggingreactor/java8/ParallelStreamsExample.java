package com.example.debuggingreactor.java8;

import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class ParallelStreamsExample {
    @SneakyThrows
    public static int doubleIt(int number) {
        Thread.sleep(1000);
        System.out.println(number + " : " + Thread.currentThread());
        return number * 2;
    }

    public static void main(String[] args) {
        System.out.println(" ==> " + Runtime.getRuntime().availableProcessors());
        System.out.println("--> " + new ForkJoinPool(100).getParallelism());
        System.out.println("--> " + System.getenv("java.util.concurrent.ForkJoinPool.common.parallelism"));
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,1, 2, 3, 4, 5, 6, 7, 8, 9, 10,1, 2, 3, 4, 5, 6, 7, 8, 9, 10,1, 2, 3, 4, 5, 6, 7, 8, 9, 10,1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
//        System.out.println(numbers.stream()
//                .map(ParallelStreamsExample::doubleIt)
//                .reduce(0, Integer::sum));
        System.out.println(numbers.parallelStream()
                .map(ParallelStreamsExample::doubleIt)
                .reduce(0, Integer::sum));
    }
}
