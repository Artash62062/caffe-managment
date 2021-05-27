package com.example.simplecaffemanagerassignment.Security;


import com.example.simplecaffemanagerassignment.Services.user.UserService;
import com.example.simplecaffemanagerassignment.model.User;
import com.example.simplecaffemanagerassignment.repositories.AdminRepository;
import com.example.simplecaffemanagerassignment.repositories.ManagerRepository;
import com.example.simplecaffemanagerassignment.repositories.WaiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    WaiterRepository waiterRepository;

    @Autowired
    AdminRepository adminRepository;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String login = authentication.getName();

        User user = managerRepository.getByLogin(login);
        if (user == null) {
            user = waiterRepository.getWaiterByLogin(login);
        }
        if (user == null) {
            user = adminRepository.getByLogin(login);
        }

        String password = authentication.getCredentials().toString();

        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Bad credentials");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority grantedAuthority = user::getRole;
        authorities.add(grantedAuthority);
        return new UsernamePasswordAuthenticationToken(user, null, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
