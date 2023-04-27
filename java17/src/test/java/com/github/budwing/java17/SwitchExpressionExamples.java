package com.github.budwing.java17;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.time.DayOfWeek;

/**
 * Switch expression was introduced in Java 12 and permanent in Java 14.
 */
@Slf4j
public class SwitchExpressionExamples {
    /**
     * Switch Statement before Java 12
     */
    @Test
    public void exampleForSwitchStatement() {
        switch (DayOfWeek.SUNDAY) {
            case MONDAY:
                System.out.println("The beginning of working days.");
                break;
            case TUESDAY:
            case WEDNESDAY:
            case THURSDAY:
                System.out.println("Boring working days.");
                break;
            case FRIDAY:
                System.out.println("The end of working days.");
                break;
            case SATURDAY:
            case SUNDAY:
                System.out.println("Week end.");
                break;
            default:
                System.out.println("Unknown day.");
        }
    }

    /**
     * Switch expression introduced in Java12
     * "case label ->" can either be used as expression or as statement.
     * the right of "case label ->" can be expression,block or exception
     */
    @Test
    public void exampleForSwitchExpr() {
        // "case label ->" used as expression
        System.out.println(
                switch (DayOfWeek.FRIDAY) {
                    case MONDAY -> "The beginning of working days.";
                    case TUESDAY, WEDNESDAY, THURSDAY -> "Boring working days.";
                    case FRIDAY -> "The end of working days.";
                    case SATURDAY, SUNDAY -> "Week end.";
                    default -> throw new RuntimeException("Unknown day");
                }
        );
        // "case label ->" used as statement
        // differences compared with switch statement is that there's no fall through
        switch (DayOfWeek.WEDNESDAY) {
            case MONDAY -> System.out.println("The beginning of working days.");
            case TUESDAY,WEDNESDAY,THURSDAY->System.out.println("Boring working days.");
            case FRIDAY->System.out.println("The end of working days.");
            case SATURDAY,SUNDAY->System.out.println("Week end.");
            default -> System.out.println("Unknown day.");
        }
    }

    /**
     * yield statement in switch expression was introduced in Java13.
     * In Java12, break is used to raise value for a branch.
     * yield is a new keyword to replace break in this situation.
     * break is still used to break branch, but it can not be used to raise values any more.
     */
    @Test
    public void exampleForSwitchExprWithVariable() {
        String msg = switch (DayOfWeek.MONDAY) {
            case MONDAY -> {
                System.out.println("first branch");
                yield  "The beginning of working days.";
            }
            case TUESDAY, WEDNESDAY, THURSDAY -> {
                System.out.println("second branch");
                yield "Boring working days.";
            }
            case FRIDAY -> {
                System.out.println("third branch");
                yield "The end of working days.";
            }
            case SATURDAY, SUNDAY -> {
                System.out.println("fourth branch");
                yield "Week end.";
            }
            default -> {
                System.out.println("fifth branch");
                yield "Unknown day.";
            }
        };
        System.out.println(msg);
    }
}
