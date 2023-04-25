package com.github.budwing.java;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

@Slf4j
public class BinaryLiteralExamples {
    /**
     * bsil is byte,short,in and long.
     * In Java SE 7, the integral types (byte, short, int, and long) can also be expressed using the binary number system.
     * To specify a binary literal, add the prefix 0b or 0B to the number. The following examples show binary literals:
     */
    @Test
    public void bsil() {
        byte aByte = (byte) 0b00100001;
        log.info("An 8-bit 'byte' value: '0b00100001' ---> {}", aByte);

        short aShort = (short) 0b1010000101000101;
        log.info("A 16-bit 'short' value: '0b1010000101000101' ---> {}", aShort);

        int anInt1 = 0b10100001010001011010000101000101;
        log.info("Some 32-bit 'int' values: '0b10100001010001011010000101000101' ---> {}", anInt1);
        int anInt2 = 0b101;
        log.info("Some 32-bit 'int' values: '0b101' ---> {}", anInt2);
        int anInt3 = 0B101;
        log.info("The B can be upper or lower case: '0B101 ---> {}", anInt3);

        long aLong = 0b1010000101000101101000010100010110100001010001011010000101000101L;
        log.info("A 64-bit 'long' value. Note the \"L\" suffix: '0b1010000101000101101000010100010110100001010001011010000101000101L' ---> {}", aLong);
    }

    /**
     * binary format make rendering binary data easier than before.
     * The following short array represents a happy face.
     */
    public static final short[] HAPPY_FACE = {
            (short) 0b0000011111100000,
            (short) 0b0000100000010000,
            (short) 0b0001000000001000,
            (short) 0b0010000000000100,
            (short) 0b0100000000000010,
            (short) 0b1000011001100001,
            (short) 0b1000011001100001,
            (short) 0b1000000000000001,
            (short) 0b1000000000000001,
            (short) 0b1001000000001001,
            (short) 0b1000100000010001,
            (short) 0b0100011111100010,
            (short) 0b0010000000000100,
            (short) 0b0001000000001000,
            (short) 0b0000100000010000,
            (short) 0b0000011111100000
    };
    @Test
    public void happyFace() {
        log.info("{}", HAPPY_FACE);
        for (short num : HAPPY_FACE) {
            print(num);
        }
    }
    // Integer.toBinaryString contains unuseful zero.
    private void print(int num) {
        for (int i = 15; i >= 0; i--) {
            if ((num >>> i & 1) == 1) {
                System.out.print(" * ");
            } else {
                System.out.print("   ");
            }
        }
        System.out.println();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //// try yourselves
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * No. 1
     * Do you remember other number literal expression, like oct, hex, list them here
     */
    @Test
    public void examplesForOctHex() {

    }
    /**
     * No. 2
     * by default, binary number's type is int
     * try to remove type cast to see what will happen, and explain why
     */
    @Test
    public void defaultDataType() {
        // remove (byte), it's OK
        byte aByte = (byte) 0b00100001;
        log.info("An 8-bit 'byte' value: '0b00100001' ---> {}", aByte);

        // remove (short), it's error. try to change 0b1.... to 0b0...., see what happen
        short aShort = (short) 0b1010000101000101;
        log.info("A 16-bit 'short' value: '0b1010000101000101' ---> {}", aShort);
    }
}
