package com.example.joinair.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface qnaMapper {
    List<Map<String, Object>> qnaList();
}
