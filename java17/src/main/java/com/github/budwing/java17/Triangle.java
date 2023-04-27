package com.github.budwing.java17;

/**
 * Triangle record with explicit constructor
 * @param a
 * @param b
 * @param c
 */
public record Triangle(double a, double b, double c) {
    /**
     * Repeating the record class's components in the signature of the canonical constructor can be tiresome and error-prone.
     * To avoid this, you can declare a compact constructor whose signature is implicit (derived from the components automatically).
     */
    public Triangle {
        if (a+b<c || a+c<b || b+c<a) {
            throw new IllegalStateException("incorrect length of the edges");
        }
    }
}
