package io.github.tobiasz.client;

import java.io.IOException;
import java.net.Socket;

public class TcpClientCommunicator {

    private final TcpClientHandler tcpClientHandler;

    public TcpClientCommunicator(String host, int port) throws IOException {
        this.tcpClientHandler = new TcpClientHandler(new Socket(host, port));
    }

    public String sendMessage(String message) {
        this.tcpClientHandler.sendMessage(message);
        return this.tcpClientHandler.readMessage();
    }
}
