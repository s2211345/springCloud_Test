package com.lwc.test.controller.sys;

import com.lwc.test.controller.base.BaseController;
import com.lwc.test.model.sys.SysUser;
import com.lwc.test.service.sys.SysUserService;
import com.lwc.test.view.sys.request.SysUserReqVO;
import com.lwc.test.view.sys.response.SysResult;
import com.lwc.test.view.sys.response.SysUserRespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/admin/sysUser")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

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

    @PostMapping("/getCurrentUser")
    public SysUserRespVO getCurrentUser(@RequestBody SysUserReqVO userReqVO){
        return sysUserService.getCurrentUser(userReqVO);
    }

}
