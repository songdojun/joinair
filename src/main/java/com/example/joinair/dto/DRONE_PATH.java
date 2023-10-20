package com.example.joinair.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DRONE_PATH {
    private Long DP_Code; // 드론경로코드
    private Long D_Code; // 드론식별코드
    private String DP_place; // 도착장소
    private String DP_Info; // 경로정보
    private String DP_Stime; // 소요시간
    private String DP_Splacee; // 출발장소
}
