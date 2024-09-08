package RMI;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
    public static void main(String[] args) {
        try {
            // Inicia o RMI Registry na porta 1099
            LocateRegistry.createRegistry(1099);
            
            // Cria e registra a fábrica de TimeService no RMI Registry
            TimeServiceFactoryImpl factory = new TimeServiceFactoryImpl();
            Naming.rebind("rmi://localhost/TimeServiceFactory", factory);
            
            System.out.println("TimeServiceFactory is ready.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
