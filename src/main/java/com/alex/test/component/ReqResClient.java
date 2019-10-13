package com.alex.test.component;

import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.frame.decoder.PayloadDecoder;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.util.DefaultPayload;

public class ReqResClient {
    private final RSocket socket;

    public ReqResClient() {
        this.socket = RSocketFactory.connect()
                .frameDecoder(PayloadDecoder.ZERO_COPY)
                .transport(TcpClientTransport.create("localhost", 7000))
                .start()
                .block();
    }

    public String callBlocking(String string) {
        return socket
                .requestResponse(DefaultPayload.create(string))
                .map(Payload::getDataUtf8)
                .block();
    }

    public void dispose() {
        this.socket.dispose();
    }
}
