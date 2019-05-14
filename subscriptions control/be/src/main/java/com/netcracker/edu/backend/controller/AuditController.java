package com.netcracker.edu.backend.controller;


import com.netcracker.edu.backend.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/audit")
public class AuditController {

    private AuditService auditService;

    @Autowired
    public AuditController(AuditService auditService){
        this.auditService = auditService;
    }

}
