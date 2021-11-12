package VanquishP2.Application.Beans.ModelServices;

import VanquishP2.Application.Beans.Repos.LoggerRepo;
import VanquishP2.Application.Beans.Models.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;

@Service
@Transactional
public class LoggerService {
    private final LoggerRepo repo;

    @Autowired
    public LoggerService(LoggerRepo repo) {
        this.repo = repo;
    }

    public void writeLog(String message, int level) {
        Logger logger = new Logger(getCurrentDateTime(), formatLogEntry(message), level);

        repo.save(logger);

    }

    private String getCurrentDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return formatter.format(System.currentTimeMillis());
    }

    private String formatLogEntry(String message){
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        String stackTrace = stackTraceElements[stackTraceElements.length - 1].toString();

        return String.format("[%s]  %s%n", stackTrace, message);
    }
}