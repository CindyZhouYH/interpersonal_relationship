package com.relation.controller;

import com.relation.pojo.BirthInformation;
import com.relation.pojo.EntranceInformation;
import com.relation.pojo.SchoolNum;
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
import java.util.*;

import static com.relation.helper.Parser.objectToJson;

@Controller
@RequestMapping("/relations")
public class SearchRelation {

    // for search relations
    private ArrayList<Relation> searchResult = new ArrayList<>();
    private HashMap<Integer, User> idMap = new HashMap<>();

    private User getUserFromRequest(HttpServletRequest request) {
        HttpSession sess = request.getSession();
        return (User) sess.getAttribute("user");
    }

    private class userStep {
        int step;
        User user;
        userStep(int step, User user){
            this.step = step;
            this.user = user;
        }

        public int getStep() {
            return step;
        }

        public User getUser() {
            return user;
        }
    }

    private ArrayList<Relation> getRelations(User curUser) throws SQLException {
        ArrayList<Relation> result = new ArrayList<>();
        HashSet<User> set=new HashSet<>();
        // classmates
        ArrayList<EntranceInformation> ei = Service.EntranceInformationService.getEntranceInformation(curUser);
        HashSet<User> classmates = new HashSet<>();
        for(EntranceInformation info: ei) {
            set = getClassmatesThroughEntranceInfo(info);
            if(set != null){
                classmates.addAll(set);
            }
        }
        // family
        HashSet<User> familyMembers = new HashSet<>();
        set = getBrothersThroughMotherFamily(curUser);
        if(set != null){
            familyMembers.addAll(getBrothersThroughMotherFamily(curUser));
        }
        set = getBrothersThroughSelfFamily(curUser);
        if(set != null){
            familyMembers.addAll(getBrothersThroughSelfFamily(curUser));
        }
        // friend
        HashSet<User> friends = new HashSet<>();
        // TO-DO
        for(User user: classmates) {
            result.add(new Relation(curUser, user, Relation.CLASSMATE));
        }
        for(User user: familyMembers) {
            result.add(new Relation(curUser, user, Relation.FAMILY));
        }
        for(User user: friends) {
            result.add(new Relation(curUser, user, Relation.FRIEND));
        }
        return result;
    }

    private void dfsForRelation(User user1, User user2, ArrayList<Relation> result, HashSet<Relation> curRelation) throws SQLException {
        if (user1 == user2) {
            result.addAll(curRelation);
            for (Relation relation: curRelation) {
                idMap.put(relation.getUser_1().getId(), relation.getUser_1());
                idMap.put(relation.getUser_2().getId(), relation.getUser_2());
            }
            return;
        }
        if (curRelation.size() > 10) {
            return;
        }
        ArrayList<Relation> allRelation = getRelations(user1);
        for(Relation relation: allRelation) {
            if (curRelation.contains(relation)) {
                continue;
            }
            User curUser = relation.getUser_2();
            curRelation.add(relation);
            dfsForRelation(curUser, user2, result, curRelation);
            curRelation.remove(relation);
        }
    }

    @RequestMapping("/getall")
    public void getAllUsers(HttpServletRequest request,
                            HttpServletResponse response) throws SQLException, IOException {
        System.out.println("inside back getall");
        idMap.clear();
        System.out.println("GET ALL -----------------------");
        User user1 = getUserFromRequest(request);
        System.out.println("USER1: " + user1.getName());
        System.out.println("user2name "+ request.getParameter("name"));
        User user2 = Service.UserService.getUserThroughName(request.getParameter("name"));
        System.out.println("USER2: " + user2.getName());

        ArrayList<Relation> result = new ArrayList<>();
        HashSet<Relation> curRelation = new HashSet<>();
        dfsForRelation(user1, user2, result, curRelation);
        for(Relation res: result) {
            System.out.println(res.getUser1() + " " + res.getUser2() + " " + res.getType());
        }
        this.searchResult = result;
        System.out.println("return from getall");
        System.out.println("idmap:  " + idMap.toString());
        response.getWriter().print(objectToJson(idMap.entrySet().toArray()));
    }

    @RequestMapping("/search")
    public void searchRelation(HttpServletRequest request,
                               HttpServletResponse response) throws SQLException, IOException {
        //return "redirect:/showRelation.jsp";
        response.getWriter().print(objectToJson(searchResult));
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
        if(info != null){
            int selfId = info.getPatriarchal_family_id();
            HashSet<User> brothers = new HashSet<>();
            ArrayList<BirthInformation> brothersInfo = Service.BirthInfoService.getBrotherThroughFamilyId(selfId);
            for (BirthInformation brotherInfo : brothersInfo) {
                brothers.add(Service.UserService.getUserThroughId(brotherInfo.getUser_id()));
            }
            return brothers;
        }
        return null;
    }

    public HashSet<User> getBrothersThroughMotherFamily(User user) throws SQLException {
        BirthInformation info = Service.BirthInfoService.getBirthInfo(user.getId());
        if(info != null) {
            int motherFamilyId = info.getMaternal_family_id();
            HashSet<User> brothers = new HashSet<>();
            ArrayList<BirthInformation> brothersInfo = Service.BirthInfoService.getBrotherThroughFamilyId(motherFamilyId);
            for (BirthInformation brotherInfo : brothersInfo) {
                brothers.add(Service.UserService.getUserThroughId(brotherInfo.getUser_id()));
            }
            return brothers;
        }
        return null;
    }
}
