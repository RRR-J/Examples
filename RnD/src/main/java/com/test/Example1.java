package com.test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Example1 {

    public static void main(String[] args) {
        testStream();
        testStreamWithMethodReference();
        testStreamWithPredicate();
    }

    private static boolean isEqualToA(String s) {
        return s.equals("A");
    }

    private boolean isEqualToB(String s) {
        return s.equals("B");
    }

    private static void testStream() {
        System.out.println("Example1.testStream");
        List<String> list = Arrays.asList("a", "b", "c", "a");

        list.stream()
                .filter(s -> s.equals("a"))
                .forEach(x -> System.out.println(x));
    }

    private static void testStreamWithMethodReference() {
        System.out.println("Example1.testStreamWithMethodReference");
        List<String> list = Arrays.asList("a", "B", "c", "A");

        // static reference
        list.stream()
                .filter(Example1::isEqualToA)
                .forEach(x -> {
                    System.out.println("Static reference");
                    System.out.println(x);
                });

        // object reference
        Example1 example1Object = new Example1();
        list.stream()
                .filter(example1Object::isEqualToB)
                .forEach(x -> {
                    System.out.println("Object reference");
                    System.out.println(x);
                });

    }

    private static void testStreamWithPredicate() {
        System.out.println("Example1.testStreamWithPredicate");
        List<String> list = Arrays.asList("a", "b", "c", "a");
        Predicate<String> stringPredicate = s -> s.equals("c");

        list.stream()
                .filter(stringPredicate)
                .forEach(x -> System.out.println(x));
    }



}
