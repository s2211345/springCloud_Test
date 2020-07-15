package com.lwc.test.config.sys;

import com.lwc.test.filter.sys.LindTokenAuthenticationFilter;
import com.lwc.test.service.sys.impl.security.SecurityUserDetailsServiceImpl;
import com.lwc.test.view.sys.response.SysUserRespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private SecurityUserDetailsServiceImpl securityUserDetailsService;
    @Autowired
    private LindTokenAuthenticationFilter lindTokenAuthenticationFilter;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    /**
     * 授权规则
     * @param http
     * authenticated 需要登录
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()//开启登录配置
                .antMatchers("/admin/verifyCode/vercode","/admin/sysUser/login","/login.html",
                        "/test/getPass").permitAll() //不需要验证的接口
                .anyRequest().authenticated()//表示以外的访问都需要登录
                .and()
                .formLogin()
                //定义登录页面，未登录时，访问一个需要登录之后才能访问的接口，会自动跳转到该页面
                .loginPage("/admin/sysUser/login")
                //登录处理接口
                .loginProcessingUrl("/admin/sysUser/doLogin")
                //登录成功的处理器
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        SysUserRespVO user = (SysUserRespVO) authentication.getPrincipal();

                        /*Token token = tokenService.saveToken(loginUser);
                        ResponseUtil.responseJson(response, HttpStatus.OK.value(), token);*/
                        resp.setHeader("Access-Control-Allow-Origin", "*");
                        resp.setHeader("Access-Control-Allow-Methods", "*");
                        resp.setContentType("application/json;charset=UTF-8");
                        resp.setStatus(200);
                        String token = securityUserDetailsService.getAndSaveToken(user);
                        resp.getWriter().write(token);
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException exception) throws IOException, ServletException {
                        String msg = null;
                        if (exception instanceof BadCredentialsException) {
                            msg = "密码错误";
                        } else {
                            msg = exception.getMessage();
                        }
                        resp.setHeader("Access-Control-Allow-Origin", "*");
                        resp.setHeader("Access-Control-Allow-Methods", "*");
                        resp.setContentType("application/json;charset=UTF-8");
                        resp.getWriter().write(msg);
                    }
                })
                .permitAll()//和表单登录相关的接口统统都直接通过
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        resp.setHeader("Access-Control-Allow-Origin", "*");
                        resp.setHeader("Access-Control-Allow-Methods", "*");
                        resp.setContentType("application/json;charset=UTF-8");
                        PrintWriter out = resp.getWriter();
                        String token = securityUserDetailsService.getToken(req);
                        securityUserDetailsService.userOutLogin(token);
                        out.write("退出成功");
                        out.flush();
                    }
                })
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
        http.headers().cacheControl();
        http.headers().frameOptions().disable();
        http.addFilterBefore(lindTokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 设置拦截忽略文件夹，可以对静态资源放行
        web.ignoring().antMatchers("/statics/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //使用自定义认证规则，并且使用BCrypt算法处理密码
        auth.userDetailsService(securityUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
