package br.motorola.manpower.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebConfigSecurity {

    @Configuration
    public class SecurityConfiguration {

        @Bean
        public UserDetailsService userDetailsService() {
            return new UsuarioConfigServices();
        }

        @Bean
        public BCryptPasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public AuthenticationProvider authProvider() {
            DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
            authProvider.setUserDetailsService(udService());
            authProvider.setPasswordEncoder(passwordEncoder());
            return authProvider;
        }

        @Bean
        public UserDetailsService udService() {
            return new UsuarioConfigServices();
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

            http.httpBasic();
            http.cors();
            // http.formLogin().loginPage("/login").permitAll();
            http
            .formLogin(form -> form
                    .loginPage("/login")
                    .permitAll());
            http.authorizeRequests().antMatchers("/person/**","/area/**", "/user/**", "/team/**").hasAnyRole("ADMIN", "SLT");
            http.authorizeRequests().anyRequest().authenticated();
            http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
            http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK));
            http.csrf().disable();
            return http.build();
        }

        @Bean
        public WebSecurityCustomizer webSecurityCustomizer() {

            return (web) -> web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
        }

    }

}
