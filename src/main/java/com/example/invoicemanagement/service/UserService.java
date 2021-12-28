package com.example.invoicemanagement.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.invoicemanagement.model.Role;
import com.example.invoicemanagement.model.User;
import com.example.invoicemanagement.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    public User save(User registration) {
        System.out.println(registration.toString());
        User user = new User();
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
       // user.setUsername(registration.getUsername());
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        //user.setConfirmPassword(passwordEncoder.encode(registration.getConfirmPassword()));
        user.setEnabled(registration.getEnabled());
        user.setRoles(Arrays.asList(new Role("ROLE_USER")));
        return userRepository.save(user);

    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }
}
//package com.example.invoicemanagement.service;
//
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import com.example.invoicemanagement.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//import com.example.invoicemanagement.model.Role;
//import com.example.invoicemanagement.model.User;
//import com.example.invoicemanagement.repository.UserRepository;
//
//@Service
//public class UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    private BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
//
//    public UserService(UserRepository userRepository) {
//        super();
//        this.userRepository = userRepository;
//    }
//
//    public User save(User registration) {
//        User user = new User();
//        user.setFirstName(registration.getFirstName());
//        user.setLastName(registration.getLastName());
//        user.setEmail(registration.getEmail());
//        user.setPassword(passwordEncoder.encode(registration.getPassword()));
//        user.setRoles(Arrays.asList(new Role("ROLE_USER")));
//        user.setEnabled(registration.getEnabled());
//        System.out.println(user.toString());
//        return userRepository.save(user);
//    }
//
//    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
//
//        User user = userRepository.findByEmail(email);
//        if (user == null) {
//            throw new UsernameNotFoundException("Invalid username or password.");
//        }
//        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
//                mapRolesToAuthorities(user.getRoles()));
//    }
//
//    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
//        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
//    }
//
//    public List<User> getAll() {
//        return userRepository.findAll();
//    }
//
//    public User findByEmail(String email) {
//        return userRepository.findByEmail(email);
//    }
//
//}
