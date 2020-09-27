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
public class Delete {
    UserService us = new UserService();

    @RequestMapping("/delete")
    public String delete(@RequestParam("username") String username,
                         @RequestParam("name") String name,
                         @RequestParam("email") String email,
                         @RequestParam("key") String key,
                         Model model) throws SQLException {
        User newUser = new User(username, name, email, key);
        boolean ans = us.deleteUser(newUser);
        if (!ans) {
            model.addAttribute("msg", "注销失败" + ans);
            return "showMessage";
        }
        model.addAttribute("msg", "注销成功,再见!");
        return "showMessage";
    }
}
