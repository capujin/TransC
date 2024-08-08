package com.mvc.test.controller;

import com.mvc.test.entity.User;
import com.mvc.test.mapper.UserMapper;
import com.mvc.test.service.MenuService;
import com.mvc.test.service.UserService;
import com.mvc.test.service.UserShowService;
import com.mvc.test.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
@Api(tags = "用户",value = "用户注册、登录、个人管理")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "更新学生信息")
    @PostMapping("/updateuser")
    public Result updateUser(User user) {
        Result result = new Result();
        if(userService.updateUser(user)>0){
            result.setCode("200");
            result.setData(user);
            result.setMsg("更新密码成功!");
        }
        else{
            result.setCode("500");
            result.setData(user);
            result.setMsg("修改失败");
        }
        return result;
    }

    @ApiOperation(value = "根据用户名查询")
    @GetMapping("/query")
    public Result queryUserByName(User user) {
        System.out.println("传来的user信息："+user);
        Result result = new Result();
        String u_id = userService.findUserByName(user);
        System.out.println("u_id:"+u_id);
        if(u_id != null){
            result.setCode("200");
            result.setData(userService.getUserById(u_id));
            result.setMsg("查询成功");
        }
        else{
            result.setCode("500");
            result.setData(null);
            result.setMsg("查询失败,用户名不存在");
        }
        return result;
    }
}
