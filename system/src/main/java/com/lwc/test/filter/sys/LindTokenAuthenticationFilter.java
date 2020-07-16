package com.lwc.test.filter.sys;

import com.lwc.test.service.sys.SysUserService;
import com.lwc.test.service.sys.impl.security.SecurityUserDetailsServiceImpl;
import com.lwc.test.utils.SecurityTokenUtils;
import com.lwc.test.view.sys.request.SysUserReqVO;
import com.lwc.test.view.sys.response.SysUserRespVO;
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

/**
 * 如果连接带Token，尝试刷新授权
 */
@Component
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
   * 如果带有Authorization，
   * @param request
   * @param response
   * @param filterChain
   * @throws ServletException
   * @throws IOException
   */
  @Override
  protected void doFilterInternal(
          HttpServletRequest request,
          HttpServletResponse response,
          FilterChain filterChain) throws ServletException, IOException {

    String token = securityTokenUtils.getTokenByRequest(request);
    if (StringUtils.isNotBlank(token)) {
      String userName = securityTokenUtils.getSubjectFromToken(token);
      if (securityTokenUtils.checkBlacklist(token) && StringUtils.isNotBlank(userName)) {
        SysUserRespVO userRespVO = new SysUserRespVO();
        userRespVO.setUserName(userName);
        if(securityTokenUtils.validateToken(token,userRespVO)){
          if (StringUtils.isNotBlank(userName) && SecurityContextHolder.getContext().getAuthentication() == null) {
            SysUserReqVO queryParam = new SysUserReqVO();
            queryParam.setUserName(userName);
            UserDetails userDetails = sysUserService.queryByReq(queryParam);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                    request));
            logger.info("用户：" + userName + "传入Token信息并且Token过期, 更新token");
            SecurityContextHolder.getContext().setAuthentication(authentication);
          }
        }
      }
    }

    filterChain.doFilter(request, response);
  }
}