package com.github.budwing.java;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class TypeInference {
    /**
     * You can replace the type arguments required to invoke the constructor of a generic class with an empty set of type parameters (<>)
     * as long as the compiler can infer the type arguments from the context. This pair of angle brackets is informally called the diamond.
     */
    public static void typesInConstructor() {
        Map<String, List<String>> myMap1 = new HashMap<String, List<String>>();
        // Unchecked assignment warning
        Map<String, List<String>> myMap2 = new HashMap();
    }

    /**
     * the diamond often works in method calls; however, it is suggested that you use the diamond primarily for variable declarations.
     */
    public static void usedInDeclaration() {
        List<String> list = new ArrayList<>();


        // doesn't work, should declare first
        // list.addAll(new ArrayList<>());

        List<? extends String> list2 = new ArrayList<>();
        list.addAll(list2);
    }
}

class MyClass<X> {
    <T> MyClass(T t) {
        System.out.println(t);
        System.out.printf("Generic type is %s, type is visible within constructor.\n",t.getClass());
    }

    void classGenericType(X x) {
        System.out.printf("Parameter value is %s, Class type is %s\n", x, x.getClass());
    }

    <T> void methodGenericType(T t) {
        System.out.println(t);
        System.out.printf("Generic type is %s, type is visible within print2.\n",t.getClass());
    }
}