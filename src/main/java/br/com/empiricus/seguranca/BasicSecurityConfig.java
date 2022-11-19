package br.com.empiricus.seguranca;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class BasicSecurityConfig {

	@Bean // --> é um objeto que é instanciado e gerenciado pelo Spring.
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable().cors();

		http.authorizeHttpRequests((auth) -> auth.antMatchers("/usuarios/PF/logar/cpf").permitAll()
				.antMatchers("/usuarios/PF/cadastrar/cpf").permitAll().antMatchers("/usuarios/PJ/logar/cnpj").permitAll()
				.antMatchers("/usuarios/PJ/cadastrar/cnpj").permitAll().antMatchers(HttpMethod.OPTIONS).permitAll()
				.anyRequest().authenticated()).httpBasic();

		return http.build();
	}
}
