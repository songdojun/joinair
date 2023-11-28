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
        int startIndex = (pagingInfo.getPage() - 1) * pagingInfo.getPageSize();
        int endIndex = startIndex + pagingInfo.getPageSize();
        String keyword = dto.getKeyword();

        // 해당 키워드에 대한 전체 항목 수 가져오기
        int totalItemCount = qnaMapper.getTotalItemCountWithKeyword(keyword);

        // 전체 페이지 수 계산
        int totalPageCount = (int) Math.ceil((double) totalItemCount / pagingInfo.getPageSize());

        // 키워드를 기반으로 한 페이징된 QNA 목록 가져오기
        List<QNA> qnaList = qnaMapper.qnaListWithPaging(startIndex, endIndex, dto.getKeyword());


        // QNAPAGE 객체에 페이지 정보 추가
        pagingInfo.setTotalItemCount(totalItemCount);
        pagingInfo.setTotalPageCount(totalPageCount);

        return qnaList;
    }

    public int getTotalItemCountWithKeyword(String keyword) {
        return qnaMapper.getTotalItemCountWithKeyword(keyword);
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