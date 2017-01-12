package de.steinberg.engine.core.process;

import de.steinberg.engine.core.exception.script.ExecutionException;
import de.steinberg.engine.core.exception.script.InvalidArgumentsException;
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

    public void run(Command command) {
        try {
            Process process = Runtime.getRuntime().exec(command.command);
            printOutput(process.getInputStream());
            printError(process.getErrorStream());
        } catch (Exception e) {
            throw new ExecutionException(e);
        }
    }

    public void run(Script script, String... args) {
        TemporaryBatchFile batchFile = null;
        try {
            validateArguments(script, args);
            batchFile = TemporaryBatchFile.createFrom(script);
            Process process = Runtime.getRuntime().exec(batchFile.path.toAbsolutePath().toString(), args);
            printOutput(process.getInputStream());
            printError(process.getErrorStream());
            batchFile.delete();
        } catch (Exception e) {
            throw new ExecutionException(e);
        }
    }

    private void validateArguments(Script script, String[] args) {
        if (script.numParameters == 0) {return;}
        if (script.numParameters > 0 && args == null) {
            throw new InvalidArgumentsException("numParameters > 0 and args == null");
        }
        if (script.numParameters > 0 && script.numParameters != args.length) {
            throw new InvalidArgumentsException("numParameters != args.length");
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
