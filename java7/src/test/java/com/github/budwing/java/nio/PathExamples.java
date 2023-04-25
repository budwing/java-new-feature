package com.github.budwing.java.nio;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.*;

@Slf4j
public class PathExamples {
    @Test
    public void exampleForFileSystems() throws IOException {
        FileSystem fs = FileSystems.getDefault();
        log.info("default file system: {}", fs);
        for (FileStore store : fs.getFileStores()) {
            log.info("file store: {}, total space: {}GB", store.name(), store.getTotalSpace() / (1024 * 1024 * 1024));
        }
        for (Path path : fs.getRootDirectories()) {
            log.info("root path: {}", path);
        }
    }
    @Test
    public void exampleForPaths() {
        FileSystem fs = FileSystems.getDefault();
        Path path = fs.getPath("a","b","c");
        log.info("FileSystem.getPath without root: {}", path);
        path = fs.getPath("g:", "fakedir", "fakefile");
        log.info("FileSystem.getPath with root: {}", path);
        path = Paths.get("a","b","c");
        log.info("Paths.get without root: {}", path);
        path = Paths.get("g:", "fakedir", "fakefile");
        log.info("Paths.get with root: {}", path);
    }
}
