package io.github.tobiasz.client;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UdpClientCommunicator {

    private final byte[] BUFFER_IN = new byte[128];

    private final DatagramSocket socket;
    private final int port;
    private final InetAddress address;

    public UdpClientCommunicator(String host, int port) throws SocketException, UnknownHostException {
        this.address = Inet4Address.getByName(host);
        this.port = port;
        this.socket = new DatagramSocket();
    }

    public String sendMessage(String message) throws IOException {
        byte[] bufferOut = message.getBytes(UTF_8);
        this.socket.send(new DatagramPacket(bufferOut, bufferOut.length, this.address, this.port));

        DatagramPacket response = new DatagramPacket(BUFFER_IN, BUFFER_IN.length);
        this.socket.receive(response);
        return new String(response.getData(), 0, response.getLength());
    }
}
