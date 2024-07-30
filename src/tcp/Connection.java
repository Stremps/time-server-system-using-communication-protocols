package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Connection extends Thread {
    private DataInputStream in;
    private DataOutputStream out;
    private Socket clientSocket;
    private String serverTime;

    public Connection(Socket aClientSocket, String serverTime) {
        try {
            this.clientSocket = aClientSocket;
            this.in = new DataInputStream(clientSocket.getInputStream());
            this.out = new DataOutputStream(clientSocket.getOutputStream());
            this.serverTime = serverTime;
            this.start(); // Start the thread
        } catch (IOException e) {
            System.out.println("Connection: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            // Date/Time in log file creation
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            String formattedDateTime = now.format(formatter);

            // Reads the client time
            @SuppressWarnings("deprecation")
            String clientTime = in.readLine(); // Use readLine to support writeBytes on the client
            String ipUser = clientSocket.getInetAddress().getHostAddress();
            String log = String.format("[%s] - (%s) Time Sync Request: Local Time (%s) --> Sent Time (%s)", formattedDateTime, ipUser, clientTime, serverTime);
            System.out.println(log);

            // Send the message back to the customer
            out.writeBytes(serverTime + '\n');
        } catch (EOFException e) {
            System.out.println("EOF: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                /* close failed */
            }
        }
    }
}