package com.example.joinair.mapper;

import com.example.joinair.dto.QNA;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface qnaMapper {
    public List<QNA> qnaList();

    QNA qna(int qna_No);

    int qnainsert(QNA qna);

    int qnaupdate(QNA qna);

    int qnadelete(int qna_No);
}
