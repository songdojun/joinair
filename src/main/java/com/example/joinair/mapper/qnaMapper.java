package com.example.joinair.mapper;

import com.example.joinair.dto.QNA;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface qnaMapper {
    public List<QNA> qnaList();

    QNA qna(QNA QNA_CO);

    int qnainsert(QNA qna);

    int qnaupdate(QNA qna);

    void qnadelete(QNA QNA_NO);
}
