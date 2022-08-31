package io.github.tobiasz.server;

import io.github.tobiasz.client.TcpClient;
import io.github.tobiasz.util.IObservable;
import io.github.tobiasz.util.IObserver;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TcpServer implements Runnable, IObservable<String> {

    private final int port;
    private final List<IObserver<String>> observers = new ArrayList<>();

    public TcpServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port);) {
            while (true) {
                Socket socket = serverSocket.accept();
                TcpClient client = new TcpClient(socket, this);
                this.broadcast("New client has joined");
                this.add(client);
                new Thread(client).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void broadcast(String message) {
        for (IObserver<String> observer : this.observers) {
            observer.update(message);
        }
    }

    @Override
    public void add(IObserver<String> observer) {
        this.observers.add(observer);
    }

    @Override
    public void remove(IObserver<String> observer) {
        this.observers.remove(observer);
    }
}
