package de.steinberg.rpc.execution.trigger.core.protocol.receiver;

import de.steinberg.rpc.execution.trigger.core.exception.RpcExecutionTriggerException;
import de.steinberg.rpc.execution.trigger.core.exception.SocketInitializationException;
import de.steinberg.rpc.execution.trigger.core.protocol.message.Message;
import de.steinberg.rpc.execution.trigger.core.protocol.message.StringMessage;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by lkleen on 22.11.2016.
 */
@Slf4j
public abstract class SocketReceiver<T> implements Receiver<T> {

    private static int UNINITIALIZED = -1;

    @Getter
    @Setter
    private int port = -1;

    private ServerSocket serverSocket = null;

    public void close() {
        try {
            serverSocket.close();
            serverSocket = null;
        } catch (IOException e) {
            throw new RpcExecutionTriggerException(e);
        }
    }

    private ServerSocket initializeServerSocket() throws Exception {
        if (port == UNINITIALIZED) {
            throw new SocketInitializationException("port not set");
        }
        if (serverSocket != null && serverSocket.getLocalPort() == port) {
            return serverSocket;
        }
        if (serverSocket != null) {
            log.info ("closing socket on port {}", serverSocket.getLocalPort());
            serverSocket.close();
            serverSocket = null;
        }
        ServerSocket serverSocket = new ServerSocket(port);
        log.info ("open socket on port {}", port);
        return serverSocket;
    }


    @Override
    public Message<T> receive() {
        try {
            serverSocket = initializeServerSocket ();
            Socket socket = serverSocket.accept();
            return receiveMessage(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            throw new RpcExecutionTriggerException(e);
        }
    }

    protected abstract Message<T> receiveMessage(InputStreamReader reader) throws IOException;
}
