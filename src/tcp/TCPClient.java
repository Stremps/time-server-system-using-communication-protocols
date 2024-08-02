package projetosf.trabalhoSD1.timeserver;

// Importações necessárias para uso de sockets, I/O e geração de números aleatórios
import java.io.*;
import java.net.*;
import java.util.Random;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TCPClient {
    public static void main(String[] args) {
        try {
            // Geração de um horário aleatório para simular um relógio desincronizado
            Random random = new Random();
            int randomHour = random.nextInt(24);
            int randomMinute = random.nextInt(60);
            int randomSecond = random.nextInt(60);
            String currentTime = String.format("%02d:%02d:%02d", randomHour, randomMinute, randomSecond);
            
            System.out.println("Random time before sync: " + currentTime);

            // Configuração do endereço e porta do servidor
            String serverAddress = "127.0.0.1";
            int serverPort = 8082;

            // Estabelecimento da conexão TCP com o servidor
            Socket clientSocket = new Socket(serverAddress, serverPort);

            // Criação de streams para enviar e receber dados através do socket
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            long startTime = System.currentTimeMillis();
            // Solicitação de sincronização de tempo ao servidor
            outToServer.writeBytes("sync time\n");

            // Recebimento e impressão do horário atual do servidor
            String serverTime = inFromServer.readLine();

            // Medir o tempo de volta após receber a resposta
            long endTime = System.currentTimeMillis();

            currentTime = serverTime;

            long rtt = endTime - startTime;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
            LocalTime time = LocalTime.parse(currentTime, formatter);
            LocalTime adjustedTime = time.plusNanos(rtt / 2 * 1000000); // Ajustar metade do RTT em nanossegundos

            System.out.println("RAW TIME FROM SERVER: " + currentTime);
            System.out.println("ADJUSTED TIME FROM SERVER: " + adjustedTime);

            // Encerramento da conexão
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}