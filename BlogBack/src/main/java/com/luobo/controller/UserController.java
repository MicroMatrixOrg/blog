package com.luobo.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.luobo.common.lang.Result;
import com.luobo.entity.User;
import com.luobo.service.UserService;
import org.apache.shiro.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2020-08-06
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public Object test(@PathVariable("id") Long id) {
        User user = new User();
        user = userService.getById(id);
        Assert.notNull(user,"用户不存在");
        return Result.succ(user);
    }

    @PostMapping("/save")
    public Object testUser(@Validated @RequestBody User user){
        return user.toString();
    }

    @PostMapping("/editUserInfo")
    public Object editUserInfo(@Validated @RequestBody User user){
        User temp = null;
        temp = userService.getById(user.getId());
        Assert.isTrue(null != temp,"该用户ID不存在");
        userService.saveOrUpdate(user);
        return Result.succ(null);
    }
    
}
