package com.github.budwing.java;

/**
 * Record can have static fields, static initializers, and static methods.
 * At the same time, it can also implement interface.
 * @param radius
 */
public record Circle(double radius) implements Shape {
    static double PI = Math.PI;

    @Override
    public double area() {
        return PI * radius * radius;
    }

    @Override
    public double perimeter() {
        return 0;
    }
}
