package com.example.joinair.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QNAPAGE {
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    private int page; // 현재 페이지
    private int pageSize; // 페이지당 항목 수
}
