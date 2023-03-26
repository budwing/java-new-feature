package com.github.budwing.java;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class TestTryWithResource {
    @Test
    public void testReadFirstLineFromFile() throws IOException {
        String resource = "/logback.xml";
        String firstLine = TryWithResouce.readFirstLineFromFile(resource);
        log.info("readFirstLineFromFile: the first line is '{}'", firstLine);
        firstLine = TryWithResouce.readFirstLineFromFileWithFinallyBlock(resource);
        log.info("readFirstLineFromFileWithFinallyBlock: the first line is '{}'", firstLine);
    }
    @Test
    public void testAddResourceToZip() throws IOException {
        String zipFile = System.getProperty("user.home")+"/test.zip";
        log.info("zip file will write to: {}", zipFile);
        TryWithResouce.addResourceToZip(zipFile, "/logback.xml");
        Files.deleteIfExists(Paths.get(zipFile));
    }
}
