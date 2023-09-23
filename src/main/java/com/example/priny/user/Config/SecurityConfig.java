package com.example.priny.user.Config;


import com.example.priny.user.Service.JwtAuthenticationFilter;
import com.example.priny.user.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final UserService userService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

 //   @Bean
 //   @Override
 //   public AuthenticationManager authenticationManagerBean() throws Exception {
  //      return super.authenticationManagerBean();
   // }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .formLogin().disable()
                .httpBasic().disable()
                .cors()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .antMatchers("/user/login").permitAll()
                .antMatchers("/user/join").permitAll()
                .antMatchers("/resume").hasRole("USER")
                .antMatchers("/resume/**").hasRole("USER")
                .antMatchers("/resumes").permitAll()
                .antMatchers("/scout").permitAll()
                .antMatchers("/idCheck").permitAll()
                .antMatchers("/emailCheck").permitAll()
                .antMatchers("http://localhost:3000").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("user/**").hasRole("ADMIN")
                .antMatchers("/scout/**").permitAll()
                .antMatchers("/apply").hasRole("USER")
                .antMatchers("/post/**").hasRole("ADMIN")
                .antMatchers("/listPost").permitAll()
                .antMatchers("/jobPost").hasRole("ADMIN")
                .antMatchers("/editPost/**").hasRole("ADMIN")
                .antMatchers("/deltePost/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }

    private static final String[] AUTH_WHITELIST = {
            "/v2/api-docs",
            "/v3/api-docs/**",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/file/**",
            "/image/**",
            "/swagger/**",
            "/swagger-ui/**",
            "/h2/**"
    };


@Override
public void configure(WebSecurity webSecurity) throws Exception{
    webSecurity.ignoring().antMatchers(AUTH_WHITELIST);
}


}
