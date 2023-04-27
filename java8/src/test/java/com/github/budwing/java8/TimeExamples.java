package com.github.budwing.java8;

import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

/**
 * Java 8 introduces new package java.time
 */
@Slf4j
public class TimeExamples {
    /**
     * An instantaneous point on the time-line.
     *
     * It has a long representing epoch-seconds and an int representing nanosecond-of-second.
     */
    @Test
    public void instance() {
        Instant instant = Instant.now();
        log.info("Epoch second:{}, Nano time: {}", instant.getEpochSecond(), instant.getNano());
        long sys = System.currentTimeMillis();
        long ins = Instant.now().toEpochMilli();
        log.info("the difference: {}", ins - sys);
    }

    /**
     * LocalDate is an immutable date-time object that represents a date, often viewed as year-month-day.
     */
    @Test
    public void localDate() {
        log.info("Local date: {}", LocalDate.now());
    }

    /**
     * A time without time-zone in the ISO-8601 calendar system, such as 10:15:30, viewed as hour-minute-second.
     */
    @Test
    public void localTime() {
        log.info("Local time: {}", LocalTime.now());
    }

    /**
     * A date-time without a time-zone in the ISO-8601 calendar system, such as 2007-12-03T10:15:30, often viewed as year-month-day-hour-minute-second.
     */
    @Test
    public void localDateTime() {
        log.info("Local date time: {}", LocalDateTime.now());
    }

    /**
     * A date-based amount of time in the ISO-8601 calendar system, such as '2 years, 3 months and 4 days'.
     * Period modules time in terms of years, months and days
     */
    @Test
    public void period() {
        LocalDate olympicDate = LocalDate.of(2008,8, 8);
        LocalDate now = LocalDate.now();
        Period period = Period.between(olympicDate,now);
        log.info("Beijing Olympic was {} years {} months and {} days ago.", period.getYears(), period.getMonths(), period.getDays());
    }

    /**
     * A time-based amount of time, such as '34.5 seconds'.
     * Duration modules time in terms of seconds and nanoseconds.
     */
    @Test
    public void duration() {
        LocalDateTime olympicDate = LocalDateTime.of(2008,8,8,8,8,8);
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(olympicDate, now);
        log.info("Beijing Olympic was {} days ago.", duration.get(ChronoUnit.SECONDS)/3600/24);
    }

    /**
     * TODO: calculate the day after 10,000 days.
     */
    @Test
    public void dayAfter() {
        Assert.fail("calculate the day after 10,000 days.");
    }
}
