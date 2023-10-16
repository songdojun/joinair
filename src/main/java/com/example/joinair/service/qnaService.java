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
        return qnaMapper.qnaList();
    }

    public QNA qna(QNA qna_No) {
        return qnaMapper.qna(qna_No);
    }

    public boolean qnainsert(QNA qna) {
        int cnt = qnaMapper.qnainsert(qna);
        return cnt > 0;
    }

    public boolean qnaupdate(QNA qna_No) {
        int cnt = qnaMapper.qnaupdate(qna_No);
        return cnt > 0;
    }

    public void qnadelete(QNA qna_No) {
        qnaMapper.qnadelete(qna_No);
    }
}
