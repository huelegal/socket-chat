package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
    private BufferedWriter writer;

    private String username;

    // Construtor:
    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;

            /*
             * Socket gera um OutputStream (stream de bytes), que são convertidos para
             * OutputStreamWriter (strem de char) e inseridos dentro do Buffer.
             */
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.username = reader.readLine(); // Recebe o username do client.
            clients.add(this); // Adicionando o usuário ao group chat.
        } catch (IOException e) {
            closeConnection(socket, reader, writer);
        }
    }

    /**
     * Client saiu do servidor.
     */
    public void userLeft() {
        clients.remove(this);
        broadcastMessage("SERVER: " + username + " saiu do servidor!"); // Informa no server que o client saiu.
    }

    public void closeConnection(Socket socket, BufferedReader reader, BufferedWriter writer) {
        userLeft(); // Remove o client da lista e informa os outros clients.

        // Fechando o reader, writer e socket.
        try {
            if (reader != null)
                reader.close();

            if (writer != null)
                writer.close();

            if (socket != null)
                socket.close(); // Fechar o socket também encerra o Output/InputStreamWriter
        } catch (IOException e) {
            e.printStackTrace(); // Imprimindo a pilha da exception no console.
        }
    }

    /**
     * Envia mensagens para todos os usuários conectados no server.
     * 
     * @param message
     */
    public void broadcastMessage(String message) {
        // Percorrendo a lista de usuários conectados.
        clients.forEach(client -> {
            try {
                // Se não foi o usuário que mandou a mensagem, ele a recebe.
                if (!client.username.equals(username)) {
                    client.writer.write(message); // Imprime a mensagem pasra o client.
                    client.writer.newLine(); // "Já enviei, não precisa esperar mais/apertar enter".
                    client.writer.flush();
                }
            } catch (IOException e) {
                closeConnection(socket, reader, writer);
            }
        });
    }

    /** Esperando por mensagens. */
    @Override
    public void run() {
        String message;

        // Enquanto o client ainda estiver conectado.
        while (socket.isConnected()) {

        }
    }
}
