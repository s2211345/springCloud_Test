package com.lwc.test.utils;

import com.lwc.test.model.sys.security.AccessToken;
import com.lwc.test.model.sys.security.JwtProperties;
import com.lwc.test.view.sys.response.SysUserRespVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Component
public class SecurityTokenUtils {
    private static final Logger log = LoggerFactory.getLogger(SecurityTokenUtils.class);

    private static final String tokenHead = "Bearer ";
    private static final String tokenHeader = "Authorization";

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 从请求中获得token
     * Authorization：Bearer token
     * @param request
     * @return
     */
    public String getTokenByRequest(HttpServletRequest request) {
        String token = request.getParameter(tokenHeader);
        if (StringUtils.isEmpty(token)) {
            token = request.getHeader(tokenHeader);
        }
        return token;
    }

    /**
     * 根据用户信息生成token
     */
    public AccessToken createToken(SysUserRespVO userDetails) {
        return createToken(userDetails.getUsername());
    }

    /**
     * 生成token
     * 参数是我们想放入token中的字符串
     */
    public AccessToken createToken(String subject) {
        // 当前时间
        final Date now = new Date();
        // 过期时间
        final Date expirationDate = new Date(now.getTime() + jwtProperties.getExpirationTime() * 1000);

        String token = jwtProperties.getTokenPrefix() + Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getApiSecretKey())
                .compact();
        return AccessToken.builder().loginAccount(subject).token(token).expirationTime(expirationDate).build();
    }

    /**
     * 验证token是否还有效
     * <p>
     * 反解析出token中信息，然后与参数中的信息比较，再校验过期时间
     *
     * @param token       客户端传入的token
     * @param userDetails 从数据库中查询出来的用户信息
     */
    public boolean validateToken(String token, SysUserRespVO userDetails) {
        token = formatTokenDelBearer(token);
        Claims claims = getClaimsFromToken(token);
        return claims.getSubject().equals(userDetails.getUsername()) && !isTokenExpired(claims);
    }


    /**
     * 刷新token
     * 过滤器会对请求进行验证，所以这里可以不必验证
     *
     * @param oldToken 带tokenHead的token
     */
    public AccessToken refreshToken(String oldToken) {
        String token = formatTokenDelBearer(oldToken);

        // token反解析
        Claims claims = getClaimsFromToken(token);

        //如果token在30分钟之内刚刷新过，返回原token
        if (tokenRefreshJustBefore(claims)) {
            return AccessToken.builder().loginAccount(claims.getSubject()).token(oldToken).expirationTime(claims.getExpiration()).build();
        } else {
            return createToken(claims.getSubject());
        }
    }

    /**
     * 判断token是否需要刷新，token过期，但不超过1小时
     */
    private boolean tokenRefreshJustBefore(Claims claims) {
        Date refreshDate = new Date();
        //刷新时间在创建时间的指定时间内
        if (refreshDate.after(claims.getExpiration()) && refreshDate.before(offsetSecondTime(claims.getExpiration(), -3600))) {
            return true;
        }
        return false;
    }

    /**
     * 从token中拿到负载信息
     */
    private Claims getClaimsFromToken(String token) {
        token = formatTokenDelBearer(token);
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(jwtProperties.getApiSecretKey())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.error("JWT反解析失败, token已过期或不正确, token : {}", token);
        }
        return claims;
    }


    /**
     * 从token中获取主题
     */
    public String getSubjectFromToken(String token) {
        token = formatTokenDelBearer(token);
        Claims claims = getClaimsFromToken(token);
        if (claims != null) {
            return claims.getSubject();
        } else {
            return null;
        }
    }

    /**
     * 判断token是否已经过期
     */
    private boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }

    /**
     * 添加Token黑名单
     * @param token
     */
    public void addTokenBlacklist(String token){
        token = formatTokenDelBearer(token);
        redisTemplate.boundValueOps(token).set("",jwtProperties.getExpirationTime(), TimeUnit.SECONDS);
    }

    /**
     * 检查Token是否在黑名单
     * @param token
     */
    public Boolean checkBlacklist(String token){
        token = formatTokenDelBearer(token);
        if(redisTemplate.hasKey(token)){
            return true;
        }else{
            return false;
        }
    }


    /**
     * 时间偏移 （秒）
     * @param date
     * @param second
     * @return
     */
    public Date offsetSecondTime(Date date,Integer second) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = new GregorianCalendar();
        // 获取当前日期之前 xx 秒。整数为当前时间往后移，负数则往前推。
        c.setTime(date);
        c.add(Calendar.SECOND, second);    // 秒
        date = c.getTime();
        System.out.println(df.format(date));
        return date;
    }

    public String formatTokenDelBearer(String token){
        return token.substring(tokenHead.length());
    }

}
