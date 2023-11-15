package com.example.joinair.service;

import com.example.joinair.dto.QNA;
import com.example.joinair.dto.QNAPAGE;
import com.example.joinair.mapper.qnaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class qnaService {
    @Autowired
    private qnaMapper qnaMapper;

    public List<QNA> qnaList(QNA dto) {
        return qnaMapper.qnaList(dto);
    }

    public QNA qna(QNA qna_No) {
        return qnaMapper.qna(qna_No);
    }

    @Transactional
    public boolean qnainsert(QNA qna) {
        int cnt = qnaMapper.qnainsert(qna);
        return cnt > 0;
    }

    public boolean qnaupdate(QNA qna) {
        int cnt = qnaMapper.qnaupdate(qna);
        return cnt > 0;
    }
    public void qnaupdateCommand(QNA qna_No){
        qnaMapper.qnaupdateCommand(qna_No);}

    public void qnadelete(QNA qna_No) {
        qnaMapper.qnadelete(qna_No);
    }


    public List<QNA> qnaListWithPaging(QNAPAGE pagingInfo, QNA dto) {
//        // 시작 인덱스 계산
        int startIndex = (pagingInfo.getPage() - 1) * pagingInfo.getPageSize();
//
//        // 끝 인덱스 계산
        int endIndex = startIndex + pagingInfo.getPageSize();
//
        String keyword = dto.getKeyword();
//
        return qnaMapper.qnaListWithPaging(startIndex, endIndex, keyword);
    }


    public int getTotalItemCount() {
        return qnaMapper.getTotalItemCount();
    }


    public List<String> pagingList(int totalPageCount, int currentPage) {
        List<String> pageList = new ArrayList<>();

        if (totalPageCount > 0) {
            // 이전 버튼 추가
            if (currentPage > 1) {
                pageList.add("/qna/qnaList?page=" + (currentPage - 1));
            }

            // 숫자 버튼 추가
            for (int i = 1; i <= totalPageCount; i++) {
                // Thymeleaf 표현식 사용
                String pageUrl = "/qna/qnaList?page=" + i;
                pageList.add(pageUrl);
            }

            // 다음 버튼 추가
            if (currentPage < totalPageCount) {
                pageList.add("/qna/qnaList?page=" + (currentPage + 1));
            }
        }

        return pageList;
    }



}
