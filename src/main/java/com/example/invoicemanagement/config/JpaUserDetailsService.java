package com.example.invoicemanagement.config;

import com.example.invoicemanagement.model.Role;
import com.example.invoicemanagement.model.User;
import com.example.invoicemanagement.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(JpaUserDetailsService.class);

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if(user == null) {
            logger.error("Error login: doesn't existe '"+ email +"'");
            throw new UsernameNotFoundException("Email: " + email + " doesn't existe ");
        }

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for(Role role: user.getRoles()) {
            logger.info("Role: ".concat(role.getRole()));
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }

        if(authorities.isEmpty()) {
            logger.error("Error login: wrong '"+ email +"' doesn't have assigned roles");
            throw new UsernameNotFoundException("Login error: user '"+ email +"' does not have assigned roles");
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),true, true, true, true, authorities);
    }



}