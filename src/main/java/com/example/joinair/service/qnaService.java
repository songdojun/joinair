package com.example.joinair.service;

import com.example.joinair.mapper.qnaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class qnaService {
    @Autowired
    private qnaMapper qnaMapper; // 변수명 수정

    public List<Map<String, Object>> qnaList() {
        List<Map<String, Object>> qnaList = qnaMapper.qnaList(); // 메서드 및 변수명 수정
        return qnaList;
    }
}
