package RMI;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class TimeServiceFactoryImpl extends UnicastRemoteObject implements TimeServiceFactory {

    protected TimeServiceFactoryImpl() throws RemoteException {
        super();
    }

    @Override
    public TimeService createNewTimeService() throws RemoteException {
        // Cria uma nova instância de TimeServiceImpl para o cliente
        return new TimeServiceImpl();
    }
}
