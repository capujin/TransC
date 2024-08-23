package com.mvc.test.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mvc.test.entity.User.User;
import com.mvc.test.service.UserService;
import com.mvc.test.utils.Result;
import io.swagger.annotations.ApiOperation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
@CrossOrigin
//@Api(tags = "用户",value = "用户注册、登录、个人管理")
public class UserController {
    @Autowired
    private UserService userService;

    @Tag(name = "管理员")
    @ApiOperation(value = "获取所有权限")
    @GetMapping("/admin/permissions")
    public Result getPermissions(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username","admin");
        User user = new User();
        List<Map<String,Object>> users = userService.listMaps(queryWrapper);
        return Result.success(users);
    }

    @Tag(name = "管理员")
    @ApiOperation(value = "获取用户列表")
    @Operation(summary = "获取用户列表", description = "获取所有用户的列表")
    @GetMapping("/admin/users")
    public Result getUsers(
            @Parameter(description = "当前页码", example = "1") @RequestParam Integer page_num,
            @Parameter(description = "每页条数", example = "10") @RequestParam Integer page_size,
            @Parameter(description = "用户名", example = "") @RequestParam(required = false) String username,
            @Parameter(description = "用户状态", example = "",schema = @Schema(allowableValues = {"ACTIVE", "INACTIVE", "LOCKED", "DISABLED"})) @RequestParam(required = false) String status
    ){
        IPage<User> page = new Page<>(page_num, page_size);
        IPage<User> userPage = userService.page(page);
        List<User> users = userPage.getRecords();
        //格式化结果集
        List<Map<String,Object>> userMaps = new ArrayList<>();
        for (User user : users) {
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("id", user.getId());
            userMap.put("username", user.getUsername());
            userMap.put("status", user.getUsername());
            userMap.put("created_at", user.getCreatedAt());
            userMaps.add(userMap);
        }
        long total = userPage.getTotal();
        return Result.success(userMaps,total);
    }
}

