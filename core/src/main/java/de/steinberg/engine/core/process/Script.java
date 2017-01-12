package de.steinberg.engine.core.process;

import static de.steinberg.engine.core.process.Platform.WIN;

/**
 * Created by LKLeen on 12.01.2017.
 */
public enum Script {
    FLUSH_TRACE("flushXPerfTrace.bat", 1, WIN),
    START_TRACE("startXPerfTrace.bat", 0, WIN),
    STOP_TRACE("stopNTKernelLogger.bat", 0, WIN);

    final String filename;
    final int numParameters;
    final Platform plaform;

    Script(String filename, int numParameters, Platform plaform) {
        this.filename = filename;
        this.numParameters = numParameters;
        this.plaform = plaform;
    }
}
