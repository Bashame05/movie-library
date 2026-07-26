package com.makar.movielibrary.security;

import com.makar.movielibrary.model.User;
import com.makar.movielibrary.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User tempUser = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Error: Username not found"));
        return new UserPrincipal(tempUser);
    }
}
