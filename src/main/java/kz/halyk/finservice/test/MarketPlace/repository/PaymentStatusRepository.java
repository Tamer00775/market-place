package kz.halyk.finservice.test.MarketPlace.repository;

import kz.halyk.finservice.test.MarketPlace.entity.PaymentDetails;
import kz.halyk.finservice.test.MarketPlace.entity.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentStatusRepository extends JpaRepository<PaymentStatus, Long> {
}