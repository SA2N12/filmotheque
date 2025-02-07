package fr.eni.tp.filmotheque.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.Customizer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
            .authorizeHttpRequests((authorize) -> 
                authorize
                    .requestMatchers("/").permitAll()
                    .requestMatchers("/css/*").permitAll()
                    .requestMatchers("/img/*").permitAll()
                    .requestMatchers("/login").permitAll()
                    .requestMatchers( "/films").permitAll()
                    .requestMatchers( "/films/detail").permitAll()
                    .requestMatchers("/creer").hasRole("ADMIN")
                    .anyRequest().denyAll()
            )
            .httpBasic(Customizer.withDefaults())
			.formLogin((formLogin) ->
                formLogin
                    .loginPage("/login")
                    .defaultSuccessUrl("/")
            )
            .logout((logout) ->
            logout
                .invalidateHttpSession(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                .logoutSuccessUrl("/")
            )
            ;
        return http.build();
    }

    @Bean
    UserDetailsManager users(DataSource dataSource){
        JdbcUserDetailsManager userManager = new JdbcUserDetailsManager(dataSource);

        userManager.setUsersByUsernameQuery("select email, password, 'true' as enabled from membre where email = ?");
        userManager.setAuthoritiesByUsernameQuery("select membre.email,roles.role from membre JOIN roles ON membre.admin=roles.is_admin where membre.email=?");

        return userManager;
    }


}
