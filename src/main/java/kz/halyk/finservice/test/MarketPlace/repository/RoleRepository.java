package kz.halyk.finservice.test.MarketPlace.repository;

import kz.halyk.finservice.test.MarketPlace.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
