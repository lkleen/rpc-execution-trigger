package de.steinberg.engine.core.protocol.receiver;

import de.steinberg.engine.core.exception.EngineException;
import de.steinberg.engine.core.protocol.message.Message;
import de.steinberg.engine.core.protocol.message.StringMessage;
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
public class SocketStringReceiver extends StringReceiver {

    public static final int PORT = 8031;

    private ServerSocket serverSocket = null;

    public void close() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new EngineException(e);
        }
    }

    protected Message<String> receiveMessage() {
        try {

            if (serverSocket == null) {
                serverSocket = initializeServerSocket ();
            }

            Socket socket = serverSocket.accept();
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String msg;
            String value = "";
            while ((msg = br.readLine()) != null) {
                value += msg;
            }

            StringMessage stringMessage = new StringMessage();
            stringMessage.setValue (value);
            return stringMessage;
        } catch (Exception e) {
            throw new EngineException(e);
        }
    }

    private ServerSocket initializeServerSocket() throws Exception {
        ServerSocket serverSocket = new ServerSocket(PORT);
        log.info ("listening to port {}", PORT);
        return serverSocket;
    }


}
