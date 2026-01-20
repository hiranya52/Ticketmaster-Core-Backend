package edu.icet.ticketmaster.aspect;

import edu.icet.ticketmaster.model.entity.AuditLog;
import edu.icet.ticketmaster.repository.AuditLogRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class AuditAspect {

    @Autowired
    private AuditLogRepository auditLogRepository;

    @AfterThrowing(pointcut = "@annotation(AuditFailure)", throwing = "ex")
    public void auditFailure(JoinPoint joinPoint, Exception ex) {

        Object[] args = joinPoint.getArgs();

        int userId = 0;

        for (Object arg : args) {
            if (arg instanceof Integer) {
                userId = (Integer) arg;
                break;
            }
        }

        AuditLog log = new AuditLog();
        log.setUserId(userId);
        log.setTimestamp(LocalDateTime.now());
        log.setReason(ex.getMessage());

        auditLogRepository.save(log);
    }
}
