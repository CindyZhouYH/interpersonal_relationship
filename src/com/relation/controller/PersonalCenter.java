package com.relation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.relation.pojo.User;
import com.relation.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
@RequestMapping("/user")
public class PersonalCenter {
    UserService us = new UserService();

    @RequestMapping("/PersonalCenter")
    public String showPersonalInfo(Model model, HttpServletRequest request) throws SQLException, JsonProcessingException {
        HttpSession sess=request.getSession();
        User searchUser =(User)sess.getAttribute("user");
        ObjectMapper mapper=new ObjectMapper();
        model.addAttribute("msg", mapper.writeValueAsString(searchUser));
        return "redirect:/user_center.jsp";
    }
}
