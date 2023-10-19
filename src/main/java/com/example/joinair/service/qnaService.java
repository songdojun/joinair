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
    public void qnaupdateCommand(QNA qna_No){
        qnaMapper.qnaupdateCommand(qna_No);}

    public void qnadelete(QNA qna_No) {
        qnaMapper.qnadelete(qna_No);
    }

    public List<QNA> qnapaging(int offset, int pageSize) {
        return qnaMapper.qnapaging(offset, pageSize);
    }
    public int qnapageNum() { //페이징 번호 처리
        int totalRecords = qnaMapper.qnapageNum(); // qnaMapper에 해당 메서드를 추가하여 레코드 수를 가져옴.
        return totalRecords;
    }


}
