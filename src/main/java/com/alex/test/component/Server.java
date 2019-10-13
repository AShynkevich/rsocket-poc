package com.alex.test.component;

import io.rsocket.AbstractRSocket;
import io.rsocket.Payload;
import io.rsocket.RSocketFactory;
import io.rsocket.frame.decoder.PayloadDecoder;
import io.rsocket.transport.netty.server.TcpServerTransport;
import io.rsocket.util.DefaultPayload;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

public class Server {

    private final Disposable server;

    public Server() {
        this.server = RSocketFactory.receive()
                .frameDecoder(PayloadDecoder.ZERO_COPY)
                .acceptor((setupPayload, reactiveSocket) -> Mono.just(new RSocketImpl()))
                .transport(TcpServerTransport.create("localhost", 7000))
                .start()
                .subscribe();
    }

    public void dispose() {
        this.server.dispose();
    }

    private class RSocketImpl extends AbstractRSocket {
        @Override
        public Mono<Payload> requestResponse(Payload payload) {
            try {
                return Mono.just(DefaultPayload.create("Caught: " + payload.getDataUtf8())); // reflect the payload back to the sender
            } catch (Exception x) {
                return Mono.error(x);
            }
        }
    }
}
