package com.relation.controller;
        import com.relation.pojo.BirthInformation;
        import com.relation.pojo.Family;
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

@Controller
@RequestMapping("/birthinfo")
public class BirthFamilyInfo {

    private User getUserFromRequest(HttpServletRequest request) {
        HttpSession sess=request.getSession();
        return (User)sess.getAttribute("user");
    }

    @RequestMapping("/addBirthInfo")
    public String register(HttpServletRequest request,
                           Model model) throws SQLException {
        User user = getUserFromRequest(request);
        String father_name=request.getParameter("father_name");
        String father_place=request.getParameter("father_place");
        String mother_name=request.getParameter("mother_name");
        String mother_place=request.getParameter("mother_place");
        String year = request.getParameter("year");
        Family family1 = Service.FamilyService.searchFamily(father_name, father_place);
        if(family1 == null){
            family1=new Family(father_name, father_place);
            Service.FamilyService.addFamily(family1);
            System.out.println("add father family");
        }
        Family family2 = Service.FamilyService.searchFamily(mother_name, mother_place);
        if(family2 == null){
            family2=new Family(mother_name, mother_place);
            Service.FamilyService.addFamily(family2);
            System.out.println("add mother family");
        }
        Service.BirthInfoService.addBirthInfo(new BirthInformation(user.getId(),family1.getId(), family2.getId(), Integer.parseInt(year)));
        System.out.println("add birthinfo");
        return "redirect:/index.jsp";
    }
}
