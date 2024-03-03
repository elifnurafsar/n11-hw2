package com.MavenOrderCraft.MavenOrderCraft.Repository;

import com.MavenOrderCraft.MavenOrderCraft.Entitiy.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {

    List<Invoice> findByUserId(UUID userId);

    List<Invoice> findByCreatedAtBetween(LocalDateTime atStartOfDay, LocalDateTime atTime);

    List<Invoice> findByTotalAmountGreaterThan(BigDecimal amount);

    List<Invoice> findByTotalAmountLessThan(BigDecimal amount);

    List<Invoice> findByUserCreatedAtBetween(LocalDateTime atStartOfDay, LocalDateTime atTime);
}