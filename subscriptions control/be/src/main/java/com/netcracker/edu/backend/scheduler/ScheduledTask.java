package com.netcracker.edu.backend.scheduler;

import com.netcracker.edu.backend.entity.Audit;
import com.netcracker.edu.backend.entity.Subscription;
import com.netcracker.edu.backend.entity.Wallet;
import com.netcracker.edu.backend.repository.WalletRepository;
import com.netcracker.edu.backend.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ScheduledTask {

    private WalletRepository walletRepository;
    private AuditService auditService;
    @Autowired
    public ScheduledTask(WalletRepository walletRepository, AuditService auditService){
        this.walletRepository = walletRepository;
        this.auditService = auditService;
    }

    @Scheduled(fixedDelay = 1000*10)
    public void getMoney(){
        List<Wallet> list = (List<Wallet>) walletRepository.findAll();
        list.forEach(value->{
            if(value.getSubscriptions().size() != 0){
                double sum = 0;
                for (Subscription o:value.getSubscriptions()) {
                    sum += o.getCostPerMonth();
                }
                if(!value.isLocked()) {
                    value.setSum(value.getSum() - (sum / 30));
                }
                if(value.getSum() < 0){
                    value.setNegBalance(true);
                    Audit audit = new Audit();
                    audit.setUser(value.getUser());
                    audit.setData("Wallet: " + value.getWalletName() + " has neg balance");
                    audit.setDate(new Date());
                    auditService.addRecord(audit);
                }
                else{
                    value.setNegBalance(false);
                    Audit audit = new Audit();
                    audit.setUser(value.getUser());
                    audit.setData("Wallet: " + value.getWalletName() + " has pos balance");
                    audit.setDate(new Date());
                    auditService.addRecord(audit);
                }
                if(value.getSum() < 0 && !value.isCashSub()){
                    value.setLocked(true);
                    Audit audit = new Audit();
                    audit.setUser(value.getUser());
                    audit.setData("Wallet: " + value.getWalletName() + " blocked");
                    audit.setDate(new Date());
                    auditService.addRecord(audit);
                }
                else if(value.getSum() >= 0){
                    value.setLocked(false);
                    Audit audit = new Audit();
                    audit.setUser(value.getUser());
                    audit.setData("Wallet: " + value.getWalletName() + " unblocked");
                    audit.setDate(new Date());
                    auditService.addRecord(audit);
                }
                walletRepository.save(value);
            }
        });
    }

    @Scheduled(fixedDelay = 1000*60*15)
    public void cleanAudit(){
        auditService.cleanHistory();
    }
}
