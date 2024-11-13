import java.net.ServerSocket;

import server.Server;

public class App {
    public static void main(String[] args) throws Exception {

        // Instânciando um servidor:
        Server server = new Server(new ServerSocket(7777));
        server.runServer();
    }
}
