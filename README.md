# Definitions
The Glitchtest Tracer Engine is running on the System under Test and controls the windows performance recorder (WPR). Once the Tracer Engine is started, WPR is continously writing system trace data into a ring buffer.
The size of the buffer can be defined according to the test requirements. Large buffers capture long traces but may create more overhead during runtime, small buffers are better for the performance, but may be too short to capture the actual glitch.
When the Tracer Engine is triggered either manually, or by the Glitchtest Monitor Engine, all data in the ring buffer is written to hard drive for later analysis.
After flushing, WPR continues with collecting data.

The Glitchtest Monitor Engine allows to monitor the System under Test from a remote workstation over the network.
When the monitored directory has been changed, the Monitor Engine receives a trigger information and sends a glitch notification to the Tracer Engine.
The Tracer Engine flushes the ring buffer and writes the trace data to hard drive. The ring buffer must be chosen large enough to contain the system operations which caused the glitch. It's a good idea to check out the necessary buffer size before the real test.

# Glitchtest Tracer Engine
## Setup System under Test
1. Start Glitchtest Tracer Engine executable (.jar on Mac) on the System under Test
2. Install applicable Java Runtime Environment, if necessary (Java will prompt you to do so)
## Open Network Connection
1. Open a Port for Glitch-Monitoring over Network (Hint: prefer high port numbers (< 10.000))
2. Press Start; if prompted by Firewall settings: grant private network access to the application
## Setup Trace Recorder
1. Select the type of trace you like to capture on a trigger event (e.g. full, priority changes,...)
2. Set the buffer size (in MB). This defines the space on your memory, to where WPR continuously writes the trace data of the last 10, 20, 30... seconds. Each system and trace profile requires a different amount of space for a trace of a certain length - try out and choose a buffer size that  your needs (recommended: 15 sec. at least).
3. Press Start; the Tracer is now continuously collecting data in the ring buffer and is ready for being triggered manually ("Capture Trace"), or from remote via Network connection.

# Glitchtest Monitoring Engine
## Setup Trigger Monitor System (Mac & PC)
1. Start Glitchtest Monitoring Engine application (.jar on Mac)
2. Install applicable Java Runtime Environment, if neccessary (Java will prompt you to do so)
## Establish Network Connection to Tracer Engine
1. Host: Insert the IP address of the System under Test (looks like this: 10.12x.xx.xxx - retrieve with "show localhost" button in the Tracer Engine or with cmd "ipconfig" (Win)/"ifconfig" (Mac))
2. Port: Insert the applicable port (same port you opened on the System under Test)
3. Test connection: Press "send glitch notification"
4. Success returns "(...) sender.SocketSender - connection established (...)" - the Glitch Tracer should write a trace to hard drive
5. A failed connection returns "(...) could not connect to 10.12x.xx.xxx:12345" - in this case, check your Firewall settings (a reset may help)
## Setup Monitoring Directory
1. Path: Insert the absolute path to the directory you want to monitor (the directory, where the trigger events are stored, e.g., to where .log, .dmp, .glitch files are written)
2. File Extension: Optional; if you want to limit the trigger to certain file types (e.g. .log, .glitch), inset the applicable file extension 