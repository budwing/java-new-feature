package com.github.budwing.java;

import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

@Slf4j
public class StringInSwitchExamples {
    /**
     * The switch statement compares the String object in its expression with the expressions associated with each case label as if it were using the String.equals method;
     * consequently, the comparison of String objects in switch statements is case sensitive.
     * The Java compiler generates generally more efficient bytecode from switch statements that use String objects than from chained if-then-else statements.
     * @param dayOfWeekArg
     * @return
     */
    public String getTypeOfDayWithSwitchStatement(String dayOfWeekArg) {
        String typeOfDay;
        switch (dayOfWeekArg) {
            case "Monday":
                typeOfDay = "Start of work week";
                break;
            case "Tuesday":
            case "Wednesday":
            case "Thursday":
                typeOfDay = "Midweek";
                break;
            case "Friday":
                typeOfDay = "End of work week";
                break;
            case "Saturday":
            case "Sunday":
                typeOfDay = "Weekend";
                break;
            default:
                throw new IllegalArgumentException("Invalid day of the week: " + dayOfWeekArg);
        }
        return typeOfDay;
    }

    @Test
    public void test() {
        log.info("Input: Monday, Expected Output: Start of work week.");
        String type = getTypeOfDayWithSwitchStatement("Monday");
        Assert.assertEquals(type, "Start of work week");

        log.info("Input: Tuesday/Wednesday/Thursday, Expected Output: Midweek.");
        type = getTypeOfDayWithSwitchStatement("Tuesday");
        Assert.assertEquals(type, "Midweek");
        type = getTypeOfDayWithSwitchStatement("Wednesday");
        Assert.assertEquals(type, "Midweek");
        type = getTypeOfDayWithSwitchStatement("Thursday");
        Assert.assertEquals(type, "Midweek");

        log.info("Input: Friday, Expected Output: End of work week.");
        type = getTypeOfDayWithSwitchStatement("Friday");
        Assert.assertEquals(type, "End of work week");

        log.info("Input: Saturday/Sunday, Expected Output: Weekend.");
        type = getTypeOfDayWithSwitchStatement("Saturday");
        Assert.assertEquals(type, "Weekend");
        type = getTypeOfDayWithSwitchStatement("Sunday");
        Assert.assertEquals(type, "Weekend");

        log.info("Input: WrongDay, Expected Output: IllegalArgumentException.");
        Assert.assertThrows(new Assert.ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                getTypeOfDayWithSwitchStatement("WrongDay");
            }
        });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //// try yourselves
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * can long variable be used in switch?
     */
    @Test
    public void typesInSwitch() {
        int target = 10;
        switch (target) {
            case 10:
                log.info("it's ten");
        }
    }
}
