package com.relation.controller;

import com.relation.pojo.EntranceInformation;
import com.relation.pojo.School;
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
import java.util.ArrayList;

@Controller
@RequestMapping("/user")
public class updateInfor {

    public String stringtolevel(String level) {
        if (level.equals("kd")) {
            return "1";
        }
        if (level.equals("ps")) {
            return "2";
        }
        if (level.equals("jhs")) {
            return "3";
        }
        if (level.equals("shs")) {
            return "4";
        }
        if (level.equals("uni")) {
            return "5";
        }
        return null;
    }

    private User getUserFromRequest(HttpServletRequest request) {
        HttpSession sess = request.getSession();
        return (User) sess.getAttribute("user");
    }

    @RequestMapping("/updateInform")
    public String updateInfor2(HttpServletRequest request,
                               HttpServletResponse response, HttpSession session) throws SQLException, IOException {
        User user = getUserFromRequest(request);
        System.out.println(request);
        String username = request.getParameter("username");
        System.out.println(username);
        String password = request.getParameter("password");
        System.out.println(password);
        boolean ans;
        if (!user.getUsername().equals(username)) {
            ans = Service.UserService.updateUserUsername(username, user.getId());
            if (ans) {
                response.getWriter().print("Successfully update username from " + user.getUsername() + " to " + username);
            } else {
                response.getWriter().print("Failed to update username from " + user.getUsername() + " to " + username);
            }
        }
        if (!user.getKey().equals(password)) {
            ans = Service.UserService.updateUserKey(password, user.getId());
            if (ans) {
                response.getWriter().print("Successfully update password from " + user.getKey() + " to " + password);
            } else {
                response.getWriter().print("Failed to update password from " + user.getKey() + " to " + password);
            }
        }
        User searchUser = Service.UserService.searchUser(username);
        session.setAttribute("user", searchUser);
        return "redirect:/user_center.jsp";
    }

    @RequestMapping("/addSchool")
    public void add_school(HttpServletRequest request,
                           HttpServletResponse response,
                           String schoolName, String level, String year) throws SQLException, IOException {
        response.getWriter().print(addEIAndUpdateSession(schoolName, stringtolevel(level), year, request));
    }

    @RequestMapping("/deleteSchool")
    public void delete_school(HttpServletRequest request,
                              HttpServletResponse response,
                              int index) throws SQLException, IOException {
        User user = getUserFromRequest(request);
        ArrayList<EntranceInformation> entranceInfoArr = Service.EntranceInformationService.getEntranceInformation(user);
        if (index >= entranceInfoArr.size()) {
            response.getWriter().print("0");
            return;
        }
        EntranceInformation ei = entranceInfoArr.get(index);
        boolean result = Service.EntranceInformationService.deleteEntranceInformation(ei);
        if (result) {
            response.getWriter().print("1");
        } else {
            response.getWriter().print("0");
        }
    }

    @RequestMapping("/updateSchool")
    public void update_school(HttpServletRequest request,
                              HttpServletResponse response,
                              int index,
                              String name,
                              String year,
                              String level) throws SQLException, IOException {
        delete_school(request, response, index);
        add_school(request, response, name, level, year);
    }

    public boolean addEIAndUpdateSession(String schoolName, String level, String year, HttpServletRequest request) throws SQLException {
        School checkSchool = Service.SchoolService.searchSchool(schoolName);
        if (checkSchool == null) {
            checkSchool = new School(schoolName, Integer.parseInt(level));
            boolean ans = Service.SchoolService.addSchool(checkSchool);
            if (!ans) {
                return false;
            }
        }
        HttpSession sess = request.getSession();
        User user = (User) sess.getAttribute("user");
        EntranceInformation ei = new EntranceInformation(user.getId(), checkSchool.getId(), Integer.parseInt(year));
        System.out.println(ei);
        boolean ans = Service.EntranceInformationService.addInfo(user, checkSchool, ei);
        return ans;
    }
}