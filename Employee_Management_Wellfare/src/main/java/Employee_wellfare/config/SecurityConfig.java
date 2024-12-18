package Employee_wellfare.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import Employee_wellfare.service.UserInfoService;



@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@Configuration

public class SecurityConfig {
	
	
	@Bean
	public UserDetailsService userdetailsservice() {
		
		return new UserInfoService();
	}
	
	 @Bean
	    public AuthenticationProvider authenticationProvider(){
	        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
	        authenticationProvider.setUserDetailsService(userdetailsservice());
	        authenticationProvider.setPasswordEncoder(passwordEncoder());
	        return authenticationProvider;
	    }
	 @Bean
		public AuthTokenFilter authTokenFilter() {
			return new AuthTokenFilter();
		}
	 @Bean
		public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
				throws Exception {
			return authenticationConfiguration.getAuthenticationManager();
		}
	 
	 
	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         
		 
		 return http.csrf((csrf) -> csrf.disable())
				 .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
					.authenticationProvider(authenticationProvider())
					.addFilterBefore(authTokenFilter(),UsernamePasswordAuthenticationFilter.class)
				 .authorizeHttpRequests((auth)->{
				 auth.requestMatchers(HttpMethod.POST,"/auth/new").permitAll();
				 auth.requestMatchers(HttpMethod.POST,"/auth/login").permitAll();
				 auth.requestMatchers(HttpMethod.GET,"auth/employee/{empid}").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN"); 
				 auth.requestMatchers(HttpMethod.GET,"auth/employees").hasAuthority("ROLE_ADMIN");
				 auth.anyRequest().authenticated();
				}).build();

	    }

	 
	 @Bean
	 public PasswordEncoder passwordEncoder() {
		 
		 return new BCryptPasswordEncoder();
	 }
	 
}

