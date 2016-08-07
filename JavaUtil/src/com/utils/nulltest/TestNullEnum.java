package com.utils.nulltest;

import java.io.Serializable;

/**
 * 
 * 枚举类
 * 
 * @author sunjie at 2016年8月3日
 *
 */
public enum TestNullEnum implements Serializable {

    ENUM_VALUE0("0", "零"),

    ENUM_VALUE1("1", "一"),

    ENUM_VALUE2("2", "二"),

    ;

    private String code;

    private String name;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    private TestNullEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static TestNullEnum getEnum(String code) {
        for (TestNullEnum e : TestNullEnum.values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }
}
