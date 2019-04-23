package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Wallet;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends CrudRepository<Wallet, Long> {
    Wallet findByWalletName(String walletName);
    void deleteByWalletName(String walletName);
    @Modifying
    @Query("update Wallet w set w.sum = ?1 where w.id = ?2")
    void rechargeWallet(double num, long id);
}
