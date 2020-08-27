package lozm.global.config;


import lombok.RequiredArgsConstructor;
import lozm.api.sign.SignService;
import lozm.global.config.authentication.SecurityResourceService;
import lozm.global.config.authentication.UrlFilterInvocationSecurityMetadataSource;
import lozm.global.config.authentication.UrlResourcesMapFactoryBean;
import lozm.global.jwt.JwtAuthenticationEntryPoint;
import lozm.global.jwt.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final SignService signService;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtRequestFilter jwtRequestFilter;
    private final SecurityResourceService securityResourceService;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(signService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();

        httpSecurity.exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true)
                .expiredUrl("/sign/in");

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(customFilterSecurityInterceptor(), FilterSecurityInterceptor.class);

        httpSecurity.authorizeRequests()
                .antMatchers("/", "/sign/in", "/api/sign/in").permitAll()
                .antMatchers("/libs/**", "/script/**", "/images/**", "error/**" ,"/pages/**").permitAll()
                .anyRequest().authenticated();
    }

    @Bean
    public FilterSecurityInterceptor customFilterSecurityInterceptor() throws Exception {
        FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
        filterSecurityInterceptor.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource());
        filterSecurityInterceptor.setAccessDecisionManager(affirmativeBased());
        filterSecurityInterceptor.setAuthenticationManager(authenticationManagerBean());

        return filterSecurityInterceptor;
    }

    @Bean
    public FilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource() throws Exception {
        return new UrlFilterInvocationSecurityMetadataSource(urlResourceMapFactoryBean().getObject());
    }

    @Bean
    public UrlResourcesMapFactoryBean urlResourceMapFactoryBean() {
        return new UrlResourcesMapFactoryBean(securityResourceService);
    }

    @Bean
    public AccessDecisionManager affirmativeBased() {
        AffirmativeBased affirmativeBased = new AffirmativeBased(getAccessDecisionVoters());
        return affirmativeBased;
    }

    private List<AccessDecisionVoter<?>> getAccessDecisionVoters() {
        return Arrays.asList(new RoleVoter());
    }

}
