package com.mvc.test.controller;

import com.mvc.test.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
@Api(tags = "管理员",value = "用户管理,权限管理")
public class AdminController {

//    @Autowired
//    private UserService userService;
//    @Autowired
//    private AdminService adminService;

//    @VerifyToken
//    @ApiOperation(value = "创建用户")
//    @PostMapping("/register")
//    public Result register(@RequestBody UserRegistrationDTO userRegistrationDTO) {
//        // 获取当前的 Authentication 对象
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        // 从 Authentication 对象中获取用户信息
//        if (authentication != null && authentication.getPrincipal() instanceof String) {
//            String userId = (String) authentication.getPrincipal();
//            String roleName = userService.getRoleById(userId).getRoleName();
//            if(Objects.equals(roleName, "管理员") || Objects.equals(roleName, "超级管理员")){
//                //        如果用户名已经存在则不能创建新用户
//                if(userService.checkUsernameExists(userRegistrationDTO.getUsername())){
////                    User user = new User(userRegistrationDTO.getUsername(),userRegistrationDTO.getPassword());
////                    boolean isSuccess = userService.save(user);
////                    List<String> roleIds = userRegistrationDTO.getRoleIds();
//////            遍历List
////                    for (String roleId : roleIds) {
//////                插入user和role的关联表
////                        UserRoles userRoles = new UserRoles(roleId,user.getId());
////                        userService.saveUserRoles(userRoles);
////                    }
////
////                    if(isSuccess){
////                        System.out.println("存储成功");
////                        return Result.success();
////                    }
//                    return Result.internalServerError();
//                }else{
//                    return Result.msgError("用户已存在");
//                }
//            }
//        }else{
//            return Result.internalServerError();
//        }
//        return Result.internalServerError();
//    }
//
//    @VerifyToken
//    @ApiOperation(value = "获取所有用户")
//    @GetMapping("/all")
//    public Result getAllUser() {
//        try{
//            List<User> users = adminService.findAllUsers();
//            System.out.println(users);
//            return Result.success(users);
//        }catch (Exception e){
//            return Result.internalServerError();
//        }
//    }

}
