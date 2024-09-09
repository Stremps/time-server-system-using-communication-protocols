package TCP;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;

import Log.localIp;

public class TCPServer {
    private volatile String currentTime;
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
    private static final int UPDATE_INTERVAL_MS = 1000; // Update interval in milliseconds

    public static void main(String[] args) {
        int port = 8082; // Port the server will listen on
        TCPServer server = new TCPServer();
        
        // Start thread to update time
        server.startUpdatingTime();

        try {
            
            // Gets the IP address of the "wlo" network interface or Windows equivalent
            String localIP = new localIp().getLocalIPAddress(new String[]{"wlo", "Wi-Fi", "WLAN"});
            
            if (localIP == null) {
                System.out.println("The IP address of interface 'wlo' could not be found.");
                return;
            }
            
            // Creates the TCP server socket
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started at IP address: " + localIP + " and waiting for connection on the port " + port);
            
            while (true) {
                // Accepts client connection
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connected client: " + clientSocket.getInetAddress().getHostAddress());
                
                // Create a new thread to handle the connection
                new Connection(clientSocket, server.getCurrentTime());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TCPServer() {
        updateCurrentTime();
    }

    private void startUpdatingTime() {
        Thread timeUpdater = new Thread(() -> {
            while (true) {
                updateCurrentTime();
                try {
                    Thread.sleep(UPDATE_INTERVAL_MS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        timeUpdater.setDaemon(true); // Makes the thread a daemon so that it does not prevent the program from exiting
        timeUpdater.start();
    }

    private void updateCurrentTime() {
        currentTime = LocalTime.now().format(TIME_FORMATTER);
    }

    public String getCurrentTime() {
        return currentTime;
    }
}
