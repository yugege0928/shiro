package com.lanou.controller;

import com.lanou.exception.CustomExcetion;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yugege on 17/11/7.
 */
@Controller
public class MainController {

    @RequestMapping(value = "/home")
    public String frontPage(){
        return "/home";
    }

    //用户登录页面
    @RequestMapping(value = "/login")
    public String login(){

//        方式一:
        if (SecurityUtils.getSubject().isAuthenticated()){
            return "home";
        }
        return "login";
    }

    //错误界面
    @RequestMapping(value = "/error")
    public String error(){
        return "error";
    }

    @RequestMapping(value = "/logout")
    public String logout(){
        if (SecurityUtils.getSubject().isAuthenticated()){
            SecurityUtils.getSubject().logout();
        }
        return "login";
    }

    //用户登录表单验证
    @RequestMapping(value = "/loginsubmit",method = RequestMethod.POST)
    public String loginsubmit(HttpServletRequest request) throws Exception {

        //shiro在认证过程中出现错误后,将异常类路径通过request返回
        String exceptionClassName =
                (String) request.getAttribute("shiroLoginFailure");

        if (exceptionClassName.equals(UnknownAccountException.class.getName())){
            throw new CustomExcetion("账户名不存在");
        }else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)){
            throw new CustomExcetion("密码错误");
        }else {
            throw new Exception();
        }

    }

}
