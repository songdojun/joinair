package com.example.joinair.service;

import com.example.joinair.dto.QNA;
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


//    public List<QNA> qnaListWithPaging(QNAPAGE pagingInfo, QNA dto) {
//        // 시작 인덱스 계산
//        int startIndex = (pagingInfo.getPage() - 1) * pagingInfo.getPageSize();
//
//        // 끝 인덱스 계산
//        int endIndex = startIndex + pagingInfo.getPageSize();
//
//        String keyword = dto.getKeyword();
//
//        return qnaMapper.qnaListWithPaging(startIndex, endIndex, keyword);
//    }


    public int getTotalItemCount() {
        return qnaMapper.getTotalItemCount();
    }


    public List<String> pagingList(int totalPageCount, int currentPage) {
        List<String> pageList = new ArrayList<>();

        if (totalPageCount > 0) {
            // 현재 페이지 기준으로 버튼 표시 범위 설정
            int startPage = Math.max(1, currentPage - 2); // 수정: maxButtons를 2로 설정
            int endPage = Math.min(totalPageCount, startPage + 4); // 수정: maxButtons를 4로 설정

            // 이전 버튼 추가
            if (currentPage > 1) {
                pageList.add("이전");
            }

            // 숫자 버튼 추가
            for (int i = startPage; i <= endPage; i++) {
                pageList.add(String.valueOf(i));
            }

            // 다음 버튼 추가
            if (currentPage < totalPageCount) {
                pageList.add("다음");
            }
        }

        return pageList;
    }

}
