package com.github.budwing.java;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * Pattern Matching was introduced in Java 14
 * 1. instanceof was permanent in Java 16
 * 2. switch was introduced in Java 17, fourth preview in Java 20
 */
@Slf4j
public class PatternMatchingExamples {
    @Test
    public void exampleForInstanceOf() {
        Exception e = new SQLException("reason", "unknown");
        // traditional way
        if (e instanceof IOException) {
            // It's easy to introduce error
            ParseException parseException = (ParseException) e;
            log.info("Traditional parse exception offset: {}", parseException.getErrorOffset());
        } else if (e instanceof SQLException) {
            SQLException sqlException = (SQLException) e;
            log.info("Traditional sql exception state: {}", sqlException.getSQLState());
        }
        // pattern matching
        if (e instanceof ParseException exception) {
            log.info("Pattern matching parse exception offset: {}", exception.getErrorOffset());
        } else if (e instanceof SQLException exception) {
            log.info("Pattern matching sql exception state: {}", exception.getSQLState());
        }
    }

    @Test
    public void exampleForSwitch() {
        Exception e = new SQLException("reason", "unknown");
        String errorMsg = switch (e) {
            case ParseException exception -> "Pattern matching switch: " + exception.getErrorOffset();
            case SQLException exception -> "Pattern matching switch: " + exception.getSQLState();
            default -> "unknow exception";
        };
        log.info("error message: {}", errorMsg);
    }
}
