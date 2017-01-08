package de.steinberg.engine.core.protocol.sender;

import de.steinberg.engine.core.exception.RpcExecutionTriggerException;
import de.steinberg.engine.core.exception.SocketInitializationException;
import de.steinberg.engine.core.protocol.message.Message;
import de.steinberg.engine.core.protocol.receiver.SocketStringReceiver;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by lkleen on 22.11.2016.
 */
@Slf4j
public class SocketStringSender extends StringSender {

    @Getter
    @Setter
    private String host = null;

    @Getter
    @Setter
    private int port = SocketStringReceiver.PORT;

    private Socket socket = null;

    protected void sendMessage(Message<String> message) {
        try {
            if (socket == null) {
                    socket = initializeSocket();
            }

            log.info("sending request");
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bw.write(message.getValue());
            bw.close();

        } catch (Exception e) {
            throw new RpcExecutionTriggerException(e);
        }
    }

    private Socket initializeSocket() throws Exception {
        if (host == null) {
            throw new SocketInitializationException("host == null");
        }
        return new Socket(host, port);
    }
}
