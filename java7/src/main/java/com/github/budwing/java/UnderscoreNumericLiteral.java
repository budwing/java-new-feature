package com.github.budwing.java;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UnderscoreNumericLiteral {
    /**
     * In Java SE 7 and later, any number of underscore characters (_) can appear anywhere between digits in a numerical literal.
     * This feature enables you, for example, to separate groups of digits in numeric literals, which can improve the readability of your code.
     * For instance, if your code contains numbers with many digits, you can use an underscore character to separate digits in groups of three,
     * similar to how you would use a punctuation mark like a comma, or a space, as a separator.
     *
     * The following example shows other ways you can use the underscore in numeric literals:
     */
    public static void legalUsage() {
        long creditCardNumber = 1234_5678_9012_3456L;
        log.info("Underscore in long '1234_5678_9012_3456L' --> {}", creditCardNumber);
        long socialSecurityNumber = 999_99_9999L;
        log.info("Underscore in long '999_99_9999L' --> {}", socialSecurityNumber);
        float pi = 	3.14_15F;
        log.info("Underscore in float variable pi '3.14_15F' --> {}", pi);
        long hexBytes = 0xFF_EC_DE_5E;
        log.info("Underscore in hex number per byte '0xFF_EC_DE_5E' --> {}", hexBytes);
        long hexWords = 0xCAFE_BABE;
        log.info("Underscore in hex number per word '0xCAFE_BABE' --> {}", hexWords);
        long maxLong = 0x7fff_ffff_ffff_ffffL;
        log.info("Underscore for max long '0x7fff_ffff_ffff_ffffL' --> {}", maxLong);
        byte nybbles = 0b0010_0101;
        log.info("Underscore per nybbles '0b0010_0101' --> {}", nybbles);
        long bytes = 0b11010010_01101001_10010100_10010010;
        log.info("Underscore per byte  '0b11010010_01101001_10010100_10010010' --> {}", bytes);
    }

    /**
     * You can place underscores only between digits; you cannot place underscores in the following places:
     *
     *    At the beginning or end of a number
     *    Adjacent to a decimal point in a floating point literal
     *    Prior to an F or L suffix
     *    In positions where a string of digits is expected
     *
     * The following examples demonstrate valid and invalid underscore placements (which are highlighted) in numeric literals
     */
    public static void illegalUsage() {
        // float pi1 = 3_.1415F;
        log.error("'float pi1 = 3_.1415F;' --> cannot put underscores adjacent to a decimal point");
        // float pi2 = 3._1415F;
        log.error("'float pi1 = 3._1415F;' --> cannot put underscores adjacent to a decimal point");
        // long socialSecurityNumber1 = 999_99_9999_L;
        log.error("'long socialSecurityNumber1 = 999_99_9999_L;' --> cannot put underscores prior to an L suffix");

        // int x1 = _52;
        log.error("'int x1 = _52;' --> This is an identifier, not a numeric literal");
        // int x2 = 52_;
        log.error("'int x2 = 52_;' --> cannot put underscores at the end of a literal");
        int x3 = 5_2;              // OK (decimal literal)
        int x4 = 5_______2;        // OK (decimal literal)
        log.info("'5_2' --> {}, '5_______2' --> {}, multiple underscores can be used.", x3, x4);
        // int x5 = 0_x52;
        log.info("'int x5 = 0_x52;' --> cannot put underscores in the 0x radix prefix");
        // int x6 = 0x_52;
        log.info("'int x6 = 0x_52;' --> cannot put underscores at the beginning of a number");
        int x7 = 0x5_2;            // OK (hexadecimal literal)
        log.info("'int x8 = 0x52_;' --> cannot put underscores at the end of a number");

        int x9 = 0_52;             // OK (octal literal)
        int x10 = 05_2;            // OK (octal literal)
        log.info("'int x11 = 052_;' --> cannot put underscores at the end of a number");
    }
}
