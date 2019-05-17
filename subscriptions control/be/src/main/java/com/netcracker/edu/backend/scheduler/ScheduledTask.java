package com.netcracker.edu.backend.scheduler;

import com.netcracker.edu.backend.entity.Audit;
import com.netcracker.edu.backend.entity.Subscription;
import com.netcracker.edu.backend.entity.Wallet;
import com.netcracker.edu.backend.repository.SubscriptionRepository;
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
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    public ScheduledTask(WalletRepository walletRepository, AuditService auditService, SubscriptionRepository subscriptionRepository) {
        this.walletRepository = walletRepository;
        this.auditService = auditService;
        this.subscriptionRepository = subscriptionRepository;
    }




    @Scheduled(fixedDelay = 1000*10)
    public void getMoney(){
        List<Wallet> list = (List<Wallet>) walletRepository.findAll();
        list.forEach(value->{
            if(value.getSubscriptions().size() != 0){
                for (Subscription o:value.getSubscriptions()) {
                    double subtract = (o.getProduct().getCostPerMonth()- (o.getProduct().getCostPerMonth()/100*o.getSale()))/30;
                    if(value.getSum() - subtract > 0){
                        value.setSum(value.getSum() - subtract);
                        o.setLocked(false);
                        Audit audit = new Audit();
                        audit.setUser(value.getUser());
                        audit.setData("Wallet: " + value.getWalletName() + " unblocked");
                        audit.setDate(new Date());
                        walletRepository.save(value);
                        auditService.addRecord(audit);
                        subscriptionRepository.save(o);
                    }
                    else{
                        o.setLocked(true);
                        subscriptionRepository.save(o);
                        Audit audit = new Audit();
                        audit.setUser(value.getUser());
                        audit.setData("Wallet: " + value.getWalletName() + " blocked");
                        audit.setDate(new Date());
                        auditService.addRecord(audit);
                    }
                }

            }
        });
    }

    @Scheduled(fixedDelay = 1000*60*15)
    public void cleanAudit(){
        auditService.cleanHistory();
    }
}
