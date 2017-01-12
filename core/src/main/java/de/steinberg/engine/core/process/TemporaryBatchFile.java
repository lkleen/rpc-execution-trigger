package de.steinberg.engine.core.process;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

/**
 * Created by LKLeen on 12.01.2017.
 */
public class TemporaryBatchFile {

    final Path path;

    public TemporaryBatchFile(String filename) {
        path = Paths.get(filename);
    }

    public static TemporaryBatchFile createFrom(Script script) throws Exception {
        TemporaryBatchFile tempFile = new TemporaryBatchFile(createTempFileName ());
        if (Files.exists(tempFile.path)) {Files.delete(tempFile.path);}
        try {
            copyScriptContent(script, tempFile.path);
        } catch (IOException e) {
            tempFile.delete();
            throw e;
        }
        return tempFile;
    }

    private static String createTempFileName() {
        return new BigInteger(130, new Random()).toString(32) + getBatchFileExtension();
    }

    private static void copyScriptContent(Script script, Path path) throws IOException {
        InputStream input = getInputStreamFrom(script.filename);
        Files.copy(input, path);
        input.close();
    }

    private static InputStream getInputStreamFrom(String filename) throws FileNotFoundException {
        // first try to load from resources
        InputStream input = ClassLoader.getSystemResourceAsStream(filename);
        // if not found try loading file directly
        if (input == null) {
            input = new FileInputStream(filename);
        }
        return input;
    }

    public void delete() throws IOException {
        Files.delete(path);
    }

    private static String getBatchFileExtension() {
        return ".bat";
    }

}
