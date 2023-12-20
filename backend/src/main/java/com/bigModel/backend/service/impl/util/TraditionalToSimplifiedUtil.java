package com.bigModel.backend.service.impl.util;

import com.github.houbb.opencc4j.util.ZhConverterUtil;


/**
 * 类功能描述：繁体转简体，静态方法
 */
public class TraditionalToSimplifiedUtil {
    public static String TraditionalToSimplified(String tradition) {
        String simplified = ZhConverterUtil.convertToSimple(tradition);
        return simplified;
    }
}
