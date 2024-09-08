package TCP;

import java.io.*;
import java.net.*;
import java.util.Random;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TCPClient {
    public static void main(String[] args) {
        try {
            // Generation of a random time to simulate a desynchronized clock
            Random random = new Random();
            int randomHour = random.nextInt(24);
            int randomMinute = random.nextInt(60);
            int randomSecond = random.nextInt(60);
            String currentTime = String.format("%02d:%02d:%02d", randomHour, randomMinute, randomSecond);
            
            System.out.println("Random time before sync: " + currentTime);

            // Configuring the server address and port
            String serverAddress = "127.0.0.1";
            int serverPort = 8082;

            // Establishing the TCP connection with the server
            Socket clientSocket = new Socket(serverAddress, serverPort);

            // Creating streams to send and receive data through the socket
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
            long startTime = System.currentTimeMillis();
            // Time synchronization request to the server
            outToServer.writeBytes(currentTime + "\n"); //  Add line break to inform the server

            // Receiving and printing the current time from the server
            String serverTime = inFromServer.readLine();

            // Measure turnaround time after receiving response
            long endTime = System.currentTimeMillis();

            currentTime = serverTime;

            long rtt = endTime - startTime;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
            LocalTime time = LocalTime.parse(currentTime, formatter);
            LocalTime adjustedTime = time.plusNanos(rtt / 2 * 1000000); // Adjust half RTT in nanoseconds

            System.out.println("RAW TIME FROM SERVER: " + currentTime);
            System.out.println("ADJUSTED TIME FROM SERVER: " + adjustedTime);

            // Closing the connection
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}