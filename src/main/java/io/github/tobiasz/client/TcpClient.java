package io.github.tobiasz.client;

import io.github.tobiasz.server.TcpServer;
import io.github.tobiasz.util.IObserver;
import java.io.IOException;
import java.net.Socket;

public class TcpClient implements Runnable, IObserver<String> {

    private final TcpClientHandler tcpClientHandler;
    private final TcpServer tcpServer;

    public TcpClient(Socket socket, TcpServer tcpServer) throws IOException {
        this.tcpClientHandler = new TcpClientHandler(socket);
        this.tcpServer = tcpServer;
    }

    @Override
    public void run() {
        while (true) {
            String message = this.tcpClientHandler.readMessage();
            if (message.equals("exit")) {
                break;
            }
            this.tcpServer.broadcast(message);
        }

        this.tcpClientHandler.closeSocket();
        this.tcpServer.remove(this);
    }

    @Override
    public void update(String data) {
        this.tcpClientHandler.sendMessage(data);
    }
}
