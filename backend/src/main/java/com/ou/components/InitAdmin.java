package com.ou.components;

import com.google.cloud.firestore.*;
import com.ou.pojo.User;
import com.ou.services.BillService;
import com.ou.services.FirebaseService;
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

    @Autowired
    private BillService billService;

    @PostConstruct
    public void init() throws ExecutionException, InterruptedException {
        String defaultAdmin = "admin1";
        User user = null;
        if (!userService.userExistsByUsername(defaultAdmin)) {
            // Tạo admin ở db
            User u = User.builder()
                    .username(defaultAdmin)
                    .password("12345")
                    .build();
            user = userService.addAdmin(u);
            System.out.println("Added admin account with username: " + defaultAdmin + ", password: 12345");
        } else {
            user = this.userService.getUserByUsername(defaultAdmin);
        }

        try {
            List<QueryDocumentSnapshot> users = this.firebaseService.getUsersByField("username", defaultAdmin);
            if (users.isEmpty()) {
                Map<String, Object> userMap = new HashMap<>();
                userMap.put("username", defaultAdmin);
                userMap.put("userId", user.getId());
                //---------------Them user--------------------------------
                this.firebaseService.addDocument("users",userMap);
                System.out.println("Added user: " + user.getUsername() + " to firebase");
            }
            boolean isRoomCollectionExisted = this.firebaseService.checkCollectionExist("rooms");
            if (!isRoomCollectionExisted) {
                Map<String, Object> roomMap = new HashMap<>();
                roomMap.put("members", Arrays.asList(new String[]{defaultAdmin}));
                roomMap.put("name", "Nhóm Chát Chung Cư");
                this.firebaseService.addDocument("rooms",roomMap);
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            System.out.println("Some thing went wrong with firebase");
        }
    }
}
