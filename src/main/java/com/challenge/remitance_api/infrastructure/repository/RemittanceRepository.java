package com.challenge.remitance_api.infrastructure.repository;

import com.challenge.remitance_api.infrastructure.repository.entity.RemittanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RemittanceRepository extends JpaRepository<RemittanceEntity, Integer> {

   @Query("SELECT COALESCE(SUM(r.value), 0) from person p" +
           " join wallet w on p.id = w.personId and w.personId =:personId" +
           " left join remittance r on w.id = r.debitorWalletId" +
           " and cast(r.remittanceDate as date) = CURRENT_DATE" +
           " group by cast (r.remittanceDate as date)")
   Double findDailyTotalByPersonId(Integer personId);
}
