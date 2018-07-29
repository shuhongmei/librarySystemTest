package com.ly.ssm.controller;

import com.ly.ssm.model.User;
import com.ly.ssm.service.UserService;
import com.ly.ssm.utils.Md5;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Created by ly on 2017/11/30.
 */

@Controller
public class UserController {

    private Logger log = Logger.getLogger(UserController.class);
    @Resource
    private UserService userService;



    @RequestMapping("login")
    @ResponseBody
    public boolean login(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username+password);
        Md5 md = new Md5();
        String ps = md.MD5(password);
        HttpSession session = request.getSession();
        User user=userService.checkLogin(username,ps);
        if(user!=null){
            session.setAttribute("user", user.getUserName());
            return true;
        }

        return false;
    }

    @RequestMapping("register")
    @ResponseBody
    public boolean register(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        System.out.println(username+password+email);
        Md5 md = new Md5();
        String ps = md.MD5(password);
        HttpSession session = request.getSession();
        boolean rg =userService.checkRegister(username,ps,email);
        if(rg){
            return true;
        }

        return false;
    }

    @RequestMapping("logout")
    @ResponseBody
    public boolean logout(HttpServletRequest request,HttpServletResponse response){
        Object user = request.getSession().getAttribute("user");
        if(user!=null){
            request.getSession().removeAttribute("user");
            return true;
        }
        return false;
    }

    @RequestMapping("online")
    @ResponseBody
    public String online(HttpServletRequest request,HttpServletResponse response){
        Object user = request.getSession().getAttribute("user");

        if (user != null) {
            System.out.println("username"+user);
            return user.toString();
        } else {
            return null;
        }
    }
    @RequestMapping("updatepass")
    @ResponseBody
    public boolean updatePass(HttpServletRequest request,HttpServletResponse response){
        Object user = request.getSession().getAttribute("user");
        Md5 md = new Md5();
        String pss = request.getParameter("password");
        System.out.println(pss);
        String newPass = md.MD5(pss);
         userService.updatePass(newPass,user.toString());
        request.getSession().removeAttribute("user");
        return true;
    }

}
