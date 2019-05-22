package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Audit;
import com.netcracker.edu.backend.entity.Response;
import com.netcracker.edu.backend.entity.User;
import com.netcracker.edu.backend.repository.AuditRepository;
import com.netcracker.edu.backend.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
    public Response subscribedRecord(User user, String name) {
        Audit audit = new Audit();
        audit.setUser(user);
        audit.setData("Subscribed to: " + name);
        audit.setDate(new Date());
        addRecord(audit);
        return new Response("ok");
    }

    @Override
    public Response unsubscribedRecord(User user, String name) {
        Audit audit = new Audit();
        audit.setUser(user);
        audit.setData("Unsubscribed from: " + name);
        audit.setDate(new Date());
        addRecord(audit);
        return new Response("ok");
    }

    @Override
    public Response expiredRecord(User user, String name) {
        Audit audit = new Audit();
        audit.setUser(user);
        audit.setData("Subscription: " + name + " has expired");
        audit.setDate(new Date());
        addRecord(audit);
        return new Response("ok");
    }

    @Override
    public Response subscriptBlockedStatus(User user, String name) {
        Audit audit = new Audit();
        audit.setUser(user);
        audit.setData("Subscription: " + name + " blocked");
        audit.setDate(new Date());
        addRecord(audit);
        return new Response("ok");
    }

    @Override
    public Response subscriptOkStatus(User user, String name) {
        Audit audit = new Audit();
        audit.setUser(user);
        audit.setData("Subscription: " + name + " status ok");
        audit.setDate(new Date());
        addRecord(audit);
        return new Response("ok");
    }

    @Override
    public Response addWalletRecord(User user, String name) {
        Audit audit = new Audit();
        audit.setUser(user);
        audit.setData("Add wallet " + name);
        audit.setDate(new Date());
        addRecord(audit);
        return new Response("ok");
    }

    @Override
    public Response deleteWalletRecord(User user, String name) {
        Audit audit = new Audit();
        audit.setUser(user);
        audit.setData("Delete wallet " + name);
        audit.setDate(new Date());
        addRecord(audit);
        return new Response("ok");
    }

    @Override
    public Response rechargeWalletRecord(User user, String name, double sum) {
        Audit audit = new Audit();
        audit.setUser(user);
        audit.setData("Recharge wallet " + name + " on " + sum);
        audit.setDate(new Date());
        addRecord(audit);
        return new Response("ok");
    }

    @Override
    public Response cleanHistory() {
        auditRepository.deleteAll();
        return new Response("ok");
    }

    @Override
    public List<Audit> getUserHistory(long id) {
        return auditRepository.getUserHistory(id);
    }

    @Override
    public List<Audit> searchHistory(long id, String from, String to) {
        int fm = from.indexOf("_");
        int fd = from.lastIndexOf("_");
        int tm = to.indexOf("_");
        int td = to.lastIndexOf("_");
        String _from = from.substring(0, fm) + '-' + from.substring(fm + 1, fd) + '-' + from.substring(fd + 1);
        String _to = to.substring(0, tm) + '-' + to.substring(tm + 1, td) + '-' + to.substring(td + 1);
        return auditRepository.searchHistory(id, _from, _to);
    }
}
