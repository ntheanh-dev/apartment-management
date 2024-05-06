package com.ou.controllers;

import com.ou.dto.Room;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/room")
public class RoomControllers {

    @GetMapping("/")
    public String index() {
        return "roomIndex";
    }


    @GetMapping("/{roomId}/add-tenant")
    public String roomAddTenant(@PathVariable String roomId) {
        return "roomAddTenant";
    }

    @GetMapping("/create")
    public String roomCreation() {
        return "roomCreation";
    }

    @GetMapping("/{roomId}/edit")
    public String roomEdit(Model model, @PathVariable String roomId) {
        // Lấy thông tin phòng bằng id rồi hiển thị ra giao diện
        Room room = Room.builder().roomName("P01").capacity(2).male("1").price(3000000).width(5)
                        .length(10).description("Đây là phòng vip").location("Tầng 1").build();
        model.addAttribute("room", room);
        return "roomEdit";
    }

    @GetMapping("/list")
    public String roomList() {
        return "roomList";
    }

    @GetMapping("/detail")
    public String roomDetail() {
        return "roomDetail";
    }

    @GetMapping("/tenants")
    public String listTenant() {
        return "listTenant";
    }
}
