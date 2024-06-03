package com.ou.controllers;

import com.ou.dto.RoomRegisterDto;
import com.ou.pojo.Resident;
import com.ou.pojo.Room;
import com.ou.services.RoomServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

@Controller
@RequestMapping(path = "/room")
public class RoomControllers {

    @Autowired
    private RoomServices roomServices;
    @GetMapping("/")
    public String index(Model model,@RequestParam Map<String, String> params) {
        model.addAttribute("rooms", roomServices.getAllRooms(params));
        return "roomIndex";
    }

    @GetMapping("/{roomId}/add-tenant")
    public String roomAddTenant(Model model,@PathVariable String roomId) {
        model.addAttribute("resident",new Resident());
        return "roomAddTenant";
    }

    @GetMapping("/{roomId}/edit-tenant")
    public String roomEditTenant(@PathVariable String roomId) {
        // Lấy thông tin khách thuê phòng, dịch vụ, hợp đồng by roomId
        return "roomEditTenant";
    }

    @GetMapping("/create")
    public String roomCreation(Model model) {
        model.addAttribute("room", new Room());
        return "roomCreation";
    }

    @GetMapping("/{roomId}/edit")
    public String roomEdit(Model model, @PathVariable Integer roomId) {
        // Lấy thông tin phòng bằng id rồi hiển thị ra giao diện
        model.addAttribute("status", new ArrayList<>(Arrays.asList("còn trống", "Bảo trì")));
        model.addAttribute("room", this.roomServices.getRoomById(roomId));
        return "roomCreation";
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

    @PostMapping(value ="/create")
    public String roomCreate(Model model, @ModelAttribute(value = "room") @Valid Room r,
                             BindingResult rs) {
        if (!rs.hasErrors()) {
            try {
                this.roomServices.addOrUpdateRoom(r);
                return "redirect:/";
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                model.addAttribute("errMsg", ex.toString());
            }
        }
        return "roomIndex";
    }
    @GetMapping(value = "/{roomId}/delete")
    public String roomDelete(@PathVariable Integer roomId) {
        this.roomServices.deleteRoomById(roomId);
        return "roomIndex";
    }
}
