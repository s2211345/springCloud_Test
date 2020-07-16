package com.lwc.test.controller.sys;

import com.lwc.test.controller.base.BaseController;
import com.lwc.test.model.sys.SysUser;
import com.lwc.test.model.sys.security.AccessToken;
import com.lwc.test.service.sys.SysUserService;
import com.lwc.test.service.sys.impl.security.SecurityUserDetailsServiceImpl;
import com.lwc.test.utils.SecurityTokenUtils;
import com.lwc.test.view.base.response.BaseResult;
import com.lwc.test.view.sys.request.SysUserReqVO;
import com.lwc.test.view.sys.response.SysResult;
import com.lwc.test.view.sys.response.SysUserRespVO;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/admin/sysUser")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SecurityUserDetailsServiceImpl securityUserDetailsService;
    @Autowired
    private SecurityTokenUtils securityTokenUtils;

    @PostMapping("save")
    public SysResult<SysUser> save(@RequestBody SysUser user){
        SysResult<SysUser> result = new SysResult<>();
        sysUserService.save(user);
        result.setData(user);
        return result;
    }

    @GetMapping("save")
    public String save(){
        return "get";
    }

    @GetMapping("/login")
    public RedirectView login(){
        return new RedirectView("/login.html");
    }

    @GetMapping("/getCurrentUser")
    @ResponseBody
    public SysUserRespVO getCurrentUser(){
        return securityUserDetailsService.getLoginUser();
    }
    @GetMapping("/refreshToken")
    @ResponseBody
    public AccessToken refreshToken(@RequestParam(value = "token") String oldToken){
        if(StringUtils.isBlank(oldToken)){
            return null;
        }
        return securityTokenUtils.refreshToken(oldToken);
    }

}
