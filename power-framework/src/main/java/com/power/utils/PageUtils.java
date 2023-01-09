package com.power.utils;

/**
 * @author power
 * @Date 2023/1/9 11:47
 */
public class PageUtils {

    private PageUtils() {
    }

    public static boolean isIllegal(Integer pageNum, Integer pageSize) {
        // 简单的校验
        return pageNum == null || pageSize == null || pageNum <= 0 || pageSize <= 0;
    }
}
