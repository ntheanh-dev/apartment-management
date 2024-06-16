package com.ou.components;
import com.google.cloud.firestore.*;
import com.ou.pojo.User;
import com.ou.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Component
public class InitAdmin {

    @Autowired
    private UserService userService;

    @Autowired
    private FirebaseService firebaseService;

    @PostConstruct
    public void init() throws ExecutionException, InterruptedException {
        String defaultAdmin = "admin1";
        User user = null;
        if(!userService.userExistsByUsername(defaultAdmin)) {
            // Tạo admin ở db
            User u = User.builder()
                    .username(defaultAdmin)
                    .password("12345")
                    .build();
            user = userService.addAdmin(u);
        } else {
            user = this.userService.getUserByUsername(defaultAdmin);
            System.out.println("Admin account has added with username: " + defaultAdmin + ", password: 12345") ;
        }

        try {
            List<QueryDocumentSnapshot> users = this.firebaseService.getUsersByField("id",defaultAdmin);
            if(users.isEmpty()) {
                //----------- Khởi tạo collection users---------------
                Map<String,Object> userMap = new HashMap<>();
                userMap.put("id",defaultAdmin);
                userMap.put("username",defaultAdmin);
                userMap.put("role",user.getRole());

                this.firebaseService.initCollection("users",userMap);
                System.out.println("Added user: " + user.getUsername() + "to firebase");
            } else {
                if(users.get(0).getId().equals(defaultAdmin)) {
                    this.firebaseService.addUser(user);
                    System.out.println("Added user: " + user.getUsername() + "to firebase");
                }
            }
            boolean isRoomCollectionExisted = this.firebaseService.checkCollectionExist("rooms");
            if(!isRoomCollectionExisted) {
                Map<String, Object> roomMap = new HashMap<>();
                roomMap.put("members",Arrays.asList(new String[]{defaultAdmin}));
                roomMap.put("name", "Nhóm Chát Chung Cư");
                //----------- Khởi tạo collection rooms ---------------
                this.firebaseService.initCollection("rooms",roomMap);
                //----------- Khởi tạo collection messages ---------------
                Map<String, Object> messageMap = new HashMap<>();
                this.firebaseService.initCollection("messages",messageMap);
            }

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            System.out.println("Some thing went wrong with firebase");
        }
    }
}
