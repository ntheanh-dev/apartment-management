package com.ou.controllers;

import com.ou.dto.RoomRegisterDto;
import com.ou.pojo.Resident;
import com.ou.pojo.Room;
import com.ou.repositories.ContractRepository;
import com.ou.services.*;
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
    private ResidentService residentServices;
    @Autowired
    private MemberInRoomServices memberInRoomServices;
    @Autowired
    private ContractSerivces contractSerivces;
    @Autowired
    private UserService userService;
    @Autowired
    private RoomServices roomServices;
    @Autowired
    private ServiceServices serviceServices;
    @GetMapping("/")
    public String index(Model model,@RequestParam Map<String, String> params) {
        model.addAttribute("rooms", roomServices.getAllRooms(params));
        return "roomIndex";
    }

    @GetMapping("/{roomId}/add-tenant")
    public String roomAddTenant(Model model,@PathVariable Integer roomId) {
        Room room = roomServices.getRoomById(roomId);
        model.addAttribute("resident",new RoomRegisterDto(room.getMaximum()));
        model.addAttribute("room",room);
        model.addAttribute("service",serviceServices.getServices());
        return "roomAddTenant";
    }

    @GetMapping("/{roomId}/edit-tenant")
    public String roomEditTenant(Model model, @PathVariable Integer roomId) {
        Room room = roomServices.getRoomById(roomId);
        RoomRegisterDto roomRegisterDto = new RoomRegisterDto(contractSerivces.getContract(room),memberInRoomServices.getMemberInRooms(contractSerivces.getContract(room)),room.getMaximum());
        model.addAttribute("resident",roomRegisterDto);
        model.addAttribute("room",room);
        model.addAttribute("service",serviceServices.getServices());
        return "roomAddTenant";
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
    public String listTenant(Model model) {
        System.out.println(this.residentServices.getAllResidents());
        model.addAttribute("residents",this.residentServices.getAllResidents());
        return "listTenant";
    }

    @PostMapping(value ="/create")
    public String roomCreate(Model model, @ModelAttribute(value = "room") @Valid Room r,
                             BindingResult rs) {
        if (!rs.hasErrors()) {
            try {
                this.roomServices.addOrUpdateRoom(r);
                return "redirect:/room/";
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                model.addAttribute("errMsg", ex.toString());
            }
        }
        return "roomCreation";
    }
    @PostMapping("/{roomId}/add-tenant")
    public String roomPostAddTenant(Model model, @ModelAttribute(value = "resident") @Valid RoomRegisterDto r, BindingResult rs, @PathVariable Integer roomId) {
        if (!rs.hasErrors()) {
            try {
                this.userService.createContract(r,roomId);
                return "redirect:../";
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                model.addAttribute("errMsg", ex.toString());
            }
        }
        Room room = roomServices.getRoomById(roomId);
        model.addAttribute("room",room);
        model.addAttribute("service",serviceServices.getServices());
        return "roomAddTenant";
    }
    @GetMapping(value = "/{roomId}/delete")
    public String roomDelete(Model model,@PathVariable Integer roomId ) {
        try{
            this.roomServices.deleteRoomById(roomId);
        }catch (Exception ex){
            model.addAttribute("errMsg", "Đang có hợp đồng đang thuê không thể xóa!");
        }
        return "redirect:/room/";

    }
}
