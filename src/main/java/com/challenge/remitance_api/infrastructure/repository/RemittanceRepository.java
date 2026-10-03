package com.challenge.remitance_api.infrastructure.repository;

import com.challenge.remitance_api.infrastructure.repository.entity.RemittanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RemittanceRepository extends JpaRepository<RemittanceEntity, Integer> {

   @Query("SELECT SUM(r.value) from person p" +
           " join wallet w on p.id = w.personId" +
           " join remittance r on w.id = r.debitorWalletId" +
           " group by cast (r.remittanceDate as date)")
   Double findDailyTotalByPersonId(Integer personId);
}
