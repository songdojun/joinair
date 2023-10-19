package com.example.joinair.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QNAPAGE {
    private int page; // 현재 페이지
    private int pageSize; // 페이지당 항목 수
}
