package RMI;
import java.net.InetAddress;
import java.rmi.Naming;
import java.util.Random;

import Log.localIp;

import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
import java.net.Inet4Address;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class TimeClient {
    public static void main(String[] args) {
        try {
            // Geração de um horário aleatório para simular um relógio desincronizado
            Random random = new Random();
            int randomHour = random.nextInt(24);   // Hora aleatória entre 0 e 23
            int randomMinute = random.nextInt(60); // Minuto aleatório entre 0 e 59
            int randomSecond = random.nextInt(60); // Segundo aleatório entre 0 e 59
            int randomMillisecond = random.nextInt(1000); // Milissegundo aleatório entre 0 e 999

            // Gets the IP address of the "wlo" network interface or Windows equivalent
            String localIP = new localIp().getLocalIPAddress(new String[]{"wlo", "Wi-Fi", "WLAN"});

            // Formata o horário aleatório para exibição
            String currentTime = String.format("%02d:%02d:%02d.%03d", randomHour, randomMinute, randomSecond, randomMillisecond);
            System.out.println("Horário aleatório antes da sincronização: " + currentTime);

            long start_time = System.currentTimeMillis();
            // Conectando à fábrica remota via RMI Registry
            TimeServiceFactory factory = (TimeServiceFactory) Naming.lookup("rmi://localhost/TimeServiceFactory");
            
            // Solicitando uma nova instância do serviço de tempo
            TimeService timeService = factory.createNewTimeService();
            // Invoca o método remoto e obtém o tempo atualizado do servidor
            String synchronizedTime = timeService.getCurrentTime(localIP, currentTime);

            long end_time = System.currentTimeMillis();

            long rtt = end_time - start_time;
            
            LocalTime time = LocalTime.parse(synchronizedTime, DateTimeFormatter.ofPattern("HH:mm:ss.SSS"));
            LocalTime adjustedTime = time.plusNanos( rtt  * 1000000);

            // Exibe o horário sincronizado recebido do servidor
            System.out.println("Horário atualizado do servidor após sincronização: " + synchronizedTime);
            System.out.println("Horário atualizado do servidor após sincronização com reajuste: " + adjustedTime);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
