package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Audit;
import com.netcracker.edu.backend.entity.Response;

public interface AuditService {

    Response addRecord(Audit audit);
    Response cleanHistory();
}
