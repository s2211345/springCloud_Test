package com.lwc.test.controller.sys;

import com.lwc.test.controller.base.BaseController;
import com.lwc.test.enums.base.BaseCodeEnum;
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
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/admin/sysUser")
@Slf4j
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SecurityUserDetailsServiceImpl securityUserDetailsService;
    @Autowired
    private SecurityTokenUtils securityTokenUtils;

    @PostMapping("/save")
    @ResponseBody
    public BaseResult save(@RequestBody SysUserReqVO req){
        sysUserService.saveUser(req);
        return new BaseResult().success();
    }

    @GetMapping("/login")
    public RedirectView login(){
        return new RedirectView("/login.html");
    }

    @GetMapping("/getCurrentUser")
    @ResponseBody
    public BaseResult<String> getCurrentUser(){
        return null != securityUserDetailsService.getLoginUser() ? new BaseResult().success(): new BaseResult().fail();
    }
    @GetMapping("/refreshToken")
    @ResponseBody
    public BaseResult<AccessToken> refreshToken(@RequestParam(value = "token") String oldToken){
        BaseResult result = new BaseResult();
        if(StringUtils.isBlank(oldToken)){
            return result.fail("请传入旧token");
        }
        oldToken = securityTokenUtils.formatTokenDelBearer(oldToken);
        if(securityTokenUtils.checkBlacklist(oldToken)){
            return result.fail("无效token，请检查");
        }
        SysUserRespVO userRespVO = new SysUserRespVO();
        userRespVO.setUserName(securityTokenUtils.getSubjectFromToken(oldToken));
        if( !securityTokenUtils.validateToken(oldToken,userRespVO)){
            return result.fail(BaseCodeEnum.TOKEN_FAIL.getCode(),"无效token，或已过期，请重新获取");
        }
        AccessToken accessToken = securityTokenUtils.refreshToken(oldToken);
        return result.success(accessToken);
    }

    @PostMapping("/list")
    @ResponseBody
    public SysResult<List<SysUserRespVO>> getlist(SysUserReqVO req){
        SysResult result = new SysResult();
        if(0 != req.getPage() && 0 != req.getLimit()){
            int page = (req.getPage()-1) * req.getLimit();
            req.setPage(page);
        }
        List<SysUserRespVO> list = sysUserService.listByReq(req);
        int count = sysUserService.countByReq(req);
        return result.successOK(list,count);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public BaseResult<SysUserRespVO> get(@PathVariable Integer id){
        SysResult result = new SysResult();
        SysUserRespVO vo = sysUserService.queryById(id);
        return result.success(vo);
    }

}
