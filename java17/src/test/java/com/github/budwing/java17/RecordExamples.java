package com.github.budwing.java17;

import com.github.budwing.java17.record.Circle;
import com.github.budwing.java17.record.Rectangle;
import com.github.budwing.java17.record.Triangle;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Record Class was introduced in Java 14 and permanent in Java 16
 */
@Slf4j
public class RecordExamples {
    /**
     * A record class declares the following members automatically:
     * -- Each component has a private final field and a public accessor method with the same name and type.
     * -- A canonical constructor whose signature is the same as the header
     * -- Implementations of the equals and hashCode methods, which specify that two record classes are equal if they are of the same type and contain equal component values.
     * -- An implementation of the toString method that includes the string representation of all the record class's components, with their names.
     */
    @Test
    public void rectangle() {
        Rectangle rectangle1 = new Rectangle(12, 13);
        log.info("width is {}, height is {}.", rectangle1.width(), rectangle1.height());
        Rectangle rectangle2 = new Rectangle(12, 13);
        log.info("Rectangle2: {}", rectangle2);
        Assert.assertEquals(rectangle2, rectangle1);
    }

    /**
     * Record with explicit declaration of constructor.
     */
    @Test
    public void triangle() {
        Assert.expectThrows(IllegalStateException.class, () -> {
            log.info("This will throw an exception because the customized constructor defines the logic.");
            new Triangle(1, 2, 9);
        });
    }

    /**
     * Record can implement interfaces and have its own static members
     */
    @Test
    public void circle() {
        Circle c = new Circle(2);
        log.info("The area: {}, the perimeter: {}", String.format("%.2f", c.area()), c.perimeter());
    }

    /**
     * TODO: make Shape a sealed Record
     */
    @Test
    public void sealedShape() {
        Assert.fail("make Shape a sealed Record");
    }
}
