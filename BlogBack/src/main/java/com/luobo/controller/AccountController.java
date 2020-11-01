package com.luobo.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.luobo.common.dto.LoginDto;
import com.luobo.common.lang.Result;
import com.luobo.entity.User;
import com.luobo.service.UserService;
import com.luobo.util.JwtUtils;
import com.luobo.util.StringRandom;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletResponse;

@RestController
public class AccountController {
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserService userService;


    /**
     * 默认账号密码：markerhub / 111111
     *
     */
    @CrossOrigin
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response){
        User user = userService.getOne(new QueryWrapper<User>().eq("email",loginDto.getUsername()));
        Assert.notNull(user,"用户不存在");
        if(!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))){
            return Result.fail("密码错误");
        }
        if(0 == user.getStatus()){
            return Result.fail("该账户还未验证");
        }
        String jwt = jwtUtils.generateToken(user.getId());
        response.setHeader("Authorization",jwt);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        //用户可以用另一个接口
        return Result.succ(MapUtil.builder()
                        .put("id", user.getId())
                        .put("username", user.getUsername())
                        .put("avatar", user.getAvatar())
                        .put("email", user.getEmail())
                        .map());
    }

    //退出
    @GetMapping("/logout")
    @RequiresAuthentication
    public Result logout(){
        SecurityUtils.getSubject().logout();
        return Result.succ(null);
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user){
        if(null == user.getEmail()){
            return Result.fail("请输入你的email");
        }
        User temp = userService.getOne(new QueryWrapper<User>().eq("email",user.getEmail()));
        if(temp == null){
            User newAccount  = new User();
            newAccount.setUsername(user.getUsername());
            newAccount.setPassword(SecureUtil.md5(user.getPassword()));
            newAccount.setEmail(user.getEmail());
            newAccount.setStatus(0);
            userService.saveOrUpdate(newAccount);
        }else if(0 == temp.getStatus()){
            temp.setUsername(user.getUsername());
            temp.setPassword(SecureUtil.md5(user.getPassword()));
            userService.saveOrUpdate(temp);
//            邮箱有被记录，但是redis中没有验证吗
            Jedis jedis = new Jedis("localhost");
            String str = jedis.get(user.getEmail());
            if(null == str){
                //该账号未验证，验证码过期了
                String random = StringRandom.getStringRandom(6);
                jedis.set(user.getEmail(),random);
                //设置验证码过期时间 过期时间为300秒
                jedis.expire(user.getEmail(),604800);
                jedis.close();
            }
        }else{
            return Result.fail("该email已经被注册了",null);

        }

        return Result.succ("感谢你的注册,请去检测你的邮箱，已激活账户",null);

    }
}
