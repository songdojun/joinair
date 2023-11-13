package com.example.joinair.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER("user", "일반사용자"),
    ADMIN("admin", "일반관리자");

    private final String key;
    private final String title;


}
