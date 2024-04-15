package library.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((auth) -> auth.requestMatchers("/api/**").permitAll().anyRequest().permitAll())
               // .authorizeHttpRequests((auth) -> auth
                //        .requestMatchers("/ui/**").authenticated()
                //        .anyRequest().denyAll())
                .formLogin(Customizer.withDefaults())
                .csrf((csrf) -> csrf.ignoringRequestMatchers("/**"));

        return http.build();
    }
}