package tcp;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

public class TCPServer {
    public static void main(String[] args) {
        int port = 8082; // Port the server will listen on
        try {
            // Gets the IP address of the "wlo" network interface or Windows equivalent
            String localIP = getLocalIPAddress(new String[]{"wlo", "Wi-Fi", "WLAN"});
            
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
                new Connection(clientSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getLocalIPAddress(String[] interfaceNames) {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                
                // Checks if the interface is one of those specified
                for (String interfaceName : interfaceNames) {
                    if (networkInterface.getName().startsWith(interfaceName)) {
                        Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                        while (inetAddresses.hasMoreElements()) {
                            InetAddress inetAddress = inetAddresses.nextElement();
                            if (inetAddress instanceof Inet4Address) {
                                return inetAddress.getHostAddress();
                            }
                        }
                    }
                }
            }
        } catch (SocketException e) {
            System.out.println("Error getting network interfaces: " + e.getMessage());
        }
        return null;
    }
}
