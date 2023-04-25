package com.github.budwing.java;

public class MyClass<X> {
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
