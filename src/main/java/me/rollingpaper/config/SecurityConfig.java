package me.rollingpaper.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.RequiredArgsConstructor;
import me.rollingpaper.security.basic.CustomAuthenticationProvider;
import me.rollingpaper.security.basic.CustomUrlAuthenticationFailureHandler;
import me.rollingpaper.security.basic.CustomUrlAuthenticationSuccessHandler;
import me.rollingpaper.security.social.CustomOAuth2UserService;


@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //private final UserService userService;
    
    private final CustomOAuth2UserService customOAuth2UserService;
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider();
    }
    
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        return new CustomUrlAuthenticationSuccessHandler();
    }
    
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler(){
        return new CustomUrlAuthenticationFailureHandler();
    }
    
	/*
	 * @Bean public CorsConfigurationSource corsConfigurationSource() {
	 * CorsConfiguration configuration = new CorsConfiguration();
	 * 
	 * configuration.addAllowedOrigin("http://localhost:3000");
	 * configuration.addAllowedHeader("*"); configuration.addAllowedMethod("*");
	 * configuration.setAllowCredentials(true);
	 * 
	 * UrlBasedCorsConfigurationSource source = new
	 * UrlBasedCorsConfigurationSource(); source.registerCorsConfiguration("/**",
	 * configuration); return source; }
	 */

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/images/**", "/fonts/**", "/templates/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/api/member/**").hasRole("MEMBER")
                    .antMatchers("/loginSuccess").hasRole("MEMBER")
                    .antMatchers("/loginPage").permitAll()
                    .antMatchers("/").permitAll()
               .and()
                    .formLogin()
                    .loginPage("/")
                    .loginProcessingUrl("/api/login")
                    .successHandler(authenticationSuccessHandler())
                    .failureHandler(authenticationFailureHandler())
                    .permitAll()
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                .and()
                    .exceptionHandling()
                    .accessDeniedPage("/error")
        		.and()
        			.oauth2Login()
        			.userInfoEndpoint()
        			.userService(customOAuth2UserService)
        		.and()
        			.defaultSuccessUrl("/mainHome");
        http.headers().frameOptions().disable();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    	auth.authenticationProvider(authenticationProvider());
    }
    
}