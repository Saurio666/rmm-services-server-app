/**
 * 
 */
package rmm.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author usr
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityApp extends WebSecurityConfigurerAdapter {
	// Create 2 users for demo
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER")
                .and()
                .withUser("admin").password("{noop}password").roles("USER", "ADMIN");

    }
    
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http
         //HTTP Basic authentication
         .httpBasic()
         .and()
         .authorizeRequests()
         .antMatchers(HttpMethod.GET, "/rmm/**").hasRole("USER")
         .antMatchers(HttpMethod.POST, "/rmm").hasRole("ADMIN")
         .antMatchers(HttpMethod.PUT, "/rmm/**").hasRole("ADMIN")
         .antMatchers(HttpMethod.PATCH, "/rmm/**").hasRole("ADMIN")
         .antMatchers(HttpMethod.DELETE, "/rmm/**").hasRole("ADMIN")
         .and()
         .csrf().disable()
         .formLogin().disable();
	}

//	@Bean
//	public UserDetailsService userDetailsService() {
//		User.UserBuilder users = User.withDefaultPasswordEncoder();
//		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//		
//		manager.createUser(users.username("user").password("password").roles("USER").build());
//        manager.createUser(users.username("admin").password("password").roles("USER", "ADMIN").build());
//        return manager;
//	}
		
}
