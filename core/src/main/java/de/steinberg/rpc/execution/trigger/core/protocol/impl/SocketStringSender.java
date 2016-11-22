package de.steinberg.rpc.execution.trigger.core.protocol.impl;

import de.steinberg.rpc.execution.trigger.core.exception.RpcExecutionTriggerException;
import de.steinberg.rpc.execution.trigger.core.protocol.Message;
import de.steinberg.rpc.execution.trigger.core.protocol.StringSender;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by lkleen on 22.11.2016.
 */
public class SocketStringSender extends StringSender {

    private String host = "localhost";
    private int port = SocketStringReceiver.PORT;

    private Socket socket = null;

    protected void sendMessage(Message<String> message) {
        try {
            if (socket == null) {

                    socket = initializeSocket();
            }

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bw.write(message.getValue());
            bw.close();

        } catch (Exception e) {
            throw new RpcExecutionTriggerException(e);
        }
    }

    private Socket initializeSocket() throws Exception {
        return new Socket(host, port);
    }
}
