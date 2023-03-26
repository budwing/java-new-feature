package com.github.budwing.java;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

@Slf4j
public class TryWithResouce {
    /**
     * The try-with-resources statement is a try statement that declares one or more resources.
     * A resource is as an object that must be closed after the program is finished with it.
     * The try-with-resources statement ensures that each resource is closed at the end of the statement.
     * Any object that implements java.lang.AutoCloseable, which includes all objects which implement java.io.Closeable, can be used as a resource.
     * <p>
     * The following example reads the first line from a file. It uses an instance of BufferedReader to read data from the file.
     * BufferedReader is a resource that must be closed after the program is finished with it.
     * <p>
     * The class BufferedReader, in Java SE 7 and later, implements the interface java.lang.AutoCloseable.
     * Because the BufferedReader instance is declared in a try-with-resource statement,
     * it will be closed regardless of whether the try statement completes normally or abruptly (as a result of the method BufferedReader.readLine throwing an IOException).
     *
     * @param resource
     * @return
     * @throws IOException
     */
    public static String readFirstLineFromFile(String resource) throws IOException {
        log.info("The reource is {}", resource);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(TryWithResouce.class.getResourceAsStream(resource)))) {
            return br.readLine();
        }
    }

    /**
     * Prior to Java SE 7, you can use a finally block to ensure that a resource is closed regardless of whether the try statement completes normally or abruptly.
     * The following example uses a finally block instead of a try-with-resources statement:
     */
    public static String readFirstLineFromFileWithFinallyBlock(String resource) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(TryWithResouce.class.getResourceAsStream(resource)));
        try {
            return br.readLine();
        } finally {
            if (br != null) br.close();
        }
    }

    /**
     * You may declare one or more resources in a try-with-resources statement.
     *
     * @param zipFileFullPath
     * @param resource
     * @throws java.io.IOException
     */
    public static void addResourceToZip(String zipFileFullPath, String resource) throws java.io.IOException {
        try (
                ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileFullPath));
                InputStream in = TryWithResouce.class.getResourceAsStream(resource)
        ) {
            out.putNextEntry(new ZipEntry(resource));
            byte[] buf = new byte[1024];
            int len = -1;
            while ((len = in.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
        } catch (Exception e) {
            log.error("Suppressed exceptions: {}", e.getSuppressed().toString());
        }
    }

    /**
     * The following example uses a try-with-resources statement to automatically close a java.sql.Statement object:
     * @param con
     * @throws SQLException
     */
    public static void viewTable(Connection con) throws SQLException {

        String query = "select COF_NAME, SUP_ID, PRICE, SALES, TOTAL from COFFEES";

        try (Statement stmt = con.createStatement()) {

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String coffeeName = rs.getString("COF_NAME");
                int supplierID = rs.getInt("SUP_ID");
                float price = rs.getFloat("PRICE");
                int sales = rs.getInt("SALES");
                int total = rs.getInt("TOTAL");
                System.out.println(coffeeName + ", " + supplierID + ", " + price +
                        ", " + sales + ", " + total);
            }

        }
    }
}
