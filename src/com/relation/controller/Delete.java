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
public class Delete {

    @RequestMapping("/delete")
    public void delete(@RequestParam("username") String username,
                       @RequestParam("name") String name,
                       @RequestParam("email") String email,
                       @RequestParam("key") String key,
                       HttpServletResponse response) throws SQLException, IOException {
        User searchUser = Service.UserService.searchUser(username);
        if (searchUser.getKey().equals(key)) {
            boolean ans = Service.UserService.deleteUser(username);
            if (!ans) {
                System.out.println("Failed to log off.");
                response.getWriter().print("Failed to log off.");
                return;
            }
            System.out.println("Successfully logged off!");
            response.getWriter().print("Successfully logged off!");
        } else {
            System.out.println("Wrong password.");
            response.getWriter().print("Wrong password.");
        }
        return;
    }
}
