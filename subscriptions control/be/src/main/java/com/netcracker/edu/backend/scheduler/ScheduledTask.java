package com.netcracker.edu.backend.scheduler;

import com.netcracker.edu.backend.entity.Subscription;
import com.netcracker.edu.backend.entity.Wallet;
import com.netcracker.edu.backend.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduledTask {

    private WalletRepository walletRepository;

    @Autowired
    public ScheduledTask(WalletRepository walletRepository){
        this.walletRepository = walletRepository;
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
                }
                else{
                    value.setNegBalance(false);
                }
                if(value.getSum() < 0 && !value.isCashSub()){
                    value.setLocked(true);
                }
                else if(value.getSum() >= 0){
                    value.setLocked(false);
                }
                walletRepository.save(value);
            }
        });
    }
}
