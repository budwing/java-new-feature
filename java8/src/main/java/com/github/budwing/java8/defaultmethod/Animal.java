package com.github.budwing.java8.defaultmethod;

public interface Animal {
    static boolean canFlyWithWings() {
        return false;
    }
    boolean hasWings();
    default void fly() {
        if (hasWings()) {
            System.out.println("I am flying with my wings.");
        } else {
            System.out.println("How can I fly without wings?");
        }
    }
}
