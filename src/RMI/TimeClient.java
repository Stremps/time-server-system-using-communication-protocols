package RMI;
import java.rmi.Naming;

public class TimeClient {
    public static void main(String[] args) {
        try {
            // Conectando à fábrica remota via RMI Registry
            TimeServiceFactory factory = (TimeServiceFactory) Naming.lookup("rmi://localhost/TimeServiceFactory");
            
            // Solicitando uma nova instância do serviço de tempo
            TimeService timeService = factory.createNewTimeService();
            
            // Obtendo o horário atual do servidor
            String serverTime = timeService.getCurrentTime();
            System.out.println("Time from server: " + serverTime);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
