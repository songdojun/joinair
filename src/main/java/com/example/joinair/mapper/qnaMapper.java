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

    void qnaupdateCommand(QNA qna); //댓글

    void qnadelete(QNA QNA_NO);


//    public List<QNA> qnaListWithPaging(int startIndex, int endIndex, String keyword);

    int getTotalItemCount();
}
