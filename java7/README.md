### Java 7 new features

### Binary Literal
In Java SE 7, the **_integral types (byte, short, int, and long)_** can also be expressed using the binary number system. 
To specify a binary literal, **_add the prefix 0b or 0B to the number_**. The following examples show binary literals:

    // An 8-bit 'byte' value:
    byte aByte = (byte)0b00100001;
    
    // A 16-bit 'short' value:
    short aShort = (short)0b1010000101000101;
    
    // Some 32-bit 'int' values:
    int anInt1 = 0b10100001010001011010000101000101;
    int anInt2 = 0b101;
    int anInt3 = 0B101; // The B can be upper or lower case.

    // A 64-bit 'long' value. Note the "L" suffix:
    long aLong = 0b1010000101000101101000010100010110100001010001011010000101000101L;

Binary literals can make relationships among data more apparent than they would be in hexadecimal or octal. 

### Underscores in Numeric Literals

In Java SE 7 and later, any number of underscore characters (_) can appear anywhere between digits in a numerical literal. 
This feature enables you, for example, to separate groups of digits in numeric literals, which can improve the readability of your code.

For instance, if your code contains numbers with many digits, you can use an underscore character to separate digits in groups of three, 
similar to how you would use a punctuation mark like a comma, or a space, as a separator.

The following example shows other ways you can use the underscore in numeric literals:

    long creditCardNumber = 1234_5678_9012_3456L;
    long socialSecurityNumber = 999_99_9999L;
    float pi = 	3.14_15F;
    long hexBytes = 0xFF_EC_DE_5E;
    long hexWords = 0xCAFE_BABE;
    long maxLong = 0x7fff_ffff_ffff_ffffL;
    byte nybbles = 0b0010_0101;
    long bytes = 0b11010010_01101001_10010100_10010010;

You can place underscores only between digits; you cannot place underscores in the following places:

* At the beginning or end of a number
* Adjacent to a decimal point in a floating point literal
* Prior to an F or L suffix
* In positions where a string of digits is expected
* The following examples demonstrate valid and invalid underscore placements (which are highlighted) in numeric literals:


    float pi1 = 3_.1415F;      // Invalid; cannot put underscores adjacent to a decimal point
    float pi2 = 3._1415F;      // Invalid; cannot put underscores adjacent to a decimal point
    long socialSecurityNumber1
    = 999_99_9999_L;         // Invalid; cannot put underscores prior to an L suffix
    
    int x1 = _52;              // This is an identifier, not a numeric literal
    int x2 = 5_2;              // OK (decimal literal)
    int x3 = 52_;              // Invalid; cannot put underscores at the end of a literal
    int x4 = 5_______2;        // OK (decimal literal)
    
    int x5 = 0_x52;            // Invalid; cannot put underscores in the 0x radix prefix
    int x6 = 0x_52;            // Invalid; cannot put underscores at the beginning of a number
    int x7 = 0x5_2;            // OK (hexadecimal literal)
    int x8 = 0x52_;            // Invalid; cannot put underscores at the end of a number
    
    int x9 = 0_52;             // OK (octal literal)
    int x10 = 05_2;            // OK (octal literal)
    int x11 = 052_;            // Invalid; cannot put underscores at the end of a number

### Strings in switch Statements

In the JDK 7 release, you can use a String object in the expression of a switch statement.
Before JDK 1.5, only byte, short, int, char can be used, JDK 1.5 added enumeration in switch statement.

The switch statement compares the String object in its expression with the expressions associated with each case label as if it were using the String.equals method; 
consequently, the comparison of String objects in switch statements is case sensitive. 
The Java compiler generates generally more efficient bytecode from switch statements that use String objects than from chained if-then-else statements.