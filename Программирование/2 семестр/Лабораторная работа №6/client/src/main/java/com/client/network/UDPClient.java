package com.client.network;

import com.common.util.Deserializer;
import com.common.network.Request;
import com.common.network.Response;
import com.common.util.Serializer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.PortUnreachableException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public final class UDPClient {
    private final DatagramChannel channel;

    public UDPClient(String host, int port) throws IOException {
        InetSocketAddress serverAddress = new InetSocketAddress(host, port);
        this.channel = DatagramChannel.open();

        channel.configureBlocking(false);
        channel.connect(serverAddress);
    }

    public void sendRequest(Request request) throws IOException {
        byte[] sentData = Serializer.toBytes(request);
        ByteBuffer buffer = ByteBuffer.wrap(sentData);
        channel.write(buffer);
    }

    public Response receiveResponse() throws InterruptedException, IOException, ClassNotFoundException {
        ByteBuffer buffer = ByteBuffer.allocate(65536);
        int bytesRead = 0;

        int timeoutMillis = 3000;
        int sleepTime = 50;
        int attempts = timeoutMillis / sleepTime;

        try {
            for (int i = 0; i < attempts; i++) {
                bytesRead = channel.read(buffer);

                if (bytesRead > 0) {
                    break;
                }
                Thread.sleep(sleepTime);
            }
        } catch (PortUnreachableException e) {
            throw new ServerUnavailableException("Сервер выключен или порт недоступен");
        }

        if (bytesRead == 0) {
            throw new ServerUnavailableException("Сервер временно недоступен (превышено время ожидания)");
        }

        buffer.flip();
        byte[] receivedData = new byte[buffer.remaining()];
        buffer.get(receivedData);

        return (Response) Deserializer.toObject(receivedData);
    }
}