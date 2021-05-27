package com.example.simplecaffemanagerassignment.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
@ComponentScan("com.example.simplecaffemanagerassignment.Security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final MyAuthenticationProvider authenticationProvider;

    public SecurityConfig(MyAuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(
                new LoginPageFilter(), DefaultLoginPageGeneratingFilter.class);
        http
                .authorizeRequests()
                .antMatchers("/").authenticated()
                .antMatchers("/login").anonymous()
                .antMatchers("/register/**").hasAnyRole("ADMIN","MANAGER")
                .antMatchers("/manager/**").hasRole("MANAGER")
                .antMatchers("/waiter/**").hasRole("WAITER")
                .antMatchers("/admin").hasRole("ADMIN")
                .and().exceptionHandling().accessDeniedPage("/login")
                .and()
                .formLogin()
                .loginProcessingUrl("/login/process")
                .usernameParameter("login")
                .successHandler(new MyAuthenticationSuccessHandler())
                .failureUrl("/login?error=true")
                .and()
                .logout().logoutSuccessUrl("/");
    }

    static class LoginPageFilter extends GenericFilterBean {

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException, IOException, ServletException {
            if (SecurityContextHolder.getContext().getAuthentication() != null
                    && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                    && ((HttpServletRequest)request).getRequestURI().equals("/login")) {
                System.out.println("user is authenticated but trying to access login page, redirecting to /");
                ((HttpServletResponse)response).sendRedirect("/");
            }
            chain.doFilter(request, response);
        }

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
