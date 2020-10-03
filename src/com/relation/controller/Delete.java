package com.relation.controller;

import com.relation.pojo.User;
import com.relation.service.Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
@RequestMapping("/user")
public class Delete {

    @RequestMapping("/delete")
    public String delete(@RequestParam("username") String username,
                         @RequestParam("name") String name,
                         @RequestParam("email") String email,
                         @RequestParam("key") String key,
                         Model model) throws SQLException {
        User searchUser = Service.UserService.searchUser(username);
        if (searchUser.getKey().equals(key)) {
            boolean ans = Service.UserService.deleteUser(username);
            if (!ans) {
                model.addAttribute("msg", "注销失败" + ans);
                return "showMessage";
            }
            model.addAttribute("msg", "注销成功,再见!");
        } else {
            model.addAttribute("msg", "密码错误");
        }
        return "showMessage";
    }
}
