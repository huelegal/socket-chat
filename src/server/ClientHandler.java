package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Trata a conexão com cada novo client. Instâncias são executadas por uma
 * thread separada. É static - pertence a classe, não a instância.
 */
public class ClientHandler implements Runnable {

    /** Monitora todos os clients conectados. Guarda instâncias desta classe. */
    public static ArrayList<ClientHandler> clients = new ArrayList<>();

    /** Socket passado pela classe server. Utilizado para estabelecer conexão. */
    private Socket socket;

    /** Utilizado para ler dados/mensagens enviadas pelo client. */
    private BufferedReader reader;

    /** Utilizado para enviar dados/mensagens para o client. */
    private BufferedWriter sender;

    private String username;

    // Construtor:
    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    /** Método run da interface Runnable, executa o código em threads. */
    @Override
    public void run() {

    }
}
