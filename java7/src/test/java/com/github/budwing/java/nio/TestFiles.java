package com.github.budwing.java.nio;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

@Slf4j
public class TestFiles {
    /**
     * native copy will be used. so it's faster than read/write by java io.
     *
     * @throws IOException
     */
    @Test
    public void testCopy() throws IOException {
        Path a = Paths.get("f:", "a.txt");
        Path b = Paths.get("f:", "b.txt");
        Files.write(a, "a test file".getBytes(), StandardOpenOption.CREATE);
        Files.copy(a, b, StandardCopyOption.REPLACE_EXISTING);
        log.info("copy string: {}", Files.readAllLines(b, StandardCharsets.UTF_8).get(0));
        Files.move(b, a, StandardCopyOption.REPLACE_EXISTING);
        Files.delete(a);
    }

    /**
     * FileVisitor applies visitor pattern to iterate files in a tree.
     * @throws IOException
     */
    @Test
    public void testFileVisitor() throws IOException {
        Path root = Paths.get("c:", "Program Files");
        Files.walkFileTree(root, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                if (exc instanceof AccessDeniedException) {
                    log.error("access denied: {}", file);
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                log.info(file.toString());
                return FileVisitResult.CONTINUE;
            }
        });
    }

    /**
     * Files.createFile, createTempFile
     * @throws IOException
     */
    @Test
    public void testTempFile() throws IOException {
        Path path = Files.createFile(Paths.get("f:", "test"));
        log.info("create file: {}", path);
        Files.delete(path);
        path = Files.createTempFile(null, null);
        log.info("create temp file is {}", path);
        if(Files.deleteIfExists(path)) {
            log.info("temp file deleted");
        }
    }
}
