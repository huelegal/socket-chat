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
    private ServerSocket serverSocket;

    // Construtor para receber um Server Socket:
    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    /** Método responsável por rodar o servidor. */
    public void runServer() {
        try {
            // O servidor deve rodar indefinidamente:
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept(); // Roteia o server. Quando um cliente conecta, retorna um socket.

                ClientHandler clientHandler = new ClientHandler(socket); // Inicia um novo handler para tratar o client.
                System.out.println(clientHandler.getUsername() + " se conectou ao servidor!");

                /** Thread responsável pelo ClientHandler */
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            closeServer();
        }
    }

    /** Método responsável por encerrar o servidor. */
    public void closeServer() {
        try {
            serverSocket.close(); // Encerra o servidor.
        } catch (IOException e) {
            e.printStackTrace(); // Imprime o rastreamento de pilha da exception no console.
        }
    }

    /**
     * Cria um novo servidor.
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(7777); // Iniciando um novo Server Socket.
        Server server = new Server(serverSocket); // Criando um novo servidor.

        server.runServer(); // Rodando o servidor.
    }
}
