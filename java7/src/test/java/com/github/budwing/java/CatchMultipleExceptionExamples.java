package com.github.budwing.java;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

@Slf4j
public class CatchMultipleExceptionExamples {
    String exceptionName = "First";
    static class FirstException extends Exception {
    }

    static class SecondException extends Exception {
    }

    /**
     * In Java SE 7 and later, a single catch block can handle more than one type of exception.
     * This feature can reduce code duplication and lessen the temptation to catch an overly broad exception.
     * Note: If a catch block handles more than one exception type, then the catch parameter is implicitly final.
     * In this example, the catch parameter ex is final and therefore you cannot assign any values to it within the catch block.
     */
    @Test
    public void catchMultiple() {

        try {
            if (exceptionName.equals("First")) {
                throw new FirstException();
            } else {
                throw new SecondException();
            }
        } catch (FirstException | SecondException ex) {
            log.info("got exception: {}", ex.getClass());
            // can't assign new value, ex is final
            // ex = new FirstException();
        }
    }

    /**
     * However, in Java SE 7, you can specify the exception types FirstException and SecondException in the throws clause in the rethrowException method declaration.
     * The Java SE 7 compiler can determine that the exception thrown by the statement throw e must have come from the try block,
     * and the only exceptions thrown by the try block can be FirstException and SecondException.
     * Even though the exception parameter of the catch clause, e, is type Exception,
     * the compiler can determine that it is an instance of either FirstException or SecondException:
     *
     * @throws FirstException
     * @throws SecondException
     */
    @Test
    public void rethrowException() throws FirstException, SecondException {
        try {
            if (exceptionName.equals("First")) {
                throw new FirstException();
            } else {
                throw new SecondException();
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
