package com.github.budwing.java.defaultmethod;

public interface Bird {
    static boolean canRun() {
        return true;
    }
    void fly();
    default void run() {
        if (canRun()) {
            System.out.println("I can run.");
        }
    }
}
