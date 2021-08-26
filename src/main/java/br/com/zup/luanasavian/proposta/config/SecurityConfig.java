package br.com.zup.luanasavian.proposta.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String PROPOSTAS = "/propostas/**";
    private static final String CARTOES = "/cartoes/**";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests -> authorizeRequests
                .antMatchers(HttpMethod.GET, PROPOSTAS).hasAuthority("SCOPE_escopo-proposta")
                .antMatchers(HttpMethod.GET, CARTOES).hasAuthority("SCOPE_escopo-proposta")
                .antMatchers(HttpMethod.POST, PROPOSTAS).hasAuthority("SCOPE_escopo-proposta")
                .anyRequest().authenticated()
        ).oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }
}
