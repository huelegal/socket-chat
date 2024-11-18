package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    /** Socket passado pela classe server. Utilizado para estabelecer conexão. */
    private Socket socket;

    /** Utilizado para ler dados/mensagens enviadas pelo server. */
    private BufferedReader reader;

    /** Utilizado para enviar dados/mensagens para o server. */
    private BufferedWriter writer;

    private String username;

    // Construtor:
    public Client(Socket socket, String username) {
        try {
            this.socket = socket;

            // Converte o stream de bits em caractéres.
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            this.username = username;
        } catch (IOException e) {
            // closeConnection(socket, reader, writer);
        }
    }

    public void sendMessage() {
        try {
            writer.write(username); // Para que o ClientHandler possa identificar.
            writer.newLine();
            writer.flush();

            Scanner scanner = new Scanner(System.in); // Para receber entradas pelo console.
        }
    }
}
