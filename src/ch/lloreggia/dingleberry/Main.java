package ch.lloreggia.dingleberry;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) {
        try {
            Configuration configuration = new Configuration();
            HttpServer server = HttpServer.create(new InetSocketAddress(configuration.getServerPort()), 0);
            server.createContext("/", new HttpHandler() {
                @Override
                public void handle(HttpExchange httpExchange) throws IOException {
                    String response = "Test";
                    byte[] bytes = response.getBytes();
                    httpExchange.sendResponseHeaders(200, bytes.length);
                    OutputStream os = httpExchange.getResponseBody();
                    os.write(bytes);
                    os.close();
                }
            });
            server.setExecutor(null);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
