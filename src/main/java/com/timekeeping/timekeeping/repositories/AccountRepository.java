package com.timekeeping.timekeeping.repositories;

import com.timekeeping.timekeeping.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByUsernameAndPassword(String username, String password);
    Optional<Account> findByUsername(String username);
    @Query("SELECT a FROM Account a JOIN FETCH a.role")
    List<Account> findAllWithRoles();
}
