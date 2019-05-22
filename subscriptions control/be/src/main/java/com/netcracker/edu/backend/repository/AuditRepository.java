package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Audit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditRepository extends CrudRepository<Audit, Long> {
    @Query(value = "select * from audit a where a.user_id = ?1 order by a.date", nativeQuery = true)
    List<Audit> getUserHistory(long id);

    @Query(value = "select * from audit where user_id = ?1 and date between ?2 and ?3", nativeQuery = true)
    List<Audit> searchHistory(long id, String from, String to);
}
