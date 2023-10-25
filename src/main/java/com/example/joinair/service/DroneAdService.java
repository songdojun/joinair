package com.example.joinair.service;



import com.example.joinair.entity.Drone;
import com.example.joinair.entity.Product;
import com.example.joinair.repository.DroneAdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DroneAdService {

    private static DroneAdRepository droneAdRepository;

    @Autowired
    public DroneAdService(DroneAdRepository droneAdRepository) {
        this.droneAdRepository = droneAdRepository;
    }

    public static void registDrone(Drone drone, MultipartFile file) throws Exception {
        String projectpath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectpath, fileName);
        file.transferTo(saveFile);

        drone.setD_Filename(fileName);
        drone.setD_Filepath("/files/" + fileName);

        // 현재 날짜와 시간으로 D_Reg_Date 설정  => (작성일자 나타났다 사라지는 문제 발생하여 만들었음)
        drone.setD_Reg_Date(new Date());

        droneAdRepository.save(drone);
    }

    public List<Drone> droneadList() {
        return droneAdRepository.findAll();
    }


        // 이전 코드 생략

    public Page<Drone> droneadSearchList(String searchOption, String searchKeyword, Pageable pageable) {
        Page<Drone> list;

        // Pageable 객체를 적절하게 설정
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("D_Code").descending());

        if ("D_Code".equals(searchOption)) {
            list = droneAdRepository.findDronesByDCode(Integer.parseInt(searchKeyword), pageable);
        } else if ("D_Name".equals(searchOption)) {
            list = droneAdRepository.findDronesByDNameContaining(searchKeyword, pageable);
        }else {
            list = droneAdRepository.findAllOrderedByDCodeWithPagination(pageable);
        }
        return list;
    }



    public Optional<Drone> droneadView(Integer D_Code) {
        Optional<Drone> optionalDrone = droneAdRepository.findById(D_Code);
        return optionalDrone;
    }

    public void droneadDelete(Integer D_Code) {
        droneAdRepository.deleteById(D_Code);
    }

    public Page<Drone> droneadListWithPagination(Pageable pageable) {
        return droneAdRepository.findAllOrderedByDCodeWithPagination(pageable);
    }
}
