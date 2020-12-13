package com.relation.controller;

import com.relation.pojo.BirthInformation;
import com.relation.pojo.EntranceInformation;
import com.relation.pojo.SchoolNum;
import com.relation.pojo.User;
import com.relation.service.Service;
import javafx.util.Pair;
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
            familyMembers.addAll(set);
        }
        set = getBrothersThroughSelfFamily(curUser);
        if(set != null){
            familyMembers.addAll(set);
        }
        // friend
        HashSet<User> friends = new HashSet<>();
        set = getFriends(curUser);
        if(set != null){
            friends.addAll(set);
        }
        for(User user: classmates) {
            if (curUser.getId() != user.getId()) {
                result.add(new Relation(curUser, user, Relation.CLASSMATE));
            }
        }
        for(User user: familyMembers) {
            if (curUser.getId() != user.getId()) {
                result.add(new Relation(curUser, user, Relation.FAMILY));
            }
        }
        for(User user: friends) {
            if (curUser.getId() != user.getId()) {
                result.add(new Relation(curUser, user, Relation.FRIEND));
            }
        }
        //System.out.println("getRelations : ");
//        for(Relation relation: result) {
//            System.out.println(" ! " + relation.toString());
//        }
        return result;
    }

    private void bfsForRelation(User user1, User user2, ArrayList<Relation> result) throws SQLException {
        try {

            ArrayDeque<Pair<User, Integer>> queue = new ArrayDeque<>();
            HashMap<Integer, Relation> preRelation = new HashMap<>(); // <curId, lastRelation> 链接上一个用户的relation
            HashSet<Integer> vis = new HashSet<>();
            vis.add(user1.getId());
            queue.offerLast(new Pair<User, Integer>(user1, 0));
            System.out.println(" ` offer " + user1.getId());
            idMap.put(user2.getId(), user2);
            while(!queue.isEmpty()) {
                Pair<User, Integer> pr = queue.pollFirst();
                User curUser = pr.getKey();
                int step = pr.getValue();
                System.out.println(" ~ poll " + curUser.getId() + ", step: " + step);
                if (step > 8) {
                    break;
                }
                ArrayList<Relation> allRelation = getRelations(curUser);
                for (Relation relation : allRelation) {
                    User oppUser = relation.getUser_2();
                    if (oppUser.getId() == user2.getId()) {
                        result.add(relation);
                        System.out.println(" + " + relation.getUser_1().getId() + ", " + relation.getUser_2().getId());
                        User tempUser = curUser;
                        idMap.put(tempUser.getId(), tempUser);
                        Relation tempRelation;
                        while (tempUser.getId() != user1.getId()) {
                            tempRelation = preRelation.get(tempUser.getId());
                            result.add(tempRelation);
                            System.out.println(" + " + tempRelation.getUser_1().getId() + ", " + tempRelation.getUser_2().getId());
                            tempUser = tempRelation.getUser_1();
                            idMap.put(tempUser.getId(), tempUser);
                        }
                        continue;
                    }
                    if (vis.contains(oppUser.getId())) {
                        continue;
                    }
                    vis.add(oppUser.getId());
                    preRelation.put(oppUser.getId(), relation);
                    queue.offerLast(new Pair<>(oppUser, step + 1));
                    System.out.println(" ` offer " + oppUser.getId());
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void dfsForRelation(User user1, User user2, ArrayList<Relation> result,
                                HashSet<Integer> includedUsers, HashSet<Relation> curRelation) throws SQLException {
//        System.out.println(" -- in dfs: user1 - " + user1.getName() + ", user2 - " + user2.getName());
        if (includedUsers.contains(user1.getId())) {
            return;
        }
        includedUsers.add(user1.getId());
        if (user1.getId() == user2.getId()) {
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
//            System.out.println(" Relation -> " + relation.getUser_1() + ", " + relation.getUser_2());
            User curUser = relation.getUser_2();
            curRelation.add(relation);
            dfsForRelation(curUser, user2, result, includedUsers, curRelation);
            curRelation.remove(relation);
        }
    }

    private class ReturnUser {
        private int id;
        private User user;
        public ReturnUser(int id, User user) {
            this.id = id;
            this.user = user;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }

    @RequestMapping("/getall")
    public void getAllUsers(HttpServletRequest request,
                            HttpServletResponse response) throws SQLException, IOException {
//        System.out.println("inside back getall");
        idMap.clear();
        searchResult.clear();
//        System.out.println("GET ALL -----------------------");
        User user1 = getUserFromRequest(request);
//        System.out.println("USER1: " + user1.getName());
//        System.out.println("user2name "+ request.getParameter("name"));
        User user2 = Service.UserService.getUserThroughName(request.getParameter("name"));
//        System.out.println("USER2: " + user2.getName());

        ArrayList<Relation> result = new ArrayList<>();
        //HashSet<Relation> curRelation = new HashSet<>();
        //HashSet<Integer> includedUsers = new HashSet<>();
        //dfsForRelation(user1, user2, result, includedUsers, curRelation);
        bfsForRelation(user1, user2, result);
//        for(Relation res: result) {
//            System.out.println(res.getUser1() + " " + res.getUser2() + " " + res.getType());
//        }
        this.searchResult = result;
//        System.out.println("return from getall");
//        System.out.println("idmap:  " + Arrays.toString(idMap.entrySet().toArray()));
        ArrayList<ReturnUser> returnUsers = new ArrayList<>();
        for (Integer id: idMap.keySet()) {
            returnUsers.add(new ReturnUser(id, idMap.get(id)));
        }
//        System.out.println("return json is: " + objectToJson(returnUsers));
        response.getWriter().print(objectToJson(returnUsers));
    }

    @RequestMapping("/search")
    public void searchRelation(HttpServletRequest request,
                               HttpServletResponse response) throws SQLException, IOException {
        //return "redirect:/showRelation.jsp";
        response.getWriter().print(objectToJson(searchResult));
    }


    public HashSet<User> getClassmatesThroughEntranceInfo(EntranceInformation info) throws SQLException {
        //System.out.println("getting Classmates through entranceInfo");
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

    public HashSet<User> getFriends(User user) throws SQLException {
        //System.out.println("getting friends of user " + user.getName());
        HashSet<Integer> friendsId = Service.FriendShipService.getAllFriendsId(user.getId());
        if (friendsId == null) {
            return null;
        }
        HashSet<User> friends = new HashSet<>();
        for (Integer id: friendsId) {
            User friend = Service.UserService.getUserThroughId(id);
            friends.add(friend);
            //System.out.println(friend);
        }
        //System.out.println("------------------------");
        return friends;
    }
}
