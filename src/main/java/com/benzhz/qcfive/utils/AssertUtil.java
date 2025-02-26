package com.benzhz.qcfive.utils;

import java.util.regex.Pattern;


/**
 * @Author：zhz
 * @Package：com.benzhz.qcfive.utils
 * @Project：qc-five
 * @name：AssertUtil
 * @Date：2025/2/16 22:15
 * @Filename：AssertUtil
 */
public class AssertUtil {


    /**
     * 正则匹配
     *
     * @param text
     * @return
     */
    public static boolean isMatch(String text, Pattern pattern) {
        if (text == null || text.trim().isEmpty()) {
            return false;
        }
        // 判断是否匹配
        return pattern.matcher(text).matches();
    }
}
