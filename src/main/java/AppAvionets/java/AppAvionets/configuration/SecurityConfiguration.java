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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
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
                                .requestMatchers(HttpMethod.POST, endpoint + "/airport").hasRole("ADMIN") //.hasAuthority
                                .requestMatchers(HttpMethod.POST, endpoint + "/flights").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, endpoint + "/reservations").hasAnyRole("USER", "ADMIN")
                                .requestMatchers(HttpMethod.GET, endpoint + "/flights").hasAnyRole("ADMIN")
                                .anyRequest().authenticated())
                .userDetailsService(jpaUserDetailService)
                .httpBasic(withDefaults())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));

        http.headers(header -> header.frameOptions(frame -> frame.sameOrigin()));

        //23.1.25: Amr
        http.addFilterBefore((request, response, chain) -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                // debug: Log the authorities
                System.out.println("Authenticated User: " + authentication.getName());
                System.out.println("User Authorities: " + authentication.getAuthorities());

            }
            chain.doFilter(request, response);  // Continue the filter chain
        }, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
