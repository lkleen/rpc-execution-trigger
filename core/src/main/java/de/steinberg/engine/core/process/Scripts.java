package de.steinberg.engine.core.process;

/**
 * Created by LKLeen on 12.01.2017.
 */
public class Scripts {
    public static final Script START_FULL_TRACE = new Script("scripts/win/startFullTrace.bat", 1);
    public static final Script START_THREAD_PRIORITIES_TRACE = new Script("scripts/win/startThreadPrioritiesTrace.bat", 1);
    public static final Script FLUSH_TRACE = new Script("scripts/win/flushXPerfTrace.bat", 1);
    public static final Script STOP_TRACE = new Script("scripts/win/stopNTKernelLogger.bat", 0);
}
