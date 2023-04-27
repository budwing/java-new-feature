package com.github.budwing.java17;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Record Class was introduced in Java 14 and permanent in Java 16
 */
public class RecordExamples {
    /**
     * A record class declares the following members automatically:
     * -- Each component has a private final field and a public accessor method with the same name and type.
     * -- A canonical constructor whose signature is the same as the header
     * -- Implementations of the equals and hashCode methods, which specify that two record classes are equal if they are of the same type and contain equal component values.
     * -- An implementation of the toString method that includes the string representation of all the record class's components, with their names.
     */
    @Test
    public void testRectangle() {
        Rectangle rectangle1 = new Rectangle(12,13);
        System.out.println(rectangle1);
        System.out.printf("width is %.2f, height is %.2f.\n", rectangle1.width(), rectangle1.height());
        Rectangle rectangle2 = new Rectangle(12, 13);

        Assert.assertEquals(rectangle2, rectangle1);
    }

    /**
     * Record with explicit declaration of constructor.
     */
    @Test
    public void testTriangle() {
        Assert.expectThrows(IllegalStateException.class, ()-> {
            System.out.println("This will throw an exception because the customized constructor defines the logic.");
            new Triangle(1,2,9);
        });
    }
    /**
     * Record can implement interfaces and have its own static members
     *
     */
    @Test
    public void testCircle() {
        Circle c = new Circle(2);
        System.out.printf("The area: %.2f, the perimeter: %.2f.\n", c.area(),c.perimeter());
    }
}
