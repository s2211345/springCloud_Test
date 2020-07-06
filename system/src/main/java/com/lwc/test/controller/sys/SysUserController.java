package com.lwc.test.controller.sys;

import com.lwc.test.controller.base.BaseController;
import com.lwc.test.model.sys.SysUser;
import com.lwc.test.service.sys.SysUserServer;
import com.lwc.test.view.sys.response.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sysUser")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserServer sysUserServer;

    @PostMapping("save")
    public SysResult<SysUser> save(@RequestBody SysUser user){
        SysResult<SysUser> result = new SysResult<>();
        sysUserServer.save(user);
        result.setData(user);
        return result;
    }

    @GetMapping("save")
    public String save(){
        return "get";
    }

}
