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

import static org.springframework.security.config.Customizer.withDefaults;
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

        http.cors(withDefaults());
        http.csrf(csrf -> csrf.disable());
		http.authenticationManager(authenticationManager);
        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeHttpRequests(requests -> requests
                .antMatchers("/api/authentication/sign-in", "/api/authentication/sign-up").permitAll()
                .antMatchers("/swagger-ui/index.html", "/swagger-ui/**").permitAll()
                .antMatchers(HttpMethod.POST, "/authentication/login").permitAll()
                .antMatchers(HttpMethod.GET, "/api/recordatorio/todos").permitAll()
                .antMatchers(HttpMethod.GET, "/api/recordatorio/usuario/*").permitAll()
                .antMatchers(HttpMethod.PATCH, "/api/recordatorio/actualizar").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/recordatorio/eliminar/*").permitAll()
                .antMatchers(HttpMethod.POST, "/api/recordatorio/crear").permitAll()
                .antMatchers("/cursos/**")//El resto se requiere authenticacion
                .hasRole(Role.ADMIN.name()) //Y con role admin
                .anyRequest()
                .authenticated());

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
