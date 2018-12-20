package com.ssm.controller;

import com.ssm.dao.UserDao;
import com.ssm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @Autowired
    private UserDao userDao;
    /**
     * 访问登录时跳转页面
     *
     */
    @RequestMapping(value = "/" ,method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("msg","");
        return "login";
    }

    @RequestMapping(value = "/login" )
    public String login(Model model, HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean flag = LoginCheck(username,password);
        if(flag){
            return "userList";
        }else {
            model.addAttribute("msg","用户名或密码不正确，请重新登录");
            return "login";
        }

    }


    public boolean LoginCheck(String username,String password){
        User user = userDao.selectByUserId(username);
        if(user == null && "".equals(user)){
            return false;
        }
        if(user.getPassword().equals(password)){
            return true;
        }else{
            return false;
        }
    }
}
