    package com.timekeeping.timekeeping.services;

    import com.timekeeping.timekeeping.models.Account;
    import com.timekeeping.timekeeping.repositories.AccountRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.core.authority.SimpleGrantedAuthority;
    import org.springframework.security.core.userdetails.User;
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

        private PasswordEncoder passwordEncoder;

        @Autowired
        public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
            this.passwordEncoder = passwordEncoder;
        }

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Optional<Account> accountOptional = accountRepository.findByUsername(username);

            if (accountOptional.isEmpty()) {
                throw new UsernameNotFoundException("Sai tài khoản hoặc mật khẩu.");
            }

            Account account = accountOptional.get();

            return new CustomUserDetails(
                    account.getUsername(),
                    account.getPassword(),
                    account.getEmail(),
                    account.getFullName(),
                    List.of(new SimpleGrantedAuthority("ROLE_USER"))
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

        public void save(Account account, int roleId) {
            account.setRoleId(roleId > 0 ? roleId : 4);
            account.setPassword(passwordEncoder.encode(account.getPassword()));
            accountRepository.save(account);
        }
    }
