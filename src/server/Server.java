package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Aguarda pedidos de conexão de novos clients e instância threads para
 * tratá-los.
 */
public class Server {

    /**
     * Aguarda conexões ou clients, instância um novo socket para se comunicar com
     * eles.
     */
    private ServerSocket server;

    // Construtor para receber um Server Socket:
    public Server(ServerSocket server) {
        this.server = server;
    }

    /** Método responsável por rodar o servidor. */
    public void runServer() {
        try {
            // O servidor deve rodar indefinidamente:
            while (!server.isClosed()) {
                Socket socket = server.accept(); // Roteia o server. Quando um cliente conecta, retorna um socket:
            }
        } catch (IOException e) {
            closeServer();
        }
    }

    /** Método responsável por encerrar o servidor. */
    public void closeServer() {
        try {
            server.close(); // Encerra o servidor.
        } catch (IOException e) {
            e.printStackTrace(); // Imprime o rastreamento de pilha da exceção no console.
        }
    }
}
