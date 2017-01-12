package de.steinberg.engine.core.process;

/**
 * Created by LKLeen on 12.01.2017.
 */
public class Scripts {
    public static final Script START_TRACE_RECORDER = new Script("startXPerfTrace.bat", 0);
    public static final Script FLUSH_TRACE_RECORDER = new Script("flushXPerfTrace.bat", 1);
    public static final Script STOP_TRACE_RECORDER = new Script("stopNTKernelLogger.bat", 0);
}
