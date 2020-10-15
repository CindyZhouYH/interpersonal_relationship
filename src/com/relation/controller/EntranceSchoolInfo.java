package com.relation.controller;

import com.relation.dao.addSchoolHelper;
import com.relation.pojo.*;
import com.relation.service.Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
@RequestMapping("/registerEntranceInfo")
public class EntranceSchoolInfo {

    @RequestMapping("/addFromUserCenter")
    public String addFromUserCenter(addSchoolHelper add,
                                  Model model,
                                  HttpServletRequest request) throws SQLException {
        if(add.getSchoolName1()!=null){
            boolean ans=goon(add.getSchoolName1(),add.getLevel1(), add.getYear1(), request);
            if(!ans){
                return "redirect:/register_2.jsp";
            }
        }
        if(add.getSchoolName2()!=null){
            boolean ans=goon(add.getSchoolName2(),add.getLevel2(), add.getYear2(), request);
            if(!ans){
                return "redirect:/register_2.jsp";
            }
        }
        if(add.getSchoolName3()!=null){
            boolean ans=goon(add.getSchoolName3(),add.getLevel3(), add.getYear3(), request);
            if(!ans){
                return "redirect:/register_2.jsp";
            }
        }
        if(add.getSchoolName4()!=null){
            boolean ans=goon(add.getSchoolName4(),add.getLevel4(), add.getYear4(), request);
            if(!ans){
                return "redirect:/register_2.jsp";
            }
        }
        if(add.getSchoolName5()!=null){
            boolean ans=goon(add.getSchoolName5(),add.getLevel5(), add.getYear5(), request);
            if(!ans){
                return "redirect:/register_2.jsp";
            }
        }
        return "redirect:/index.jsp";
    }


    @RequestMapping("/add")
    public String addEntranceInfo(addSchoolHelper add,
                                  Model model,
                                  HttpServletRequest request) throws SQLException {
        if(add.getSchoolName1()!=null){
            boolean ans=goon(add.getSchoolName1(),add.getLevel1(), add.getYear1(), request);
            if(!ans){
                return "redirect:/register_2.jsp";
            }
        }
        if(add.getSchoolName2()!=null){
            boolean ans=goon(add.getSchoolName2(),add.getLevel2(), add.getYear2(), request);
            if(!ans){
                return "redirect:/register_2.jsp";
            }
        }
        if(add.getSchoolName3()!=null){
            boolean ans=goon(add.getSchoolName3(),add.getLevel3(), add.getYear3(), request);
            if(!ans){
                return "redirect:/register_2.jsp";
            }
        }
        if(add.getSchoolName4()!=null){
            boolean ans=goon(add.getSchoolName4(),add.getLevel4(), add.getYear4(), request);
            if(!ans){
                return "redirect:/register_2.jsp";
            }
        }
        if(add.getSchoolName5()!=null){
            boolean ans=goon(add.getSchoolName5(),add.getLevel5(), add.getYear5(), request);
            if(!ans){
                return "redirect:/register_2.jsp";
            }
        }
        return "redirect:/index.jsp";
    }

    private boolean goon(String schoolName, String level, String year,HttpServletRequest request) throws SQLException {
        System.out.println(schoolName);
        System.out.println(level);
        System.out.println(year);
        School checkSchool= Service.SchoolService.searchSchool(schoolName);
        if(checkSchool==null){
            checkSchool=new School(schoolName, Integer.parseInt(level));
            boolean ans= Service.SchoolService.addSchool(checkSchool);
            if(!ans){
                return false;
            }
        }
        HttpSession sess=request.getSession();
        User user= (User) sess.getAttribute("user");
        EntranceInformation ei=new EntranceInformation(user.getId(),checkSchool.getId(),Integer.parseInt(year));
        System.out.println(ei);
        boolean ans= Service.EntranceInformationService.addInfo(user,checkSchool,ei);
        return ans;
    }
}
