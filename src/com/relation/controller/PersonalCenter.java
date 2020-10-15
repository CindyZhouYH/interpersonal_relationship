package com.relation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.relation.helper.Parser;
import com.relation.pojo.EntranceInformation;
import com.relation.pojo.SchoolNum;
import com.relation.pojo.User;
import com.relation.service.Service;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.relation.helper.Parser.objectToJson;

@Controller
@RequestMapping("/user")
public class PersonalCenter {

    private User getUserFromRequest(HttpServletRequest request) {
        HttpSession sess=request.getSession();
        return (User)sess.getAttribute("user");
    }

    @RequestMapping("/PersonalCenter/PersonalInfo")
    public void showPersonalInfo(HttpServletRequest request,
                                 HttpServletResponse response) throws IOException {
        User user = getUserFromRequest(request);
        response.getWriter().print(objectToJson(user));
    }

    @RequestMapping("/PersonalCenter/EntranceInfo")
    public void showEntranceInfo(HttpServletRequest request,
                                 HttpServletResponse response) throws IOException, SQLException {
        System.out.println("enter entranceInfo");
        User user = getUserFromRequest(request);
        ArrayList<EntranceInformation> entranceInfoArr = Service.EntranceInformationService.getEntranceInformation(user);
        System.out.println(objectToJson(entranceInfoArr));
        for (EntranceInformation e:entranceInfoArr){
            e.setSchoolName((Service.SchoolService.searchSchoolThrowId(e.getSchool_id())).getName());
            e.setSchoolType((Service.SchoolService.searchSchoolThrowId(e.getSchool_id())).getType());
            //System.out.println(e.toString());
        }
        response.getWriter().print(objectToJson(entranceInfoArr));
    }

    @RequestMapping("/PersonalCenter/getSchoolNum")
    public void getSchoolNum(HttpServletRequest request,
                                 HttpServletResponse response) throws IOException, SQLException {
        System.out.println("In calculation of school num");
        User user = getUserFromRequest(request);
        System.out.println("Calculating school num of user " + user.getName());
        ArrayList<EntranceInformation> entranceInfoArr = Service.EntranceInformationService.getEntranceInformation(user);
        System.out.println(entranceInfoArr);
        response.getWriter().print(objectToJson(new SchoolNum(entranceInfoArr.size())));
    }
}
