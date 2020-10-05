package com.relation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.relation.helper.Parser;
import com.relation.pojo.EntranceInformation;
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
        response.getWriter().print(Parser.objectToJson(user));
    }

    @RequestMapping("/PersonalCenter/EntranceInfo")
    public void showEntranceInfo(HttpServletRequest request,
                                 HttpServletResponse response) throws IOException, SQLException {
        User user = getUserFromRequest(request);
        ArrayList<EntranceInformation> entranceInfoArr = Service.EntranceInformationService.getEntranceInformation(user);
        //System.out.println(objectToJson(entranceInfoArr));
        response.getWriter().print(Parser.objectToJson(entranceInfoArr));
    }
}
