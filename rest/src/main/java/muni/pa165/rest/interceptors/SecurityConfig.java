package muni.pa165.rest.interceptors;

import muni.pa165.api.dto.UserResponseDTO;
import muni.pa165.api.facade.UserFacade;
import muni.pa165.persistence.enums.UserType;
import muni.pa165.rest.config.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserFacade userFacade;
    @Autowired
    JWTFilter jwtTokenFilter;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(email -> {
            UserResponseDTO userDTO = userFacade.findUserByEmail(email);
            if (userDTO.getEmail().isEmpty()){
                throw new UsernameNotFoundException("not a valid user");
            }
            System.out.println("In SecurityConfig.");
            List<Roles> roles = userDTO.getType() == UserType.MANAGER? List.of(new Roles(Roles.MANAGER),new Roles(Roles.TENNIS_USER)):List.of(new Roles(Roles.TENNIS_USER));
            return new User(userDTO.getEmail(), userDTO.getPassword(), roles);
        });
    }

    // Help from https://www.toptal.com/spring/spring-security-tutorial
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http = http.cors().and().csrf().disable();
        http = http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        http = http
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> {
                            response.sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED,
                                    ex.getMessage()
                            );
                        }
                )
                .and();
        System.out.println("In http SecurityConfig.");

        http.authorizeRequests()
                .antMatchers("/user/*").hasAuthority(Roles.MANAGER)
                .antMatchers(HttpMethod.POST,"/court/*").hasAuthority(Roles.MANAGER)
                .antMatchers(HttpMethod.DELETE,"/court/*").hasAuthority(Roles.MANAGER)
                .antMatchers(HttpMethod.PUT,"/court/*").hasAuthority(Roles.MANAGER)
                .antMatchers(HttpMethod.GET,"/court/*").hasAnyAuthority(Roles.MANAGER,Roles.TENNIS_USER)
                .antMatchers("/event/*").hasAnyAuthority(Roles.MANAGER,Roles.TENNIS_USER)
                .antMatchers("/auth/*").permitAll();
        http.addFilterBefore(jwtTokenFilter,UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
