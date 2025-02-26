package com.benzhz.qcfive.common.exception.enums;


import com.benzhz.qcfive.common.exception.ErrorCode;

/**
 * 全局错误码枚举
 * 0-999 系统异常编码保留
 * <p>
 * 一般情况下，使用 HTTP 响应状态码 https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Status
 * 虽然说，HTTP 响应状态码作为业务使用表达能力偏弱，但是使用在系统层面还是非常不错的
 * 比较特殊的是，因为之前一直使用 0 作为成功，就不使用 200 啦。
 *
 * @author 芋道源码
 */
public interface GlobalErrorCodeConstants {

    ErrorCode SUCCESS = new ErrorCode(0, "成功");
    ErrorCode BAD_REQUEST = new ErrorCode(400, "请求参数不正确");
    ErrorCode SAMPLE_NUM_NOT_SUIT = new ErrorCode(500, "导入数据的组内样本数量不正确，每组应为%s个样本");
    ErrorCode VALUE_CONVERT_ERROR = new ErrorCode(500, "无法将值 %s 转换为数字，忽略该样本。");
    ErrorCode NP_NUM_NOT_SAME = new ErrorCode(500, "NP 图要求所有样本数量必须相同。");


}
