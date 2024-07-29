package tcp;

import java.io.*;
import java.net.*;


public class TCPClient {
    public static void main(String[] args) {
        try {
            String serverAddress = "179.106.194.183"; // Replace with the server's actual IP address
            int serverPort = 8082;
            
            // Creates TCP client socket and connects to the server
            Socket clientSocket = new Socket(serverAddress, serverPort);
            
            // Creates input and output streams for communicating with the server
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
            // Mensagem a ser enviada ao servidor
            String sentence = "Teste de conexão 123";
            outToServer.writeBytes(sentence + '\n');
            
            // Read the server response
            String modifiedSentence = inFromServer.readLine();
            System.out.println("FROM SERVER: " + modifiedSentence);
            
            // Close the client socket
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}