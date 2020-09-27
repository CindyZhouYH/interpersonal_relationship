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
public class Register {
    UserService us=new UserService();
    @RequestMapping("/register")
    public String register(@RequestParam("username") String username,
                         @RequestParam("name") String name,
                         @RequestParam("email") String email,
                         @RequestParam("key") String key,
                         @RequestParam("confirmKey") String confirmKey,
                         Model model) throws SQLException {
        if (!key.equals(confirmKey)) {
            model.addAttribute("msg", "确认密码与密码不符");
            return "showMessage";
        }
        User newUser = new User(username, name, email, key);
        int ans = us.addUser(newUser);
        if (ans!=1) {
            model.addAttribute("msg", "注册失败"+ans);
            return "showMessage";
        }
        model.addAttribute("msg", "注册成功,欢迎!");
        return "showMessage";
    }
}
