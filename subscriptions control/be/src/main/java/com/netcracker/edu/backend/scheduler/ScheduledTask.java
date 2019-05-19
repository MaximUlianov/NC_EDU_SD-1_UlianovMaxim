package com.netcracker.edu.backend.scheduler;

import com.netcracker.edu.backend.entity.Subscription;
import com.netcracker.edu.backend.entity.Wallet;
import com.netcracker.edu.backend.repository.CompanyRepository;
import com.netcracker.edu.backend.repository.SubscriptionRepository;
import com.netcracker.edu.backend.repository.WalletRepository;
import com.netcracker.edu.backend.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ScheduledTask {

    private WalletRepository walletRepository;
    private AuditService auditService;
    private SubscriptionRepository subscriptionRepository;
    private CompanyRepository companyRepository;

    @Autowired
    public ScheduledTask(WalletRepository walletRepository, AuditService auditService, SubscriptionRepository subscriptionRepository, CompanyRepository companyRepository) {
        this.walletRepository = walletRepository;
        this.auditService = auditService;
        this.subscriptionRepository = subscriptionRepository;
        this.companyRepository = companyRepository;
    }




    @Scheduled(fixedDelay = 1000*10)
    public void getMoney(){
        List<Wallet> list = (List<Wallet>) walletRepository.findAll();
        list.forEach(value->{
            if(value.getSubscriptions().size() != 0){
                for (Subscription o:value.getSubscriptions()) {
                    double subtract = (o.getProduct().getCostPerMonth()- (o.getProduct().getCostPerMonth()/100*o.getSale()))/30;
                    if(o.getStart().equals(LocalDate.now()) || (o.getEnd().isAfter(LocalDate.now()) && o.getStart().isBefore(LocalDate.now())) ) {
                        if (value.getSum() - subtract > 0) {
                            value.setSum(value.getSum() - subtract);
                            o.getProduct().getCompany().setProceeds(o.getProduct().getCompany().getProceeds() + subtract);
                            o.setLocked(false);
                            companyRepository.save(o.getProduct().getCompany());
                            walletRepository.save(value);
                            subscriptionRepository.save(o);
                            auditService.subscriptOkStatus(value.getUser(), o.getProduct().getName());
                        }
                        else{
                            o.setLocked(true);
                            subscriptionRepository.save(o);
                            auditService.subscriptBlockedStatus(value.getUser(), o.getProduct().getName());
                        }
                    }
                    else{
                        o.setLocked(true);
                        subscriptionRepository.save(o);
                        auditService.expiredRecord(value.getUser(), o.getProduct().getName());
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
