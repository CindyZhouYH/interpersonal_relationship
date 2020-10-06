package com.relation.controller;

import com.relation.pojo.User;
import com.relation.service.Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Controller
@RequestMapping("/user")
public class Register {
    @RequestMapping("/register")
    public String register(@RequestParam("username") String username,
                         @RequestParam("key") String key,
                         @RequestParam("confirmKey") String confirmKey,
                         @RequestParam("name") String name,
                         @RequestParam("email") String email,
                         Model model) throws SQLException {
        if (!key.equals(confirmKey)) {
            model.addAttribute("msg", "确认密码与密码不符");
            return "redirect:/register_1.jsp";
        }
        User newUser = new User(username, name, email, key);
        int ans = Service.UserService.addUser(newUser);
        if (ans!=1) {
            model.addAttribute("msg", "注册失败"+ans);
            return "redirect:/register_1.jsp";
        }
        model.addAttribute("msg", "注册成功,欢迎!");
        return "redirect:/index.jsp";
    }

    @RequestMapping("/register/checkEmailDuplicate")
    public void checkEmailDuplicate(@RequestParam("email") String email,
                                    HttpServletResponse response) throws SQLException, IOException {
        if (Service.UserService.searchSameEmailUser(email)) {
            System.out.println("register email dup!");
            response.getWriter().print("Duplicated");
        } else {
            System.out.println("register email ok");
            response.getWriter().print("OK");
        }
    }
}
