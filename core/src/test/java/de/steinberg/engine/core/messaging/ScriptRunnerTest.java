package de.steinberg.engine.core.messaging;

import de.steinberg.engine.core.exception.script.ExecutionException;
import de.steinberg.engine.core.process.Command;
import de.steinberg.engine.core.process.Script;
import de.steinberg.engine.core.process.ScriptRunner;
import org.testng.annotations.Test;

/**
 * Created by LKLeen on 12.01.2017.
 */
public class ScriptRunnerTest {

    final ScriptRunner scriptRunner = new ScriptRunner();

    @Test(expectedExceptions = ExecutionException.class)
    public void testNullpointerArgs() {
        Script script = new Script("ls", 1);
        scriptRunner.run(script);
    }

    @Test
    public void testSimpleCommand() {
        Command cmd = new Command("java -version"); // note: will print to error stream
        scriptRunner.run(cmd);
    }

    @Test(expectedExceptions = ExecutionException.class)
    public void testInvalidCommand() {
        Script script = new Script("foo", 0);
        scriptRunner.run(script);
    }

    @Test
    public void testTestScript() {
        Script script = new Script("test.script", 0);
        scriptRunner.run(script);
    }

}
