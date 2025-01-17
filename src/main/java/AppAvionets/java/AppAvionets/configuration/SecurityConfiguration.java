package AppAvionets.java.AppAvionets.configuration;

import static org.springframework.security.config.Customizer.withDefaults;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import AppAvionets.java.AppAvionets.security.JpaUserDetailsService;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{

    @Value("${api-endpoint}")
    String endpoint;

    private JpaUserDetailsService jpaUserDetailService;

    public SecurityConfiguration(JpaUserDetailsService userDetailsService){
        this.jpaUserDetailService = userDetailsService;
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
                                .requestMatchers(HttpMethod.POST, endpoint + "register").permitAll()
                                .requestMatchers(endpoint + "/login").hasAnyRole("USER", "ADMIN")//minimum principles //USER or CLIENT¿?
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

    /*
     * @Bean
     * public InMemoryUserDetailsManager userDetailsManager() {
     *
     * UserDetails mickey = User.builder()
     * .username("mickey")
     * .password("{noop}mouse")
     * .roles("ADMIN")
     * .build();
     *
     * UserDetails minnie = User.builder()
     * .username("minnie")
     * .password("{noop}mouse")
     * .roles("USER")
     * .build();
     *
     * Collection<UserDetails> users = new ArrayList<>();
     * users.add(mickey);
     * users.add(minnie);
     *
     * return new InMemoryUserDetailsManager(users);
     * }
     */
}
