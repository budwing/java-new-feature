package com.github.budwing.java7;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class NonReifiableType {
    /**
     * A heap pollution situation occurs when the List object l, whose static type is List<Number>,
     * is assigned to another List object, ls, that has a different static type, List<String>.
     * However, the compiler still allows this assignment.
     * It must allow this assignment to preserve backwards compatibility with versions of Java SE that do not support generics.
     * Because of type erasure, List<Number> and List<String> both become List.
     * Consequently, the compiler allows the assignment of the object l, which has a raw type of List, to the object ls.
     *
     */
    public static void heapPollutionClassCast() {
        List l = new ArrayList<Number>();
        List<String> ls = l;       // unchecked warning
        l.add(0, new Integer(42)); // another unchecked warning
        String s = ls.get(0);      // ClassCastException is thrown
        log.info("ls.get(0) returns {}", s);
    }

    /**
     * Furthermore, a heap pollution situation occurs when the l.add method is called.
     * The static type second formal parameter of the add method is String,
     * but this method is called with an actual parameter of a different type, Integer.
     * However, the compiler still allows this method call. Because of type erasure,
     * the type of the second formal parameter of the add method (which is defined as List<E>.add(int,E)) becomes Object.
     * Consequently, the compiler allows this method call because, after type erasure, the l.add method can add any object of type Object,
     * including an object of Integer type, which is a subtype of Object.
     */
    public static void heapPollutionUncheckedWarning() {
        List l = new ArrayList<Number>();
        List<String> ls = l;       // unchecked warning
        l.add(0, new Integer(42)); // another unchecked warning
        ls.add("test");
        for (Object obj:l) {
            log.info("Object in list: {}", obj);
        }
    }

    /**
     * The Java SE 7 compiler generates the following warning for the definition of the method:
     * warning: [varargs] Possible heap pollution from parameterized vararg type T
     * Uses @SuppressWarnings({"unchecked", "varargs"}) or @SafeVarargs to suppress the warning
     *
     * Notes: @SafeVarargs can only annotate static & final instance method
     *
     * @param listArg
     * @param elements
     * @param <T>
     */
    public static <T> void addToListUncheckedWarning(List<T> listArg, T... elements) {
        Object[] newElements = elements;
        for (Object x : newElements) {
            listArg.add((T) x);
        }
    }

    /**
     * Explain why this warning is needed and when heap pollution happens
     * @param l
     */
    // @SuppressWarnings({"unchecked", "varargs"})
    public static void faultyMethod(List<String>... l) {
        Object[] objectArray = l;  // Valid
        objectArray[0] = Arrays.asList(new Integer(42));
        String s = l[0].get(0);    // ClassCastException thrown here
    }
}