package com.server.network;

import com.common.util.Deserializer;
import com.common.network.Request;
import com.common.network.Response;
import com.common.util.Serializer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public final class UDPServer {
    private final DatagramSocket socket;
    private InetAddress clientAddress;
    private int clientPort;

    public UDPServer(int port) throws SocketException {
        socket = new DatagramSocket(port);
    }

    public Request receiveRequest() throws IOException, ClassNotFoundException {
        byte[] buffer = new byte[65536];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        socket.receive(packet);

        clientAddress = packet.getAddress();
        clientPort = packet.getPort();

        return (Request) Deserializer.toObject(packet.getData());
    }

    public void sentResponse(Response response) throws IOException {
        byte[] sentData = Serializer.toBytes(response);

        DatagramPacket packet = new DatagramPacket(sentData, sentData.length, clientAddress, clientPort);

        socket.send(packet);
    }

    public InetAddress getClientAddress() {
        return clientAddress;
    }

    public int getClientPort() {
        return clientPort;
    }
}
