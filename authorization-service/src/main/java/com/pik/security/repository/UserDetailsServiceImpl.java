package com.pik.security.repository;

import com.pik.domain.SecurityUser;
import com.pik.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final SecurityUserRepository securityUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SecurityUser user = securityUserRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User does not found with -> username or email : " + username));
        return new UserPrincipal(user);
    }
}
