package de.steinberg.rpc.execution.trigger.core.protocol.impl;

import de.steinberg.rpc.execution.trigger.core.exception.RpcExecutionTriggerException;
import de.steinberg.rpc.execution.trigger.core.protocol.Message;
import de.steinberg.rpc.execution.trigger.core.protocol.StringMessage;
import de.steinberg.rpc.execution.trigger.core.protocol.StringReceiver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by lkleen on 22.11.2016.
 */
public class SocketStringReceiver extends StringReceiver {

    public static final int PORT = 8031;

    private ServerSocket serverSocket = null;

    public void close() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new RpcExecutionTriggerException(e);
        }
    }

    protected Message<String> receiveMessage() {
        try {

            if (serverSocket == null) {
                serverSocket = initializeServerSocket ();
            }

            Socket socket = serverSocket.accept();
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String msg = null;
            String value = "";
            while ((msg = br.readLine()) != null) {
                value += msg + "\n";
            }

            StringMessage stringMessage = new StringMessage();
            stringMessage.setValue (value);
            return stringMessage;
        } catch (Exception e) {
            throw new RpcExecutionTriggerException(e);
        }
    }

    private ServerSocket initializeServerSocket() throws Exception {
        return new ServerSocket(PORT);
    }


}
