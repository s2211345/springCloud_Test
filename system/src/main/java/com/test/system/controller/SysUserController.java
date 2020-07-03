package com.test.system.controller;

import com.lwc.base.controller.BaseController;
import com.test.system.dao.SysUserDao;
import com.test.system.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sysUser")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserDao sysUserDao;

    @PostMapping("save")
    public String save(@RequestBody SysUser user){
        return "";
    }

    @GetMapping("save")
    public String save(){
        return "get";
    }

}
