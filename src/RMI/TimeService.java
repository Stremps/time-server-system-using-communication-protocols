package RMI;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TimeService extends Remote {
    // Método para obter o horário do servidor
    String getCurrentTime(String clientIP, String clientTime) throws RemoteException;
}