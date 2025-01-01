package com.ali.min.invoicemanager.repository;

import com.ali.min.invoicemanager.entity.ActivityEntity;
import com.ali.min.invoicemanager.entity.ProductEntity;
import com.ali.min.invoicemanager.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ActivityRepository extends JpaRepository<ActivityEntity, Long> {
    List<ActivityEntity> findAllByStatus(Status status);
}
