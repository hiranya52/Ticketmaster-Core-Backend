package edu.icet.ticketmaster.repository;

import edu.icet.ticketmaster.model.entity.AuditLog;
import org.hibernate.boot.jaxb.mapping.spi.JaxbPersistentAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, Integer> {
}
