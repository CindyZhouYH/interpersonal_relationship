package com.relation.controller;

import com.relation.pojo.User;
import com.relation.service.Service;
import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import static com.relation.helper.Parser.objectToJson;

@Controller
@RequestMapping("/friend")
public class Friendship {

    private User getUserFromRequest(HttpServletRequest request) {
        HttpSession sess = request.getSession();
        return (User) sess.getAttribute("user");
    }

    @RequestMapping("/addFriend")
    public void addFriend(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, SQLException {
        User user = getUserFromRequest(request);
        String opName = request.getParameter("name");
        User opUser = Service.UserService.getUserThroughName(opName);
        Service.FriendShipService.addFriendship(user.getId(), opUser.getId());
    }

    @RequestMapping("/getFriends")
    public void getFriends(HttpServletRequest request,
                           HttpServletResponse response) throws IOException, SQLException {
        User user = getUserFromRequest(request);
        HashSet<Integer> friendsId = Service.FriendShipService.getAllFriendsId(user.getId());
        ArrayList<User> friends = new ArrayList<>();
        for (Integer id : friendsId) {
            friends.add(Service.UserService.getUserThroughId(id));
        }
        response.getWriter().print(objectToJson(friends));
    }
}
