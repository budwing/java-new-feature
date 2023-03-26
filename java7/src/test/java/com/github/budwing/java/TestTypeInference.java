package com.github.budwing.java;

import org.testng.annotations.Test;

public class TestTypeInference {
    @Test
    public void testMyClass() {
        MyClass<Integer> my_first = new MyClass<>("test for constructor generic type");
        my_first.methodGenericType("test for method generic type");
        my_first.classGenericType(100);

        MyClass<String> my_second = new MyClass<String>(200);
        my_second.methodGenericType(300);
        my_second.classGenericType("test for class generic type");
    }
}
