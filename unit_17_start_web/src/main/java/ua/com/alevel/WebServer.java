package ua.com.alevel;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class WebServer {

    public void connect() {
        HttpServer server = null;
        try {
            server = HttpServer.create();
            server.bind(new InetSocketAddress(9000), 0);

            HttpContext context = server.createContext("/", new CustomHttpHandler());

            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
