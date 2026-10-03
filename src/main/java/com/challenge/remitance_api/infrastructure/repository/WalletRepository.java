package com.challenge.remitance_api.infrastructure.repository;

import com.challenge.remitance_api.infrastructure.repository.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WalletRepository extends JpaRepository<WalletEntity, Integer> {

   @Query("SELECT w.reaisBalance FROM wallet w WHERE w.personId = :personId")
   Double findReaisBalanceByPersonId(Integer personId);

}
