package com.realworld.global.category;

public enum GroupCategory {
    BOY_GROUP("남성그룹", "0001"),
    GIRL_GROUP("여자그룹", "0002"),
    MALE_ACTOR("남배우", "0003"),
    ACTRESS("여배우", "0004"),
    MALE_SOLO("남자솔로", "0005"),
    FEMALE_SOLO("여자솔로", "0006"),
    CHARACTER("방송/예능/캐릭터", "0007");

    private final String name;
    private final String code;

    GroupCategory(String name, String code) {
        this.name = name;
        this.code = code;
    }
    
}
