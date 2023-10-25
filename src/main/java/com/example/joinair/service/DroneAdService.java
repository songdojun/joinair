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
import java.io.IOException;
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

    public void registDrone(Drone drone, MultipartFile file) throws Exception {
        // 이미지를 저장할 경로
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        // 파일 이름을 유일하게 만듭니다.
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        // 저장할 파일 객체 생성
        File saveFile = new File(projectPath, fileName);

        // 이미지 파일을 저장
        try {
            file.transferTo(saveFile);
        } catch (IOException e) {
            throw new Exception("이미지 업로드 중 오류 발생: " + e.getMessage());
        }

        // 드론 엔티티에 파일 정보 설정
        drone.setD_Filename(fileName);
        drone.setD_Filepath("/files/" + fileName);

        // 현재 날짜와 시간으로 D_Reg_Date 설정
        drone.setD_Reg_Date(new Date());

        // 드론 정보를 저장
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
