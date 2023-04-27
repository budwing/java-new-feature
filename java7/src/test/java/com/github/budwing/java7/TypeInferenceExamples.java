package com.github.budwing.java7;

import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

@Slf4j
public class TypeInferenceExamples {
    /**
     * You can replace the type arguments required to invoke the constructor of a generic class with an empty set of type parameters (<>)
     * as long as the compiler can infer the type arguments from the context. This pair of angle brackets is informally called the diamond.
     */
    @Test
    public void typesInConstructor() {
        Map<String, List<String>> myMap1 = new HashMap<String, List<String>>();
        Assert.assertEquals(myMap1.size(), 0);
    }

    /**
     * the diamond often works in method calls; however, it is suggested that you use the diamond primarily for variable declarations.
     */
    @Test
    public void usedInDeclaration() {
        List<String> list = new ArrayList<>();

        List<? extends String> list2 = new ArrayList<>();
        list.addAll(list2);
    }

    @Test
    public void testMyClass() {
        MyClass<Integer> my_first = new MyClass<>("test for constructor generic type");
        my_first.methodGenericType("test for method generic type");
        my_first.classGenericType(100);

        MyClass<String> my_second = new MyClass<String>(200);
        my_second.methodGenericType(300);
        my_second.classGenericType("test for class generic type");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //// try yourselves
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * In Java 7, you can use diamond in method parameters directly, they must be declared first.
     * In Java 8, it was enhanced, and can be used.
     *
     * TODO: Try to change language level of this module to see the difference.
     */
    @Test
    public void tryDiamondParameter() {
        List<String> list = new ArrayList<>();
        // doesn't work, should declare first
        // list.addAll(new ArrayList<>());
        log.info("list size:{}", list.size());
    }

    /**
     * Assign a raw type to a generic type will introduce an unchecked warning.
     *
     * TODO: figure out the reason of unchecked warning
     */
    @Test
    public void whyUncheckedWarning() {
        // Unchecked assignment warning
        Map<String, List<String>> myMap = new HashMap();
        Assert.fail("figure out the reason of unchecked warning");
    }

    /**
     * Diamond operator can't be used with anonymous inner class, however it's improved in Java9.
     * TODO: try to switch the language level to see diamond with inner class.
     */
    @Test
    public void tryDiamondWithInnerClass() throws Exception {
        Callable<String> c = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "it's just a test for diamond operator with anonymous inner class.";
            }
        };
        log.info(c.call());
        Assert.fail("try to switch the language level to see diamond with inner class");
    }
}