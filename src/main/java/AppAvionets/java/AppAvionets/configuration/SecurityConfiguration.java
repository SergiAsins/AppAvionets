package AppAvionets.java.AppAvionets.configuration;

import static org.springframework.security.config.Customizer.withDefaults;


import AppAvionets.java.AppAvionets.roles.Role;
import AppAvionets.java.AppAvionets.roles.RoleRepository;
import AppAvionets.java.AppAvionets.users.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import AppAvionets.java.AppAvionets.security.JpaUserDetailsService;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{

    @Value("${api-endpoint}")
    String endpoint;

    private final JpaUserDetailsService jpaUserDetailService;

    public SecurityConfiguration(JpaUserDetailsService jpaUserDetailsService){
        this.jpaUserDetailService = jpaUserDetailsService;
    }

    @Bean
    public DefaultSecurityFilterChain filterChain(HttpSecurity http)throws Exception{

        http.cors(withDefaults())
                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form.disable())
                .logout(out -> out
                        .logoutUrl(endpoint + "/logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID"))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                                .requestMatchers(endpoint).permitAll()
                                .requestMatchers(HttpMethod.POST, endpoint + "/register").permitAll()
                                .requestMatchers(endpoint + "/login").hasAnyRole("USER", "ADMIN")
                                .requestMatchers(endpoint + "/public").permitAll()
                                .requestMatchers(endpoint + "/private").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, endpoint + "/flights").hasAnyRole("USER", "ADMIN")
                                .requestMatchers(HttpMethod.POST, endpoint + "/flights").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, endpoint + "/airport").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, endpoint + "/flights").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, endpoint + "/reservations").hasAnyRole("USER", "ADMIN")
                                .requestMatchers(HttpMethod.GET, endpoint + "/flights").hasAnyRole("ADMIN")
                                .anyRequest().authenticated())
                .userDetailsService(jpaUserDetailService)
                .httpBasic(withDefaults())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));

        http.headers(header -> header.frameOptions(frame -> frame.sameOrigin()));


        return http.build();
    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    //add Role_ADMIN and Role_USER initially
    @Bean
    CommandLineRunner initRoles(RoleRepository roleRepository) {
        return args -> {
            Role userRole = new Role("ROLE_USER");
            Role adminRole = new Role("ROLE_ADMIN");

            if (!roleRepository.existsById(1L)) {
                userRole.setId(1L);
                roleRepository.save(userRole);
            }
            if (!roleRepository.existsById(2L)) {
                adminRole.setId(2L);
                roleRepository.save(adminRole);
            }
        };
    }
}
