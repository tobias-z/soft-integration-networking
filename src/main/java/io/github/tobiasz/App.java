package io.github.tobiasz;

import io.github.tobiasz.client.TcpClientCommunicator;
import io.github.tobiasz.client.UdpClientCommunicator;
import io.github.tobiasz.server.TcpServer;
import io.github.tobiasz.server.UdpServer;

public class App {

    public static void main(String[] args) throws Exception {
        new Thread(new TcpServer(6000)).start();
        new Thread(new UdpServer(6001)).start();

        TcpClientCommunicator tcpClientCommunicator = new TcpClientCommunicator("127.0.0.1", 6000);
        System.out.println(tcpClientCommunicator.sendMessage("bob"));
        System.out.println(tcpClientCommunicator.sendMessage("the builder"));

        UdpClientCommunicator udpClientCommunicator = new UdpClientCommunicator("127.0.0.1", 6001);
        System.out.println(udpClientCommunicator.sendMessage("hello oworld"));
    }
}