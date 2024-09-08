package TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import Log.LogToFile;

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
            // Reads the client time
            @SuppressWarnings("deprecation")
            String clientTime = in.readLine(); // Use readLine to support writeBytes on the client
            String ipUser = clientSocket.getInetAddress().getHostAddress();
            String message = String.format("(%s) Time Sync Request: Local Time (%s) --> Sent Time (%s)", ipUser, clientTime, serverTime);
            new LogToFile().logFile(message);

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