package com.example.joinair.mapper;

import com.example.joinair.dto.QNA;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface qnaMapper {
    public List<QNA> qnaList(QNA dto);

    QNA qna(QNA QNA_No);

    int qnainsert(QNA qna);

    int qnaupdate(QNA qna);

    void qnaupdateCommand(QNA qna);

    void qnadelete(QNA QNA_NO);


    public List<QNA> qnaListWithPaging(@Param("startIndex") int startIndex, @Param("endIndex") int endIndex, @Param("keyword") String keyword);


    int getTotalItemCountWithKeyword(String keyword);

    // 새로운 메서드 추가
    void qnaupdateView(QNA QNA_NO);
}