package comapigateway.config.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import comapigateway.models.Role;
import comapigateway.security.jwt.JwtAuthorizationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	JwtAuthorizationFilter jwtAuthorizationFilter() {
		return new JwtAuthorizationFilter();
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);

		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);

		AuthenticationManager authenticationManager = auth.build();
		logger.info("Configurando seguridad!   #####################");
		http.cors();
		http.csrf().disable();
		http.authenticationManager(authenticationManager);
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.authorizeHttpRequests()
				.antMatchers("/api/authentication/sign-in", "/api/authentication/sign-up", "/swagger-ui/index.html",
						"/swagger-ui/**")
				.permitAll().antMatchers(HttpMethod.GET, "/gateway/inmueble").permitAll()
				.antMatchers("/gateway/inmueble/**").hasRole(Role.ADMIN.name()).anyRequest().authenticated();

		http.addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}

}
