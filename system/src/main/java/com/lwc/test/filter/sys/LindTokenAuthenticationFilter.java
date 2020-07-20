package com.lwc.test.filter.sys;

import com.lwc.test.service.sys.SysUserService;
import com.lwc.test.service.sys.impl.security.SecurityUserDetailsServiceImpl;
import com.lwc.test.utils.DateUtils;
import com.lwc.test.utils.ReflectUtils;
import com.lwc.test.utils.RequestUtils;
import com.lwc.test.utils.SecurityTokenUtils;
import com.lwc.test.view.sys.request.SysUserReqVO;
import com.lwc.test.view.sys.response.SysUserRespVO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * 如果连接带Token，尝试刷新授权
 */
@Component
@Slf4j
public class LindTokenAuthenticationFilter extends OncePerRequestFilter {

  @Autowired
  RedisTemplate<String, String> redisTemplate;
  private static final String tokenHead = "Bearer ";
  private static final String tokenHeader = "Authorization";
  @Autowired
  private SecurityUserDetailsServiceImpl userDetailsService;
  @Autowired
  private SysUserService sysUserService;
  @Autowired
  private SecurityTokenUtils securityTokenUtils;

  /**
   * 加入Token，刷新认证信息和前后端分离情况认证处理
   * 如果带有Authorization Token参数，验证是否在系统内没有认证，没有则根据Token信息自动登录
   * 后端存放页面使用SecurityContextHolder.getContext().getAuthentication()验证跳过， 前后端分离从缓存中读取认证信息
   * @param request
   * @param response
   * @param filterChain
   * @throws ServletException
   * @throws IOException
   */
  @SneakyThrows
  @Override
  protected void doFilterInternal(
          HttpServletRequest request,
          HttpServletResponse response,
          FilterChain filterChain) throws ServletException, IOException {

    String token = securityTokenUtils.getTokenByRequest(request);
    if (StringUtils.isNotBlank(token)) {
      String userName = securityTokenUtils.getSubjectFromToken(token);
      //检查是否黑名单
      if ( !securityTokenUtils.checkBlacklist(token) && StringUtils.isNotBlank(userName)) {
        SysUserRespVO userRespVO = new SysUserRespVO();
        userRespVO.setUserName(userName);
        //检查是否过期
        if(securityTokenUtils.validateToken(token,userRespVO)){
          //系统内没有认证带TOKEN才需要自动登录
          if (SecurityContextHolder.getContext().getAuthentication() == null) {
            //从缓存中获得用户信息
            UserDetails userDetails = securityTokenUtils.getCacheUserByToken(token);
            if(null == userDetails){
              userDetails = userDetailsService.loadUserByUsername(userName);
              securityTokenUtils.setCacheUserByToken(userDetails,token);
            }
            //组装authentication对象
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                    request));
            //将authentication信息放入到上下文对象中
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("IP-" + RequestUtils.getIPAddress(request) + "----" + userDetails.getUsername() + "使用TOKEN请求" + request.getRequestURI() +"自动登录成功");
          }
        }
      }
    }

    filterChain.doFilter(request, response);
  }
}