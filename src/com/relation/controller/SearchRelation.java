package com.relation.controller;

import com.relation.pojo.BirthInformation;
import com.relation.pojo.EntranceInformation;
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
import java.util.HashMap;
import java.util.HashSet;

@Controller
public class SearchRelation {

    private User getUserFromRequest(HttpServletRequest request) {
        HttpSession sess = request.getSession();
        return (User) sess.getAttribute("user");
    }

    @RequestMapping("/search")
    public String searchRelation(HttpServletRequest request,
                                 HttpServletResponse response) throws SQLException {
        User user = getUserFromRequest(request);
        String name2 = request.getParameter("name");
        
        return "redirect:/showRelation.jsp";
    }

    public HashSet<User> getClassmatesThroughEntranceInfo(EntranceInformation info) throws SQLException {
        ArrayList<Integer> matesId = Service.EntranceInformationService.getClassmatesEntranceInfo(info);
        HashSet<User> mates = new HashSet<>();
        for (Integer id : matesId) {
            mates.add(Service.UserService.getUserThroughId(id));
        }
        return mates;
    }

    public HashSet<User> getBrothersThroughSelfFamily(User user) throws SQLException {
        BirthInformation info = Service.BirthInfoService.getBirthInfo(user.getId());
        int selfId = info.getPatriarchal_family_id();
        HashSet<User> brothers = new HashSet<>();
        ArrayList<BirthInformation> brothersInfo = Service.BirthInfoService.getBrotherThroughFamilyId(selfId);
        for (BirthInformation brotherInfo : brothersInfo) {
            brothers.add(Service.UserService.getUserThroughId(brotherInfo.getUser_id()));
        }
        return brothers;
    }

    public HashSet<User> getBrothersThroughMotherFamily(User user) throws SQLException {
        BirthInformation info = Service.BirthInfoService.getBirthInfo(user.getId());
        int motherFamilyId = info.getMaternal_family_id();
        HashSet<User> brothers = new HashSet<>();
        ArrayList<BirthInformation> brothersInfo = Service.BirthInfoService.getBrotherThroughFamilyId(motherFamilyId);
        for (BirthInformation brotherInfo : brothersInfo) {
            brothers.add(Service.UserService.getUserThroughId(brotherInfo.getUser_id()));
        }
        return brothers;
    }
}
