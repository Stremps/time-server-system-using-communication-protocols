package RMI;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class RMIServer {
    public static void main(String[] args) {
        try {
            // Gets the IP address of the "wlo" network interface or Windows equivalent
            String localIP = getLocalIPAddress(new String[]{"wlo", "Wi-Fi", "WLAN"});

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
