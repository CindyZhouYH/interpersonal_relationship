package com.relation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.relation.helper.Parser;
import com.relation.pojo.EntranceInformation;
import com.relation.pojo.State;
import com.relation.pojo.User;
import com.relation.service.Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
@RequestMapping("/user")
public class Login {

    private User getUserFromRequest(HttpServletRequest request) {
        System.out.println("get user");
        HttpSession sess=request.getSession();
        return (User)sess.getAttribute("user");
    }

    @RequestMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("key") String key,
                        HttpSession session, Model model) throws SQLException, JsonProcessingException {
        User searchUser = Service.UserService.searchUser(username);
        if (!searchUser.getKey().equals(key)) {
            model.addAttribute("msg", "密码错误");
            System.out.println("wrong password!");
            return "redirect:/login.jsp";
        }
        session.setAttribute("user", searchUser);
        ObjectMapper mapper=new ObjectMapper();
        model.addAttribute("msg","登陆成功！" + mapper.writeValueAsString(searchUser));
        return "redirect:/index.jsp";
    }

    @RequestMapping("/checkLoginState")
    public void checkLoginState(HttpServletRequest request,
                                 HttpServletResponse response) throws IOException, SQLException {
        System.out.println("inside");

        User user = getUserFromRequest(request);
        if(user==null){
            response.getWriter().print(Parser.objectToJson(new State("0")));
        }else {
            response.getWriter().print(Parser.objectToJson(new State("1")));
        }
       // response.getWriter().print(Parser.objectToJson(new State("0")));
    }
}
