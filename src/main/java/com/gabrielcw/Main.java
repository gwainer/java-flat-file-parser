package com.gabrielcw;

import com.gabrielcw.flatfile.FlatFileProcessor;
import com.gabrielcw.watcher.WatchDir;
import com.github.ffpojo.exception.FFPojoException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException, FFPojoException {
        Path dir = Paths.get(System.getenv("HOMEPATH") + "/data/in");

        processFilesInFolder(dir.toString());
        //watch new files and changes
        new WatchDir(dir).processEvents();
    }

    private static void processFilesInFolder(String inPath) {
        File dir = new File(inPath);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                FlatFileProcessor.getInstance().processFile(child);
            }
        }
    }
}
