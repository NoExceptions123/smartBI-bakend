package com.lft.smart_bi.utils;

import org.apache.commons.lang3.StringUtils;

/**
*@Pakage com.lft.smart_bi.utils
*@auther 邮专第一深情
*@date 2023/7/8
*@Description SQL 工具
*/
public class SqlUtils {

    /**
     * 校验排序字段是否合法（防止 SQL 注入）
     *
     * @param sortField
     * @return
     */
    public static boolean validSortField(String sortField) {
        if (StringUtils.isBlank(sortField)) {
            return false;
        }
        return !StringUtils.containsAny(sortField, "=", "(", ")", " ");
    }
}
