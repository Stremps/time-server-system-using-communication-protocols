package TCP;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogToFile {

    public void logFile(String message) {
        String filePath = "log.txt";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = LocalDateTime.now().format(formatter);

        String fullMessage = "[" + timestamp + "] - " + message + System.lineSeparator();

        try (FileWriter writer = new FileWriter(filePath, true)) {  // 'true' for append mode
            writer.write(fullMessage);
            System.out.println("Log saved successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the log.");
            e.printStackTrace();
        }
    }
}
