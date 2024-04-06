package kz.halyk.finservice.test.MarketPlace.repository;

import kz.halyk.finservice.test.MarketPlace.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
