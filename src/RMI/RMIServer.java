package RMI;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import Log.localIp;

public class RMIServer {
    public static void main(String[] args) {
        try {
            // Gets the IP address of the "wlo" network interface or Windows equivalent
            String localIP = new localIp().getLocalIPAddress(new String[]{"wlo", "Wi-Fi", "WLAN"});

            System.setProperty("java.rmi.server.hostname", localIP);  // Substitua pelo IP do servidor

            // Inicia o RMI Registry na porta 1099
            LocateRegistry.createRegistry(1099);
            
            // Cria e registra a fábrica de TimeService no RMI Registry
            TimeServiceFactoryImpl factory = new TimeServiceFactoryImpl();
            Naming.rebind("rmi://localhost/TimeServiceFactory", factory);
            
            System.out.println("TimeServiceFactory is ready. The host ip is: " + localIP);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
