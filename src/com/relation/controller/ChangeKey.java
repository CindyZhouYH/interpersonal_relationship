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
public class ChangeKey {
    @RequestMapping("/changeKey")
    public String register(@RequestParam("username") String username,
                           @RequestParam("oldKey") String oldKey,
                           @RequestParam("newKey") String newKey,
                           @RequestParam("confirmKey") String confirmKey,
                           Model model) throws SQLException {
        if (!newKey.equals(confirmKey)) {
            model.addAttribute("msg", "确认密码与密码不符");
            return "showMessage";
        }
        User searchUser = Service.UserService.searchUser(username);
        if (!searchUser.getKey().equals(oldKey)) {
            model.addAttribute("msg", "密码错误");
            return "showMessage";
        }
        searchUser.setKey(newKey);
        boolean ans = Service.UserService.updateUserKey(searchUser);
        if (!ans) {
            model.addAttribute("msg", "修改密码失败");
            return "showMessage";
        }
        model.addAttribute("msg", "密码修改成功！");
        return "showMessage";
    }
}