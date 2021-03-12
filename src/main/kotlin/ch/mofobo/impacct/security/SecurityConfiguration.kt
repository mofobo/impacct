package ch.mofobo.impacct.security

import ch.mofobo.impacct.interceptors.CustomAccessDeniedHandler
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import kotlin.jvm.Throws


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
class OAuth2SecurityConfigurerAdapter : WebSecurityConfigurerAdapter {

    private var customAccessDeniedHandler: CustomAccessDeniedHandler? = null

    constructor(customAccessDeniedHandler: CustomAccessDeniedHandler?) {
        this.customAccessDeniedHandler = customAccessDeniedHandler
    }

    @Throws(Exception::class)
    public override fun configure(http: HttpSecurity) {
        http
                .exceptionHandling()
                .accessDeniedHandler(customAccessDeniedHandler)
                .and()
                .antMatcher("/**").authorizeRequests()
                .antMatchers("/styles/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
    }
}