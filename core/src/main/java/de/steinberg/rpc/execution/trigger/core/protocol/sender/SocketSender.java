package de.steinberg.rpc.execution.trigger.core.protocol.sender;

import de.steinberg.rpc.execution.trigger.core.exception.RpcExecutionTriggerException;
import de.steinberg.rpc.execution.trigger.core.exception.SocketInitializationException;
import de.steinberg.rpc.execution.trigger.core.protocol.message.Message;
import de.steinberg.rpc.execution.trigger.core.protocol.receiver.SocketStringReceiver;
import javafx.application.Platform;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by lkleen on 22.11.2016.
 */
@Slf4j
public abstract class SocketSender<T> implements Sender<T> {

    public static int UNINITIALIZED = -1;

    @Getter
    @Setter
    private String host = null;

    @Getter
    @Setter
    private int port = UNINITIALIZED;

    private Socket socket = null;

    private Socket initializeSocket() throws Exception {
        if (socket != null
                && socket.getInetAddress().getHostName().equals(host)
                && socket.getLocalPort() == port) {
            return socket;
        }
        if (host == null || host.length() == 0) {
            throw new SocketInitializationException("host == null");
        }
        if (port == UNINITIALIZED) {
            throw new SocketInitializationException("port == UNINITIALIZED");
        }
        if (socket != null) {
            log.info("closing connection to " + socket.getInetAddress().toString());
            socket.close();
        }
        Socket socket = createSocket();
        return socket;
    }

    @Override
    public void send(Message<T> message) {
        try {
            socket = initializeSocket();
            if (socket == null) {return;}
            log.info("sending request");
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            sendMessage(bw, message);
            bw.close();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private Socket createSocket() {
        try {
            log.info("connecting to {}:{}", host, port);
            Socket socket = new Socket(host, port);
            log.info("connection established");
            return socket;
        } catch (Exception e) {
            log.error("could connect to {}:{}", host, port);
            return null;
        }
    }

    protected abstract void sendMessage(BufferedWriter writer, Message<T> message) throws IOException;
}
