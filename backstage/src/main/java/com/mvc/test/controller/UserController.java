package com.mvc.test.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mvc.test.DTO.RolesDTO.RolesPermissionDTO;
import com.mvc.test.annotation.VerifyToken;
import com.mvc.test.entity.User.*;
import com.mvc.test.service.*;
import com.mvc.test.utils.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
@CrossOrigin
//@Api(tags = "用户",value = "用户注册、登录、个人管理")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionsService permissionsService;
    @Autowired
    private RolesService rolesService;
    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private UsersRolesService usersRolesService;

    @VerifyToken
    @Tag(name = "管理员")
    @ApiOperation(value = "获取所有权限")
    @GetMapping("/admin/permissions")
    public Result getPermissions() {
        try{
            List<Permissions> data = permissionsService.list();
            long total = permissionsService.count();
            return Result.success(data,total);
        }catch (Exception e){
            return Result.internalServerError();
        }
    }

    @VerifyToken
    @Tag(name = "管理员")
    @ApiOperation(value = "获取用户列表")
    @Operation(summary = "获取用户列表", description = "获取所有用户的列表")
    @GetMapping("/admin/users")
    public Result getUsers(
            @Parameter(description = "当前页码", example = "1") @RequestParam Integer page_num,
            @Parameter(description = "每页条数", example = "10") @RequestParam Integer page_size,
            /*@Parameter(description = "用户名", example = "") @RequestParam(required = false) String username,*/
            @Parameter(description = "用户状态", example = "", schema = @Schema(allowableValues = {"ACTIVE", "INACTIVE", "LOCKED", "DISABLED"})) @RequestParam(required = false) String status
    ) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        IPage<User> page = new Page<>(page_num, page_size);
        if (status != null && !status.isEmpty()) {
            queryWrapper.eq("status", status);
        }
        IPage<User> userPage = userService.page(page,queryWrapper);
        List<User> users = userPage.getRecords();
        if(users.isEmpty() || users == null){
            return Result.success(new ArrayList<>());
        }
        //格式化结果集
        List<Map<String, Object>> userMaps = new ArrayList<>();
        for (User user : users) {
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("id", user.getId());
            userMap.put("username", user.getUsername());
            userMap.put("status", user.getStatus());
            userMap.put("created_at", user.getCreatedAt());
            userMaps.add(userMap);
        }
        long total = userPage.getTotal();
        return Result.success(userMaps, total);
    }

    @VerifyToken
    @Tag(name = "管理员")
    @ApiOperation(value = "修改用户密码")
    @PutMapping("/admin/password/{id}")
    public Result updatePassword(
            @PathVariable Integer id,
            @RequestBody @Parameter(description = "示例:\n\n{\n\"old_password\":\"string\",\n\"new_password\":\"string\"\n}") Map<String, String> passwordData
    ) {
        String old_password = passwordData.get("old_password");
            String new_password = passwordData.get("new_password");
        if(old_password == null || new_password == null){
            return Result.failure("参数错误");
        }
        User new_user = userService.getById(id);
        if(new_user == null){
            return Result.failure("用户不存在");
        }
        if(!new_user.getPassword().equals(old_password)){
            return Result.failure("密码验证失败");
        }
        new_user.setPassword(new_password);
        boolean is_success = userService.updateById(new_user);
        if(is_success){
            return Result.success();
        }else{
            return Result.internalServerError();
        }
    }

    @VerifyToken
    @Tag(name = "管理员")
    @ApiOperation(value = "获取角色列表")
    @GetMapping("/admin/roles")
    public Result getRoles(
            @Parameter(description = "当前页码", example = "1") @RequestParam Integer page_num,
            @Parameter(description = "每页条数", example = "10") @RequestParam Integer page_size
    ) {
        try {
            IPage<Roles> page = new Page<>(page_num, page_size);
            IPage<Roles> rolePage = rolesService.page(page);
            ArrayList<Roles> data = (ArrayList<Roles>) rolePage.getRecords();
            return Result.success(data);
        } catch (Exception ex) {
            // 抛出自定义异常或者让全局异常处理器来处理
            return Result.internalServerError();
        }
    }

    @VerifyToken
    @Tag(name = "管理员")
    @ApiOperation(value = "新建角色")
    @PostMapping("/admin/roles")
    @Transactional(rollbackFor = Exception.class)
    public Result createRole(
            @RequestBody RolesPermissionDTO role
    ) {
        QueryWrapper<Roles> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_name", role.getRoleName());

        if (rolesService.getOne(queryWrapper) != null) {
            return Result.failure("资源已存在");
        }

        Roles new_row = new Roles();
        new_row.setRoleName(role.getRoleName());
        rolesService.save(new_row);

        Integer role_id = rolesService.getOne(queryWrapper).getId();
        List<RolePermissions> res = new ArrayList<>();
        for (Integer permission_id : role.getPermission_ids()) {
            RolePermissions new_row_permission = new RolePermissions();
            new_row_permission.setRoleId(role_id);
            new_row_permission.setPermissionId(permission_id);
            res.add(new_row_permission);
        }
        rolePermissionService.saveBatch(res);
        return Result.success();
    }

    @VerifyToken
    @Tag(name = "管理员")
    @ApiOperation(value = "根据角色获取用户列表")
    @GetMapping("/admin/usersByRole")
    @Transactional(rollbackFor = Exception.class)
    public Result getUserListByRole(
            @Parameter(description = "角色id") @RequestParam Integer role_id
    ) {
        if (rolesService.getById(role_id) == null) {
            return Result.failure("资源不存在");
        }
        // 根据角色id获取用户id列表
        List<Integer> userIds = usersRolesService.lambdaQuery()
                .select(UsersRoles::getUserId)
                .eq(UsersRoles::getRoleId, role_id)
                .list()
                .stream()
                .map(UsersRoles::getUserId)
                .collect(Collectors.toList());
        if (userIds.isEmpty() || userIds == null) {
            return Result.success(new ArrayList<>());
        }
        Collection<User> users = userService.listByIds(userIds);
//        选择性返回数据
        List<Map<String, Object>> userMaps = new ArrayList<>();
        for (User user : users) {
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("id", user.getId());
            userMap.put("username", user.getUsername());
            userMap.put("status", user.getStatus());
            userMap.put("created_at", user.getCreatedAt());
            userMaps.add(userMap);
            System.out.println(userMap);
        }
        return Result.success(userMaps);
    }
}

