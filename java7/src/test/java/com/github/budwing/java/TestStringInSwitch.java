package com.github.budwing.java;

import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

@Slf4j
public class TestStringInSwitch {
    @Test
    public void test() {
        log.info("Input: Monday, Expected Output: Start of work week.");
        String type = StringInSwitch.getTypeOfDayWithSwitchStatement("Monday");
        Assert.assertEquals(type, "Start of work week");

        log.info("Input: Tuesday/Wednesday/Thursday, Expected Output: Midweek.");
        type = StringInSwitch.getTypeOfDayWithSwitchStatement("Tuesday");
        Assert.assertEquals(type, "Midweek");
        type = StringInSwitch.getTypeOfDayWithSwitchStatement("Wednesday");
        Assert.assertEquals(type, "Midweek");
        type = StringInSwitch.getTypeOfDayWithSwitchStatement("Thursday");
        Assert.assertEquals(type, "Midweek");

        log.info("Input: Friday, Expected Output: End of work week.");
        type = StringInSwitch.getTypeOfDayWithSwitchStatement("Friday");
        Assert.assertEquals(type, "End of work week");

        log.info("Input: Saturday/Sunday, Expected Output: Weekend.");
        type = StringInSwitch.getTypeOfDayWithSwitchStatement("Saturday");
        Assert.assertEquals(type, "Weekend");
        type = StringInSwitch.getTypeOfDayWithSwitchStatement("Sunday");
        Assert.assertEquals(type, "Weekend");

        log.info("Input: WrongDay, Expected Output: IllegalArgumentException.");
        Assert.assertThrows(new Assert.ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                StringInSwitch.getTypeOfDayWithSwitchStatement("WrongDay");
            }
        });
    }
}
