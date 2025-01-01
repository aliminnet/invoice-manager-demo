package com.ali.min.invoicemanager.repository;

import com.ali.min.invoicemanager.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;


public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {
    @Query("SELECT CASE WHEN (COALESCE(SUM(i.amount), 0) + :newValue) <= :limit THEN TRUE ELSE FALSE END FROM InvoiceEntity i WHERE i.email = :email")
    boolean isWithinThreshold(@Param("newValue") BigDecimal newValue, @Param("limit") BigDecimal limit, @Param("email") String email);
}
