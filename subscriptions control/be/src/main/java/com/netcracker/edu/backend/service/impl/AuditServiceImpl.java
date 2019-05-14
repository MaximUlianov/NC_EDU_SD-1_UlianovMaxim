package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Audit;
import com.netcracker.edu.backend.entity.Response;
import com.netcracker.edu.backend.repository.AuditRepository;
import com.netcracker.edu.backend.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditServiceImpl implements AuditService {

    private AuditRepository auditRepository;

    @Autowired
    public AuditServiceImpl(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    @Override
    public Response addRecord(Audit audit) {
        auditRepository.save(audit);
        return new Response("ok");
    }

    @Override
    public Response cleanHistory() {
        auditRepository.deleteAll();
        return new Response("ok");
    }
}
