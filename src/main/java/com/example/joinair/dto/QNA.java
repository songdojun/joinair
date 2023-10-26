package com.example.joinair.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QNA {
    public int getQNA_NO() {
        return QNA_NO;
    }

    public void setQNA_NO(int QNA_NO) {
        this.QNA_NO = QNA_NO;
    }

    public int getPRO_CODE() {
        return PRO_CODE;
    }

    public void setPRO_CODE(int PRO_CODE) {
        this.PRO_CODE = PRO_CODE;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getQNA_TITLE() {
        return QNA_TITLE;
    }

    public void setQNA_TITLE(String QNA_TITLE) {
        this.QNA_TITLE = QNA_TITLE;
    }

    public String getQNA_CONTENT() {
        return QNA_CONTENT;
    }

    public void setQNA_CONTENT(String QNA_CONTENT) {
        this.QNA_CONTENT = QNA_CONTENT;
    }

    public String getQNA_COMMAND() {
        return QNA_COMMAND;
    }

    public void setQNA_COMMAND(String QNA_COMMAND) {
        this.QNA_COMMAND = QNA_COMMAND;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    private int QNA_NO;           // 상품문의번호
    private int PRO_CODE;         // 상품코드
    private String USER_ID;       // UserID
    private String QNA_TITLE;     // 문의제목
    private String QNA_CONTENT;   // 문의내용
    private String QNA_COMMAND;   // 댓글내용
    private Boolean flag;
    private String keyword;

    @Override
    public String toString() {
        return "QNA{" +
                "QNA_NO=" + QNA_NO +
                ", PRO_CODE=" + PRO_CODE +
                ", USER_ID='" + USER_ID + '\'' +
                ", QNA_TITLE='" + QNA_TITLE + '\'' +
                ", QNA_CONTENT='" + QNA_CONTENT + '\'' +
                ", QNA_COMMAND='" + QNA_COMMAND + '\'' +
                ", flag=" + flag +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}
