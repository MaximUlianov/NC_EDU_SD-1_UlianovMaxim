package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Audit;
import com.netcracker.edu.backend.entity.Response;
import com.netcracker.edu.backend.entity.User;

public interface AuditService {

    Response addRecord(Audit audit);
    Response subscribedRecord(User user, String name);
    Response unsubscribedRecord(User user,String name);
    Response expiredRecord(User user,String name);
    Response subscriptBlockedStatus(User user,String name);
    Response subscriptOkStatus(User user,String name);
    Response addWalletRecord(User user,String name);
    Response deleteWalletRecord(User user,String name);
    Response rechargeWalletRecord(User user,String name, double sum);
    Response cleanHistory();
}
