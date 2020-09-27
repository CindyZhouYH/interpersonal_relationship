package com.relation.controller;

import com.relation.pojo.User;
import com.relation.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
@RequestMapping("/user")
public class PersonalCenter {
    UserService us = new UserService();

    @RequestMapping("/PersonalCenter")
    public String showPersonalInfo(@RequestParam("username") String username,
                         @RequestParam("key") String key,
                         Model model) throws SQLException {
        User searchUser = us.searchUser(username);
        if (!searchUser.getKey().equals(key)) {
            model.addAttribute("msg", "密码错误");
            return "showMessage";
        }
        model.addAttribute("msg", searchUser);
        return "showMessage";
    }
}