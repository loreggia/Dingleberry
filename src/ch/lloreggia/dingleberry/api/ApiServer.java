package ch.lloreggia.dingleberry.api;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class ApiServer {
    private final HttpServer _server;

    public ApiServer(int port) throws IOException {
        _server = HttpServer.create(new InetSocketAddress(port), 0);
        _server.createContext("/", e -> handleHttpExchange(e));
        _server.setExecutor(null);
    }

    private void handleHttpExchange(HttpExchange httpExchange) {
        // TODO
        try {
            String response = "Test";
            byte[] bytes = response.getBytes();
            httpExchange.sendResponseHeaders(200, bytes.length);
            OutputStream os = httpExchange.getResponseBody();
            os.write(bytes);
            os.close();
        } catch (IOException iex) {
            iex.printStackTrace();
        }
    }

    public void start() {
        _server.start();
    }
}
