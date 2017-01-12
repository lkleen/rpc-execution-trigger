package de.steinberg.engine.core.process;

/**
 * Created by LKLeen on 12.01.2017.
 */
public enum ScriptASD {
    FLUSH_TRACE("flushXPerfTrace.bat", 1),
    START_TRACE("startXPerfTrace.bat", 0),
    STOP_TRACE("stopNTKernelLogger.bat", 0);

    final String filename;
    final int numParameters;

    ScriptASD(String filename, int numParameters) {
        this.filename = filename;
        this.numParameters = numParameters;
    }
}
