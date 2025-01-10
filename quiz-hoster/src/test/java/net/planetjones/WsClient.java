package net.planetjones;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class WsClient {

    private final String brokerUrl;
    private final String topic;

    public WsClient(String brokerUrl, String topic) {
        this.brokerUrl = brokerUrl;
        this.topic = topic;
    }

    public void connectAndSubscribe() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        List<Transport> transports = new ArrayList<>();
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
        SockJsClient sockJsClient = new SockJsClient(transports);

        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
        // Add any required headers here

        StompSessionHandlerAdapter sessionHandler = new MySessionHandler(latch);

        stompClient.connect(brokerUrl, headers, sessionHandler);

        latch.await();
    }

    private class MySessionHandler extends StompSessionHandlerAdapter {

        private final CountDownLatch latch;

        public MySessionHandler(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
            session.subscribe(topic, new StompFrameHandler() {
                @Override
                public Type getPayloadType(StompHeaders headers) {
                    // Define the type of message you expect to receive
                    return String.class; // Example: Receive String messages
                }

                @Override
                public void handleFrame(StompHeaders headers, Object payload) {
                    // Process the received message
                    String message = (String) payload;
                    System.out.println("Received message: " + message);
                }
            });
            latch.countDown();
        }
    }

    public static void main (String[] args) throws InterruptedException {
        String brokerUrl = "ws://localhost:8080/quiz"; // Replace with your actual broker URL
        String topic = "/app"; // Replace with your desired topic

        WsClient client = new WsClient(brokerUrl, topic);
        client.connectAndSubscribe();
        
    }
}