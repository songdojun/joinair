package com.example.joinair.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DRONE_PATH {
    private Long dpCode; // 드론경로코드
    private Long dCode; // 드론식별코드
    private String dpPlace; // 도착장소
    private String dpInfo; // 경로정보
    private String dpStime; // 소요시간
    private String dpSplace; // 출발장소
}
