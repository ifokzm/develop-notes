package com.github.sailboat.common.enums;

/**
 * 所有异常状态码均需实现此接口
 */
public interface StatusCode {
    int getCode();

    String getMsg();
}