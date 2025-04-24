package com.carbontracker.backend.security;

import com.carbontracker.backend.entity.Login;
import com.carbontracker.backend.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.DisabledException;

import java.util.Collections;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Login login = loginRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));

        if (!"ACTIVE".equalsIgnoreCase(login.getAccountStatus())) {
            throw new DisabledException("Account is not active");
        }

        return new User(
                login.getUsername(),
                login.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + login.getRole()))
        );
    }
}
