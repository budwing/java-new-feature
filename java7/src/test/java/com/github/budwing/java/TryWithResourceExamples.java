package com.github.budwing.java;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Slf4j
public class TryWithResourceExamples {
    /**
     * The try-with-resources statement is a try statement that declares one or more resources.
     * A resource is as an object that must be closed after the program is finished with it.
     * The try-with-resources statement ensures that each resource is closed at the end of the statement.
     * Any object that implements java.lang.AutoCloseable, which includes all objects which implement java.io.Closeable, can be used as a resource.
     */
    @Test
    public void readFirstLineFromFile() throws IOException {
        String resource = "/logback.xml";
        log.info("The reource is {}", resource);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(TryWithResourceExamples.class.getResourceAsStream(resource)))) {
            log.info("readFirstLineFromFile: the first line is '{}'", br.readLine());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(TryWithResourceExamples.class.getResourceAsStream(resource)));
        try {
            log.info("readFirstLineFromFile: the first line is '{}'", br.readLine());
        } finally {
            if (br != null) br.close();
        }
    }

    /**
     * You may declare one or more resources in a try-with-resources statement.
     *
     */
    @Test
    public void addResourceToZip() throws java.io.IOException {
        String zipFile = System.getProperty("user.home")+"/test.zip", resource = "/logback.xml";
        log.info("zip file will write to: {}", zipFile);
        try (
                ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));
                InputStream in = TryWithResourceExamples.class.getResourceAsStream(resource)
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
        Files.deleteIfExists(Paths.get(zipFile));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //// try yourselves
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * try block must contain a declaration even when the resource exist, you must redeclare it using a different variable.
     *
     * In following examples, it executes one sql statement. But sometimes we may use one connection to execute two sql statement.
     * The connection needs to be shared between two try-catch block. If we want to close it in the second block, how to do that?
     *
     */
    @Test
    public void viewTable() {
        String query = "select COF_NAME, SUP_ID, PRICE, SALES, TOTAL from COFFEES";

        try (Connection connection = DriverManager.getConnection("a url");
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String coffeeName = rs.getString("COF_NAME");
                int supplierID = rs.getInt("SUP_ID");
                float price = rs.getFloat("PRICE");
                int sales = rs.getInt("SALES");
                int total = rs.getInt("TOTAL");
                System.out.println(coffeeName + ", " + supplierID + ", " + price +
                        ", " + sales + ", " + total);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
