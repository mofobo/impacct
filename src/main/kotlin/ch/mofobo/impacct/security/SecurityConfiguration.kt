package ch.mofobo.impacct.security

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
class SecurityConfiguration : WebSecurityConfigurerAdapter() {
    @Throws(Exception::class)
    public override fun configure(http: HttpSecurity) {
        http.antMatcher("/**").authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/styles/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
    }
}