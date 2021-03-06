package Application.services;

import Application.models.Logger;
import Application.repositories.LoggerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;

/**
 * LoggerService
 * Handles logging error information to the database
 *
 * @author Kollier Martin
 * @date 11/10/2021
 */
@Service
@Transactional
public class LoggerService {
    private final LoggerRepo repo;

    @Autowired
    public LoggerService(LoggerRepo repo) {
        this.repo = repo;
    }

    public void writeLog(String message, int level) {
        Logger logger = new Logger(getCurrentDateTime(), message, level);

        repo.save(logger);
    }

    private String getCurrentDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(System.currentTimeMillis());
    }
}
