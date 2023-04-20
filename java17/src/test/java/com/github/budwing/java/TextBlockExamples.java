package com.github.budwing.java;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

/**
 *
 */
@Slf4j
public class TextBlockExamples {
    /**
     * Text block is a better way to render a string that spans multiple lines.
     */
    @Test
    public void multipleLine() {
        // traditional way for multiple line strings
        log.info("traditional way for for multiple line strings");
        String traditional = "    Traditional way for first line\n" +
                "    Traditional way for second line\n" +
                "    Traditional way for third line";
        System.out.println(traditional);

        // text block way for multiple line strings
        log.info("text block way for multiple line strings");
        String textBlock = """
                    Text block for the first line
                    Text block for the second line
                    Text block for the third line
                """;
        System.out.println(textBlock);
    }

    /**
     * html use case
     */
    @Test
    public void htmlTextBlock() {
        log.info("Using \"one-dimensional\" string literals");
        String oneDimensional = "<html>\n" +
                "    <body>\n" +
                "        <p>Hello, world</p>\n" +
                "    </body>\n" +
                "</html>\n";
        System.out.println(oneDimensional);
        // please be aware of the white space
        log.info("Using a \"two-dimensional\" block of text");
        String twoDimensional = """
                <html>
                    <body>
                        <p>Hello, world</p>
                    </body>
                </html>
                """;
        System.out.println(twoDimensional);
    }

    /**
     * sql use case
     */
    @Test
    public void sqlTextBlock() {
        log.info("Using \"one-dimensional\" string literals");
        String oneDimensional = "SELECT \"EMP_ID\", \"LAST_NAME\" FROM \"EMPLOYEE_TB\"\n" +
                "WHERE \"CITY\" = 'INDIANAPOLIS'\n" +
                "ORDER BY \"EMP_ID\", \"LAST_NAME\";\n";
        System.out.println(oneDimensional);

        log.info("Using a \"two-dimensional\" block of text");
        // please be aware of the incidental white space and the final line terminator
        String twoDimensional = """
                    SELECT "EMP_ID", "LAST_NAME" FROM "EMPLOYEE_TB"
                    WHERE "CITY" = 'INDIANAPOLIS'
                    ORDER BY "EMP_ID", "LAST_NAME";
                """;
        System.out.println(twoDimensional);
    }

    /**
     * Two escapes were introduced in Java14
     *
     * The \<line-terminator> escape sequence explicitly suppresses the inclusion of an implicit new line character.
     * The \s escape sequence simple translates to space.
     */
    @Test
    public void newEscape() {
        log.info("\\<line-terminator> escape sequence is used to connect multiple lines into one line.");
        String oneLine = """
                this will be show in one line \
                although this is multiple line in our source code.
                """;
        System.out.println(oneLine);
        log.info("\\s escape sequence keeps the spaces before it.");
        String colors = """
                red  \s
                green\s
                blue \s
                """;
        System.out.println(colors);
    }
}
