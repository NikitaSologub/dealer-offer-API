package ru.alfaleasing.dealer.offer.api.config;

import com.google.common.cache.CacheBuilder;
import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.proc.DefaultJOSEObjectTypeVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import ru.alfaleasing.dealer.offer.api.exception.DefaultAccessDeniedHandler;
import ru.alfaleasing.dealer.offer.api.exception.DefaultAuthenticationEntryPoint;
import ru.alfaleasing.logging.http.HttpStatusNotIs2xxSuccessfulLogFilter;

import java.time.Duration;

import static ru.alfaleasing.dealer.offer.api.util.ScopeProperties.DEALER_OFFER;

@Configuration
@EnableWebSecurity
public class OAuth2SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    private String jwtSetUri;

    @Value("${jwks.public-key.time-to-live}")
    private Long cacheExpireTime;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .cors().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests(authorize -> authorize
                        .antMatchers("/v1/load/dealer").hasAuthority(DEALER_OFFER.getAlias())
                        .antMatchers("/v1/dealers").hasAuthority(DEALER_OFFER.getAlias())
                        .antMatchers("/v1/offers-by-api/new").hasAuthority(DEALER_OFFER.getAlias())
                        .anyRequest()
                        .authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder())
                        .and()
                        .authenticationEntryPoint(authenticationEntryPoint())
                        .accessDeniedHandler(accessDeniedHandler())))
                .exceptionHandling();

        http.addFilterBefore(new HttpStatusNotIs2xxSuccessfulLogFilter(), SecurityContextPersistenceFilter.class);
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new DefaultAuthenticationEntryPoint();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new DefaultAccessDeniedHandler();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        ConcurrentMapCache jwkSetCache = new ConcurrentMapCache("jwkSetCache", CacheBuilder.newBuilder()
                .expireAfterWrite(Duration.ofSeconds(cacheExpireTime))
                .build().asMap(), false);
        return NimbusJwtDecoder.withJwkSetUri(jwtSetUri)
                .jwtProcessorCustomizer(p -> p.setJWSTypeVerifier(
                        new DefaultJOSEObjectTypeVerifier<>(new JOSEObjectType("at+jwt"))))
                .cache(jwkSetCache)
                .build();
    }
}
