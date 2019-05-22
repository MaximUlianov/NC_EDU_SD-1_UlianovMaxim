package com.netcracker.edu.backend.controller;


import com.netcracker.edu.backend.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/audit")
public class AuditController {

    private AuditService auditService;

    @Autowired
    public AuditController(AuditService auditService){
        this.auditService = auditService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity getUserHistory(@RequestParam(value = "id") long id){
        return ResponseEntity.ok(auditService.getUserHistory(id));
    }

    @GetMapping(value = "/search")
    @ResponseBody
    public ResponseEntity searchHistory(@RequestParam(value = "id") long id,
                                        @RequestParam(value = "from") String from,
                                        @RequestParam(value = "to") String to){

        return ResponseEntity.ok(auditService.searchHistory(id, from, to));
    }

}
