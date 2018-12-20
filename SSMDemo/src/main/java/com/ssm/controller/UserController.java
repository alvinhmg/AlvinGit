package com.ssm.controller;

import com.sprucetec.commons.data.model.QKeyword;
import com.sprucetec.commons.web.controller.ControllerSupport;
import com.ssm.model.User;
import com.ssm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController  {

@Autowired
private UserService service;
    @RequestMapping("/query")
    public String query(@RequestParam(value = "username", required = true) String username, Model model) {
        User user = new User();
        user = service.queryByUsername(username);
        model.addAttribute("user", user);
        return "test";
    }


}
