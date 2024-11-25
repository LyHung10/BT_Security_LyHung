package vn.iotstart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import vn.iotstart.entity.UserInfo;
import vn.iotstart.repository.UserInfoRepository;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	UserInfoRepository repository;

	// Authentication
	@Bean
	UserDetailsService userDetailsService() {
	    return new UserInfoService(repository);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	 @Bean
	    CommandLineRunner initDatabase(UserInfoRepository repository, PasswordEncoder encoder) {
	        return args -> {
	            if (repository.findByName("trung").isEmpty()) {
	                UserInfo user = new UserInfo();
	                user.setName("trung");
	                user.setEmail("trung@example.com");
	                user.setPassword(encoder.encode("123")); // Mã hóa mật khẩu
	                user.setRoles("ROLE_ADMIN");
	                repository.save(user);
	            }
	        };
	    }

	@Bean
	AuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
	    authenticationProvider.setUserDetailsService(userDetailsService());
	    authenticationProvider.setPasswordEncoder(passwordEncoder());
	    return authenticationProvider;
	}

	// Security 6.1+
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    return http.csrf(csrf -> csrf.disable())
	            .authorizeHttpRequests(auth -> auth
	                    .requestMatchers("/user/new").permitAll()
	                    .requestMatchers("/").permitAll()
	                    .requestMatchers("/customer/**").authenticated()
	                    //.anyRequest().authenticated()
	            )
	            .formLogin(Customizer.withDefaults())
	            .build();
	}
}
