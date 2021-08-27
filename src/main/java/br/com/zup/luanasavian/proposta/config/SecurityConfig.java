package br.com.zup.luanasavian.proposta.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String PROPOSTAS = "/propostas/**";
    private static final String CARTOES = "/cartoes/**";
    private static final String ACTUATOR= "/actuator/**";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests -> authorizeRequests
                .antMatchers(HttpMethod.GET, PROPOSTAS).hasAuthority("SCOPE_escopo-proposta")
                .antMatchers(HttpMethod.GET, CARTOES).hasAuthority("SCOPE_escopo-proposta")
                .antMatchers(HttpMethod.POST, PROPOSTAS).hasAuthority("SCOPE_escopo-proposta")
                .antMatchers(HttpMethod.GET, ACTUATOR).permitAll()
                .anyRequest().authenticated()
        ).oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
