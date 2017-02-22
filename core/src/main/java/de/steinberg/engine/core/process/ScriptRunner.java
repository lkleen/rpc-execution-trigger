package de.steinberg.engine.core.process;

import de.steinberg.engine.core.exception.script.ErrorStreamParserException;
import de.steinberg.engine.core.exception.script.ExecutionException;
import de.steinberg.engine.core.exception.script.InvalidArgumentsException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by LKLeen on 12.01.2017.
 */
@Slf4j
public class ScriptRunner {

    private interface Printer {
        void println(Logger logger, String str);
    }

    private ErrorStreamParser errorStreamParser = new ErrorStreamParser();

    public void addErrorStreamValidator(ErrorStreamParser.Validator validator) {
        errorStreamParser.addValidator(validator);
    }

    public void run(Command command) {
        try {
            Process process = Runtime.getRuntime().exec(command.command);
            printOutput(process.getInputStream());
            printAndParseError(process.getErrorStream());
        } catch (Exception e) {
            throw new ExecutionException(e);
        }
    }

    public void run(Script script, String... args) {
        TemporaryBatchFile batchFile = null;
        try {
            validateArguments(script, args);
            batchFile = TemporaryBatchFile.createFrom(script);
            Process process = exec(batchFile, args);
            printOutput(process.getInputStream());
            printAndParseError(process.getErrorStream());
            batchFile.delete();
            if (errorStreamParser.getErrors().size() > 0) {
                ErrorStreamParserException ex = new ErrorStreamParserException("Script Execution Error", new ArrayList<>(errorStreamParser.getErrors()));
                errorStreamParser.clearErrors();
                throw ex;
            }
        } catch (Exception e) {
            throw new ExecutionException(e);
        }
    }

    private Process exec(TemporaryBatchFile batchFile, String[] args) throws Exception {
        String command = "cmd /c ";
        command += "\"" + batchFile.path.toAbsolutePath().toString() + "\" ";
        for (String arg : args) {
            command += arg + " ";
        }
        return Runtime.getRuntime().exec(command);
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

    private void printAndParseError(InputStream inputStream) throws Exception {
        printInputStream(inputStream, (Logger logger, String str) -> {
            errorStreamParser.parseLine(str);
            logger.error(str);
        });
    }

    private void printOutput(InputStream inputStream) throws Exception {
        printInputStream(inputStream, (Logger logger, String str) -> {logger.info(str);});
    }

    private void printInputStream(InputStream inputStream, Printer printer) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            printer.println(log, line);
        }
    }

}
