package com.example.joinair.mapper;

import com.example.joinair.dto.QNA;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface qnaMapper {
    public List<QNA> qnaList(QNA dto);

    QNA qna(QNA QNA_No);

    int qnainsert(QNA qna);

    int qnaupdate(QNA qna);

    void qnaupdateCommand(QNA qna);

    void qnadelete(QNA QNA_NO);


    List<QNA> qnaListWithPaging(int startIndex, int endIndex, String keyword);

    int getTotalItemCountWithKeyword(String keyword);

    // 새로운 메서드 추가
    void qnaupdateView(QNA QNA_NO);
}
