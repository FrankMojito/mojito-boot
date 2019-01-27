package com.mojito.mojitoboot.common.utils.validator;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Mojito
 * @Date: 2019/1/21 23:32
 * @Description:
 */
@Data
public class ValidationResult {

    private boolean hasError;

    private Map<String,String> errorMsgMap = new HashMap<>();

    // 格式化字符串信息获取错误结果

    public String getErrMsg(){ return  StringUtils.join(errorMsgMap.values().toArray(),","); }
}
