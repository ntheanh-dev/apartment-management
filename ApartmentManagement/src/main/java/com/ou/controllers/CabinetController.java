package com.ou.controllers;

import com.ou.pojo.Cabinet;
import com.ou.pojo.Item;
import com.ou.pojo.Room;
import com.ou.services.CabinetService;
import com.ou.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/cabinet")
public class CabinetController {

    @Autowired
    private CabinetService cabinetService;

    @Autowired
    private ItemService itemService;

    @GetMapping("/")
    public String index(Model model,@RequestParam Map<String, String> params) {
        var cabinets = cabinetService.getAllCabinet(params);
        model.addAttribute("cabinets", cabinets);
        return "cabinetIndex";
    }

    @GetMapping("/{cabinetId}/detail")
    public String cabinetView(Model model, @PathVariable Integer cabinetId,@RequestParam Map<String, String> params) {
        var cabinetItems = cabinetService.getItemsByCabinetId(cabinetId,params);
        model.addAttribute("cabinetItems", cabinetItems);
        model.addAttribute("cabinetId", cabinetId);

        model.addAttribute("items", new Item());

        return "cabinetDetail";
    }

    @PostMapping(value ="/{cabinetId}/create")
    public String cabinetCreate(Model model, @ModelAttribute(value = "items") @Valid Item i,
                             BindingResult rs) {
//        if (!rs.hasErrors()) {
            try {
                this.itemService.addOrUpdateItem(i);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                model.addAttribute("errMsg", ex.toString());
            }
//        }
        return "redirect:/cabinet/{cabinetId}/detail";

    }

}
