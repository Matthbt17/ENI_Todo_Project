package fr.enitodo.configuration.security;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class EniTodoSecurity {

	protected final Log logger = LogFactory.getLog(getClass());

	@Bean
	public UserDetailsManager userDetailsManager(DataSource dataSource) {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
		jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT pseudo, password,1 FROM UTILISATEUR WHERE pseudo =?");
		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT pseudo, role FROM ROLES WHERE pseudo =?"); 
		return jdbcUserDetailsManager;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> {
			auth.requestMatchers(HttpMethod.GET, "/projet").permitAll()
					.requestMatchers(HttpMethod.GET, "/projet/detail").permitAll()
					.requestMatchers(HttpMethod.GET, "/signin").permitAll()
					.requestMatchers(HttpMethod.POST, "/signin").permitAll()
					.requestMatchers(HttpMethod.GET, "/aboutus").permitAll();
			auth.requestMatchers("/*").permitAll();
			auth.requestMatchers("/css/*").permitAll();
			auth.requestMatchers("/images/*").permitAll();
			auth.anyRequest().denyAll();
		});
		//Customiser le formulaire
		http.formLogin(form -> {
			form.loginPage("/login").permitAll();
			form.defaultSuccessUrl("/aboutus");
			form.failureUrl("/");
			});
		// Logout --> vider la session et le contexte de sécurité
		http.logout(logout -> logout
		.invalidateHttpSession(true)
		.clearAuthentication(true)
		.deleteCookies("JSESSIONID")
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/")
		);
		
		return http.build();
	}

}
