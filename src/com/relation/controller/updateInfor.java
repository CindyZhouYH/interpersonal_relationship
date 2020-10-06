package com.relation.controller;

import com.relation.pojo.User;
import com.relation.service.Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.HttpConstraintElement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@Controller
@RequestMapping("/user")
public class updateInfor {

    private User getUserFromRequest(HttpServletRequest request) {
        HttpSession sess=request.getSession();
        return (User)sess.getAttribute("user");
    }

    @RequestMapping("/updateInfor2")
    public String updateInfor2(HttpServletRequest request,
                           HttpServletResponse response) throws SQLException, IOException {
        User user = getUserFromRequest(request);
        System.out.println(request);
        String username=request.getParameter("username");
        System.out.println(username);
        String password=request.getParameter("password");
        System.out.println(password);
        boolean ans;
        if(!user.getUsername().equals(username)){
            ans=Service.UserService.updateUserUsername(username, user.getId());
            if(ans){
                response.getWriter().print("Successfully update username from "+user.getUsername()+" to " +username);
            }else{
                response.getWriter().print("Failed to update username from "+user.getUsername()+" to " +username);
            }
        }
        if(!user.getKey().equals(password)){
            ans=Service.UserService.updateUserKey(password, user.getId());
            if(ans){
                response.getWriter().print("Successfully update password from "+user.getKey()+" to " +password);
            }else{
                response.getWriter().print("Failed to update password from "+user.getKey()+" to " +password);
            }
        }
        return "redirect:/user_center.jsp";
    }

    @RequestMapping("/updateInfor")
    public String updateInfor(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("name") String name,
                           @RequestParam("email") String email,
                           HttpServletRequest request,
                           HttpServletResponse response) throws SQLException, IOException {
        User user = getUserFromRequest(request);
        boolean ans;
        if(!user.getUsername().equals(username)){
            ans=Service.UserService.updateUserUsername(username, user.getId());
            if(ans){
                response.getWriter().print("Successfully update username from "+user.getUsername()+" to " +username);
            }else{
                response.getWriter().print("Failed to update username from "+user.getUsername()+" to " +username);
            }
        }
        if(!user.getKey().equals(password)){
            ans=Service.UserService.updateUserKey(password, user.getId());
            if(ans){
                response.getWriter().print("Successfully update password from "+user.getKey()+" to " +password);
            }else{
                response.getWriter().print("Failed to update password from "+user.getKey()+" to " +password);
            }
        }
        return "redirect:/user_center.jsp";
    }
}