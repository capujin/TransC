package com.mvc.test.controller;

import com.mvc.test.DTO.UserDTO.UserDTO;
import com.mvc.test.annotation.VerifyToken;
import com.mvc.test.entity.Roles;
import com.mvc.test.entity.User.User;
import com.mvc.test.entity.User.UserSecurity;
import com.mvc.test.service.RolesService;
import com.mvc.test.utils.JwtUtils;
import com.mvc.test.service.UserService;
import com.mvc.test.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
@Api(tags = "用户",value = "用户注册、登录、个人管理")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RolesService rolesService;

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public Result login(@RequestParam String username, @RequestParam String password) {
        // 这里需要对用户名和密码进行验证
        User user = userService.authenticate(username, password); // 假设你有一个方法来验证用户
        if (user != null) {
            String token = new JwtUtils().generateToken(user.getId());
            Map<String, String> data = new HashMap<>();
            data.put("token", token);
            return Result.successLogin(data);
        } else {
            return Result.inputError();
        }
    }

    @VerifyToken
    @ApiOperation(value = "获取用户信息")
    @GetMapping("/info")
    public Result getUserInfo() {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String id = (String) authentication.getPrincipal();
            User user = userService.getUserById(id);
            String role_name = rolesService.getRoleById(id).getRoleName();
            int enabled = userService.getUserSecurityById(id).getEnabled();
            UserDTO userDTO = new UserDTO(user.getId(),user.getUsername(),user.getCreatedAt(),role_name,enabled);
            return Result.success(userDTO);
        }catch (Exception e){
            return Result.internalServerError();
        }
    }
//    public Result updateUser(User user) {
//        Result result = new Result();
//        if(userService.updateUser(user)>0){
//            result.setCode("200");
//            result.setData(user);
//            result.setMsg("更新密码成功!");
//        }
//        else{
//            result.setCode("500");
//            result.setData(user);
//            result.setMsg("修改失败");
//        }
//        return result;
//    }
//
//    @ApiOperation(value = "根据用户名查询")
//    @GetMapping("/query")
//    public Result queryUserByName(User user) {
//        System.out.println("传来的user信息："+user);
//        Result result = new Result();
//        String u_id = userService.findUserByName(user);
//        System.out.println("u_id:"+u_id);
//        if(u_id != null){
//            result.setCode("200");
//            result.setData(userService.getUserById(u_id));
//            result.setMsg("查询成功");
//        }
//        else{
//            result.setCode("500");
//            result.setData(null);
//            result.setMsg("查询失败,用户名不存在");
//        }
//        return result;
//    }
}
