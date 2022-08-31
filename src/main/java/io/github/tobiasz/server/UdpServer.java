package io.github.tobiasz.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpServer implements Runnable {

    private static final byte[] BUFFER_IN = new byte[512];

    private final int port;

    public UdpServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try (DatagramSocket datagramSocket = new DatagramSocket(this.port)) {
            while (true) {
                DatagramPacket message = new DatagramPacket(BUFFER_IN, BUFFER_IN.length);
                datagramSocket.receive(message);
                datagramSocket.send(
                    new DatagramPacket(message.getData(), message.getLength(), message.getAddress(), message.getPort()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
