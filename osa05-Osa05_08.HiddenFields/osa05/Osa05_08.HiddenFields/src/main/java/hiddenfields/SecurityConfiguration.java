package hiddenfields;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        List<UserDetails> users = new ArrayList<>();

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("zaneta")
                .password("love")
                .authorities("USER").build();

        UserDetails postman = User.withDefaultPasswordEncoder()
                .username("mariusz")
                .password("love")
                .authorities("INLOVE")
                .build();

        users.add(user);
        users.add(postman);

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager(users);
        return manager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/messages").authenticated()
                .antMatchers(HttpMethod.POST, "/messages").hasAnyAuthority("INLOVE");

        http.formLogin()
                .permitAll()
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login");
    }
}
