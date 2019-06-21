package com.github.nicejing.security.security.config;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.jwt.crypto.sign.SignatureVerifier;
import org.springframework.security.jwt.crypto.sign.Signer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.AntPathMatcher;

import com.github.nicejing.security.security.support.AdminAccessDeniedHandler;
import com.github.nicejing.security.security.support.AdminAuthenticationEntryPoint;
import com.github.nicejing.security.security.support.AdminAuthenticationFailureHandler;
import com.github.nicejing.security.security.support.AdminAuthenticationFilter;
import com.github.nicejing.security.security.support.AdminAuthenticationSuccessHandler;
import com.github.nicejing.security.security.support.AdminLogoutSuccessHandler;
import com.github.nicejing.security.security.support.AdminSecurityProperties;

/**
 * @author Nathan
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AdminSecurityProperties           securityProperties;
    @Autowired
    private AdminAuthenticationSuccessHandler successHandler;
    @Autowired
    private AdminAuthenticationFailureHandler failureHandler;
    @Autowired
    private AdminLogoutSuccessHandler         logoutSuccessHandler;
    @Autowired
    private AdminAccessDeniedHandler          accessDeniedHandler;
    @Autowired
    private AdminAuthenticationEntryPoint     authenticationEntryPoint;
    @Autowired
    private AdminAuthenticationFilter         authenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.formLogin().loginProcessingUrl(securityProperties.getLoginProcessingUrl()).successHandler(successHandler)
            .failureHandler(failureHandler);
        http.logout().logoutUrl(securityProperties.getLogoutProcessingUrl()).logoutSuccessHandler(logoutSuccessHandler);
        http.authorizeRequests()
            // 登录、退出页面不需要权限
            .antMatchers(securityProperties.getLoginProcessingUrl(), securityProperties.getLogoutProcessingUrl(),
                "/error")
            .permitAll().anyRequest().access("@adminPermissionService.onAuthentication(request, authentication)").and()
            .csrf().disable().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
            .accessDeniedHandler(accessDeniedHandler);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 123456   $2a$10$ofPkBDUezOJp6Sik63Q/0.nqVu9RG1lGURetKzd2UWZDq3CaiCnpW
        return new BCryptPasswordEncoder();

    }

    @Bean
    public SignatureVerifier signatureVerifier() {
        String modulus = securityProperties.getRsaPublicKeyModulus();
        String publicExponent = securityProperties.getRsaPublicKeyPublicExponent();
        return new RsaVerifier(new BigInteger(modulus), new BigInteger(publicExponent));
    }

    @Bean
    public Signer signer() {
        String modulus = securityProperties.getRsaPublicKeyModulus();
        String privateExponent = securityProperties.getPrivateExponent();
        return new RsaSigner(new BigInteger(modulus), new BigInteger(privateExponent));
    }

    @Bean
    public AntPathMatcher antPathMatcher() {
        return new AntPathMatcher();
    }

    /**
     * @param args
     * @throws NoSuchAlgorithmException
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(256 * 8);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

        BigInteger modulus = publicKey.getModulus();
        System.out.println(modulus);
        BigInteger publicExponent1 = publicKey.getPublicExponent();
        System.out.println(publicExponent1);
        RSAPrivateCrtKey privateKey = (RSAPrivateCrtKey) keyPair.getPrivate();
        BigInteger publicExponent = privateKey.getPublicExponent();
        System.out.println(publicExponent);

        BigInteger privateExponent = privateKey.getPrivateExponent();
        System.out.println(privateExponent);
    }

}
