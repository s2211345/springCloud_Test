package com.test.system.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Order(Integer.MIN_VALUE + 1)
@WebFilter(urlPatterns = "/*", filterName = "corsFilter", asyncSupported = true)
@Component
public class MyCorsFilter extends OncePerRequestFilter {

	/* 跨域请求配置 */
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws ServletException, IOException {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest reqs = (HttpServletRequest) req;
		log.info("origin={}",reqs.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Origin", reqs.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
		response.setHeader("Access-Control-Max-Age", "5000");
		response.setHeader("Access-Control-Allow-Headers",
				"Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, " +
						"Expires, Content-Type, X-E4M-With,Authorization,Token,token,access-token,stoken,login_token");
		res = response;
		chain.doFilter(req, res);
	}

}
