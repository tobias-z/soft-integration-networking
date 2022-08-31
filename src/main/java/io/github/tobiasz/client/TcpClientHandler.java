package io.github.tobiasz.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TcpClientHandler {

    private final InputStream inputStream;
    private final PrintWriter printWriter;
    private final Socket socket;

    public TcpClientHandler(Socket socket) throws IOException {
        this.inputStream = socket.getInputStream();
        this.printWriter = new PrintWriter(socket.getOutputStream());
        this.socket = socket;
    }

    public String readMessage() {
        return new Scanner(inputStream).nextLine();
    }

    public void sendMessage(String message) {
        this.printWriter.println(message);
        this.printWriter.flush();
    }

    public void closeSocket() {
        try {
            if (!this.socket.isClosed()) {
                this.socket.close();
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to close socket", e);
        }
    }

    public boolean isSocketOpen() {
        return !this.socket.isClosed();
    }
}
