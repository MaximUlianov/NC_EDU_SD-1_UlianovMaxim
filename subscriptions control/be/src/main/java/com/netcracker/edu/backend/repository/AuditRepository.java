package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Audit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepository extends CrudRepository<Audit, Long> {
}
