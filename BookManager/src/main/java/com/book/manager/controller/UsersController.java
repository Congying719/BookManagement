package com.book.manager.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.book.manager.entity.Users;
import com.book.manager.service.UserService;
import com.book.manager.util.Response;
import com.book.manager.util.http.CodeEnum;
import com.book.manager.util.vo.PageOut;
import com.book.manager.util.ro.PageIn;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 * @Description 用户管理

 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
public class UsersController {

    @Autowired
    private UserService userService;

    @ApiOperation("用户列表")
    @PostMapping("/list")
    public Response getUsers(@RequestBody PageIn pageIn) {
        if (pageIn == null) {
            return Response.fail(CodeEnum.PARAM_ERROR);
        }
        // 封装分页出参对象
        PageInfo<Users> userList = userService.getUserList(pageIn);
        PageOut pageOut = new PageOut();
        pageOut.setCurrPage(userList.getPageNum());
        pageOut.setPageSize(userList.getPageSize());
        pageOut.setTotal((int) userList.getTotal());
        pageOut.setList(userList);
        return Response.success(CodeEnum.SUCCESS,pageOut);
    }


    /**
     * 添加读者操作
     * @param users
     * @return
     */
    @ApiOperation("添加读者")
    @ResponseBody
    @PostMapping("/addReader")
    public Response addReader(Users users) {
        if (users == null) {
            return Response.fail(CodeEnum.PARAM_ERROR);
        }
        if(StringUtils.isEmpty(users.getAvatar())) {
            return Response.fail(CodeEnum.USER_HEAD_PIC_ERROR);
        }
        if(StringUtils.isEmpty(users.getUsername())) {
            return Response.fail(CodeEnum.USERNAME_NOT_EXIST_ERROR);
        }
        if(StringUtils.isEmpty(users.getNickname())) {
            return Response.fail(CodeEnum.NICKNAME_NOT_EXIST_ERROR);
        }
        if(StringUtils.isEmpty(users.getTel())) {
            return Response.fail(CodeEnum.USER_MOBILE_NOT_EXIST_ERROR);
        }
        if(StringUtils.isEmpty(users.getEmail())) {
            return Response.fail(CodeEnum.USER_EMAIL_NOT_EXIST_ERROR);
        }
        Users byUsername = userService.findByUsername(users.getUsername());
        if(byUsername!=null){
            return Response.fail(CodeEnum.USER_NAME_IS_EXIST_ERROR);
        }
        // 读者默认是普通用户
        users.setIsAdmin(1);
        Users users1 = userService.addUser(users);
        if(users1==null){
            return Response.fail(CodeEnum.USER_ADD_ERROR);
        }
        return Response.success(CodeEnum.SUCCESS);
    }


    /**
     * 编辑用户操作
     * @param users
     * @return
     */
    @ApiOperation("编辑用户")
    @ResponseBody
    @PostMapping("/edit")
    public Response modifyUsers(Users users) {
        if (users == null) {
            return Response.fail(CodeEnum.PARAM_ERROR);
        }
        if(StringUtils.isEmpty(users.getAvatar())) {
            return Response.fail(CodeEnum.USER_HEAD_PIC_ERROR);
        }
        if(StringUtils.isEmpty(users.getUsername())) {
            return Response.fail(CodeEnum.USERNAME_NOT_EXIST_ERROR);
        }
        if(StringUtils.isEmpty(users.getNickname())) {
            return Response.fail(CodeEnum.NICKNAME_NOT_EXIST_ERROR);
        }
        if(StringUtils.isEmpty(users.getTel())) {
            return Response.fail(CodeEnum.USER_MOBILE_NOT_EXIST_ERROR);
        }
        if(StringUtils.isEmpty(users.getEmail())) {
            return Response.fail(CodeEnum.USER_EMAIL_NOT_EXIST_ERROR);
        }
        Users byUsername = userService.findByUsername(users.getUsername());
        if(byUsername!=null){
            if(!byUsername.getId().equals(users.getId())){
                return Response.fail(CodeEnum.USER_NAME_IS_EXIST_ERROR);
            }
        }
        if(!userService.updateUser(users)){
            return Response.fail(CodeEnum.USER_EDIT_ERROR);
        }
        return Response.success(CodeEnum.SUCCESS);
    }


    @ApiOperation("用户详情")
    @GetMapping("/detail")
    public Response userDetail(Integer id) {
        Users user = userService.findUserById(id);
        if (user!=null) {
            return Response.success(CodeEnum.SUCCESS,user);
        }

        return Response.fail(CodeEnum.NOT_FOUND);
    }

    @ApiOperation("删除用户")
    @GetMapping("/delete")
    public Response delUsers(Integer id) {
        userService.deleteUser(id);
        return Response.success(CodeEnum.SUCCESS);
    }

    @ApiOperation("获取当前用户登陆信息")
    @GetMapping("/currUser")
    public Response getCurrUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal!=null) {
            Map<String,Object> map = BeanUtil.beanToMap(principal);
            String username = (String) map.get("username");
            if (StrUtil.isNotBlank(username)) {
                Users users = userService.findByUsername(username);
                return Response.success(CodeEnum.SUCCESS,users);
            }
        }
        return Response.fail(CodeEnum.USER_NOT_FOUND);
    }
}
