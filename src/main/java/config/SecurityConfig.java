package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import service.ClientService;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String ADMIN_ENDPOINT = "/admin/**";
    private static final String LOGIN_ENDPOINT = "/login";
    private static final String REGISTER_ENDPOINT = "/register";
    private static final String PEOPLE_ENDPOINT = "/people/**";


    private ClientService clientService;

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
    http
//           .httpBasic().disable()
            .csrf().disable()
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
//            .and()
            .authorizeRequests()
            .antMatchers(REGISTER_ENDPOINT).not().fullyAuthenticated()
            //.antMatchers(LOGIN_ENDPOINT).anonymous()
            .antMatchers(ADMIN_ENDPOINT).hasRole("ADMIN")
            .antMatchers(PEOPLE_ENDPOINT).hasRole("USER")
            .antMatchers("/", "/resources/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login").permitAll()
            //.loginProcessingUrl("/login")
            //.failureUrl("/login?error")
            .defaultSuccessUrl("/")
            .and()
            .logout()
            .permitAll()
            .logoutSuccessUrl("/");
    }

}
