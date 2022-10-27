package com.scaler.ems.service;

import com.scaler.ems.entities.Role;
import com.scaler.ems.entities.User;
import com.scaler.ems.model.UserBo;
import com.scaler.ems.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserDetailServiceImpl implements UserDetailsService, EmsUserService {

    private UserRepository userRepository;


    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        User u = user.orElseThrow(() -> {
            throw new UsernameNotFoundException(username);
        });
        return new org.springframework.security.core.userdetails.User(u.getEmail(), u.getPassword(), mapRolesToAuthorities(u.getRoles()));

    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public User save(UserBo userbo) {
        User user = new User();
        user.setFirstName(userbo.getFirstName());
        user.setLastName(userbo.getLastName());
        user.setEmail(userbo.getEmail());
        user.setPassword(passwordEncoder.encode(userbo.getPassword()));
        user.setRoles(List.of(new Role(userbo.getRole())));
        return userRepository.save(user);
    }
}
