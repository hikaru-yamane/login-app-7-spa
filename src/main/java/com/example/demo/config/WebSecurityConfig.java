package com.example.demo.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.demo.service.p01_login.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Autowired
	UserDetailsServiceImpl service;
	
	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests()
//			.antMatchers("/user/*").hasRole("ADMIN")
//			.antMatchers("/time/*", "forum/*", "image/*", "movie/*").authenticated();
		http.formLogin()
			.loginProcessingUrl("/authenticate")
				.usernameParameter("email")
				.passwordParameter("password")
			.successHandler(new AuthenticationSuccessHandlerEx())
			.failureHandler(new AuthenticationFailureHandlerEx());
		http.logout()
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID")
			.logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
		http.csrf().disable();
//		http.csrf()
//			.ignoringAntMatchers("/login", "/authenticate")
//			.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
//		http.sessionManagement()
//			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.exceptionHandling()
			.authenticationEntryPoint(new AuthenticationEntryPointEx())
			.accessDeniedHandler(new AccessDeniedHandlerEx());
		http.cors();
		return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(List.of("http://localhost:4200"));
		configuration.setAllowedMethods(List.of("*"));
		configuration.setAllowedHeaders(List.of("*"));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}

//@SuppressWarnings("deprecation")
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	UserDetailsServiceImpl service;
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(service)
//				.passwordEncoder(passwordEncoder());
//	}
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
////		http.authorizeRequests()
////			.antMatchers("/user/*").hasRole("ADMIN")
////			.antMatchers("/time/*", "forum/*", "image/*", "movie/*").authenticated();
//		http.formLogin()
//			.loginProcessingUrl("/login")
//				.usernameParameter("email")
//				.passwordParameter("password")
//			.successHandler(new AuthenticationSuccessHandlerEx())
//			.failureHandler(new AuthenticationFailureHandlerEx());
//		http.logout()
//			.invalidateHttpSession(true)
//			.deleteCookies("JSESSIONID")
//			.logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
//		http.csrf().disable();
////		http.csrf()
////			.ignoringAntMatchers("/authenticate")
////			.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
////		http.sessionManagement()
////			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		http.exceptionHandling()
//			.authenticationEntryPoint(new AuthenticationEntryPointEx())
//			.accessDeniedHandler(new AccessDeniedHandlerEx());
//		http.cors();
//	}
//	
//	@Bean
//	PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	@Bean
//	public CorsConfigurationSource corsConfigurationSource() {
//		CorsConfiguration configuration = new CorsConfiguration();
//		configuration.setAllowedOrigins(List.of("http://localhost:4200"));
//		configuration.setAllowedMethods(List.of("*"));
//		configuration.setAllowedHeaders(List.of("*"));
//		configuration.setAllowCredentials(true);
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", configuration);
//		return source;
//	}
//
//
//}
