package de.steinberg.engine.core.process;

import de.steinberg.engine.core.exception.ScriptRunnerException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by LKLeen on 12.01.2017.
 */
@Slf4j
public class ScriptRunner {

    private interface Printer {
        void println(Logger logger, String str);
    }

    public void run(Script script, String... args) {
        try {
            Process process = Runtime.getRuntime().exec(script.filename, args);
            printOutput(process.getInputStream());
            printError(process.getErrorStream());
        } catch (Exception e) {
            throw new ScriptRunnerException(e);
        }
    }

    private void printError(InputStream inputStream) throws Exception {
        printInputStream(inputStream, (Logger logger, String str) -> {logger.error(str);});
    }

    private void printOutput(InputStream inputStream) throws Exception {
        printInputStream(inputStream, (Logger logger, String str) -> {logger.info(str);});
    }

    private void printInputStream(InputStream inputStream, Printer printer) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            printer.println(log, line);
        }
    }

}
