package com.example.joinair.controller;



import com.example.joinair.entity.Drone;
import com.example.joinair.entity.Product;
import com.example.joinair.service.DroneAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import static com.example.joinair.service.DroneAdService.registDrone;


@Controller
public class DroneAdController {

    @Autowired
    private DroneAdService droneAdService;

    @GetMapping("/dronead/regist")
    public String droneadRegistForm(){return "droneadregist";}

    @PostMapping("/dronead/registpro")
    public String droneadRegistPro(Drone drone, Model model, MultipartFile file)throws Exception{
        registDrone(drone,file);

        model.addAttribute("message", "드론 기체 등록이 완료되었습니다.");
        model.addAttribute("searchUrl","/dronead/list");
        return "message";
    }

    @GetMapping("/dronead/list")
    public String droneadList(Model model,
                                @PageableDefault(page = 0, size = 10, sort = "D_Code", direction = Sort.Direction.DESC) Pageable pageable,
                                @RequestParam(name = "searchOption", required = false) String searchOption,
                                @RequestParam(name = "searchKeyword", required = false) String searchKeyword) {
        Page<Drone> list = droneAdService.droneadSearchList(searchOption, searchKeyword, pageable);


        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        // Add search parameters to the model
        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("searchKeyword", searchKeyword); // Pass search keyword to the view

        return "droneadlist";
    }



    @GetMapping("/dronead/view")
    public String droneadView(Model model, Integer D_Code){
        model.addAttribute("Drone",droneAdService.droneadView(D_Code).orElse(null));
        return "droneadview";
    }

    @GetMapping("/dronead/delete")
    public String droneadDelete(Integer D_Code){
        droneAdService.droneadDelete(D_Code);

        return "redirect:/dronead/list";
    }

    @GetMapping("/dronead/modify/{D_Code}")
    public String droneadModify(@PathVariable("D_Code") Integer D_Code, Model model){
        Drone drone = droneAdService.droneadView(D_Code).orElse(null);

        if(drone==null){
            return "redirect:/error";
        }
        model.addAttribute("drone", drone);
        return "droneadmodify";
    }

    @PostMapping("/dronead/update/{D_Code}")
    public String droneadUpdate(@PathVariable("D_Code")Integer D_Code, Drone updateDrone, Model model, MultipartFile file) throws Exception{
        Drone droneTemp = droneAdService.droneadView(D_Code).orElse(null);

        if(droneTemp !=null){
            droneTemp.setD_Name(updateDrone.getD_Name());
            droneTemp.setD_Payload(updateDrone.getD_Payload());
            droneTemp.setD_Count(updateDrone.getD_Count());
            droneTemp.setD_Size(updateDrone.getD_Size());
            droneTemp.setD_Weight(updateDrone.getD_Weight());
            droneTemp.setD_Speed(updateDrone.getD_Speed());

            registDrone(droneTemp,file);
        }

        model.addAttribute("message", "드론 기체 수정이 완료되었습니다.");
        model.addAttribute("searchUrl", "/dronead/list");
        return  "message";
    }


}
