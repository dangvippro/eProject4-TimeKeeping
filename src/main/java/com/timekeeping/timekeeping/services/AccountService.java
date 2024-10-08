package com.timekeeping.timekeeping.services;

import com.timekeeping.timekeeping.models.Account;
import com.timekeeping.timekeeping.models.Role;
import com.timekeeping.timekeeping.repositories.AccountRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private EntityManager entityManager;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    public List<Account> findByName(String name) {
        return entityManager.createQuery("FROM Account WHERE fullName LIKE :name", Account.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> accountOptional = accountRepository.findByUsername(username);

        if (accountOptional.isEmpty()) {
            throw new UsernameNotFoundException("Sai tài khoản hoặc mật khẩu.");
        }

        Account account = accountOptional.get();

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");

        return new CustomUserDetails(
                account.getUsername(),
                account.getPassword(),
                account.getEmail(),
                account.getFullName(),
                authority
        );
    }

    public Optional<Account> findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    public Optional<Account> findByUsernameAndPassword(String username, String rawPassword) {
        Optional<Account> accountOptional = accountRepository.findByUsername(username);

        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            if (passwordEncoder.matches(rawPassword, account.getPassword())) {
                return Optional.of(account);
            }
        }

        return Optional.empty();
    }

    public void save(Account account, Role roleId) {
        account.setRole(roleId);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Optional<Account> findById(int accountId) {
        return accountRepository.findById(accountId);
    }

    public void delete(int id) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            account.setStatus("InActive");
            accountRepository.save(account);
        }
    }

    public void turnOn(int id) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            account.setStatus("Active");
            accountRepository.save(account);
        }
    }
}
