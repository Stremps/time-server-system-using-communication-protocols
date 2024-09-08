package RMI;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TimeServiceFactory extends Remote {
    // Método para criar um novo TimeService para o cliente
    TimeService createNewTimeService() throws RemoteException;
}
