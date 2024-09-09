package RMI;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import Log.LogToFile;

public class TimeServiceImpl extends UnicastRemoteObject implements TimeService {

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    // Construtor necessário para objetos RMI
    protected TimeServiceImpl() throws RemoteException {
        super();
    }

    // Implementação do método remoto para obter o horário do servidor
    @Override
    public String getCurrentTime(String clientIP, String clientTime) throws RemoteException {
        String currentTime = LocalTime.now().format(TIME_FORMATTER);
        
        // Criando um log para a requisição de tempo
        String logMessage = String.format("(%s) Time Sync Requested: Local Time (%s) --> Sent Time (%s)", clientIP, clientTime, currentTime);
        new LogToFile().logFile(logMessage);
        
        return currentTime;
    }
}
