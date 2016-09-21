package com.test;

/**
 * Created by g01027567 on 23/08/2016.
 */
public class Lambada {

    public static void main(String[] args) {
        Decider lessThan100 = i -> i < 100;
        Decider greaterThan100 = i ->  i > 100;

        Decider anyGreaterThan100 = new Decider() {
            @Override
            public boolean decide(int input) {
                return input > 100;
            }
        };

        testAllow(lessThan100, 1);
        testAllow(lessThan100, 111);
        testAllow(greaterThan100, 111);
        testAllow(greaterThan100, 11);
    }

    private static void testAllow(Decider allow, int x) {
        if (allow.decide(x)){
            System.out.println("Allowed-" + x);
        } else {
            System.out.println("Not Allowed-" + x);
        }
    }
}
