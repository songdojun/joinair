package com.example.joinair.service;

import com.example.joinair.dto.QNA;
import com.example.joinair.mapper.qnaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class qnaService {
    @Autowired
    private qnaMapper qnaMapper;

    public List<QNA> qnaList() {
        System.out.println("test1");
        return qnaMapper.qnaList();
    }

    public QNA qna(int qna_No) {

        return qnaMapper.qna(qna_No);
    }

    public boolean qnainsert(QNA qna) {
        int cnt = qnaMapper.qnainsert(qna);
        return cnt > 0;
    }

    public boolean qnaupdate(QNA qna) {
        int cnt = qnaMapper.qnaupdate(qna);
        return cnt > 0;
    }

    public boolean qnadelete(int qna_No) {
        int cnt = qnaMapper.qnadelete(qna_No);
        return cnt > 0;
    }
}
