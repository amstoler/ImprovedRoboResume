package com.example.demo.Security;

import com.example.demo.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return new SSUDS(userRepo);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/","/register","/h2-console","/addPerson", "/addEducation",
                        "/addExperience", "/addSkill", "/completedResume", "/summary","/logo", "/contactInfo",
                        "/education", "skills", "/expereince", "/view", "/coverLetter").permitAll()
                .antMatchers("/references").hasAuthority("APPLICANT")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //.formLogin().successForwardUrl("/loggedin")
                .and()

                //Displays 'you have been logged out' message on login form when a user logs out (default login form). Change this
                //to logout?logout if you are using a custom form.
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll().logoutSuccessUrl("/");

        http
                .csrf().disable();

        http
                .headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("password").authorities("APPLICANT");

        //Gets information from the database. See the code comments in the SSUDS class for user details. Haha.
        auth.userDetailsService(userDetailsServiceBean());
    }
}