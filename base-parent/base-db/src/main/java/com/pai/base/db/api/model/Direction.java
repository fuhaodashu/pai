package com.pai.base.db.api.model;


/**
 * 字段排序方向。
 * <pre> 
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2016-09-29 18:00:11
 * </pre>
 */
public enum Direction {
    ASC, DESC;
    
    
    public static Direction fromString(String value) {
        try {
            return Direction.valueOf(value.toUpperCase());
        } catch (Exception e) {
            return ASC;
        }
    }
}

