package com.mojito.mojitoboot.web.response.error;
/**
 * @Auther: Mojito
 * @Date: 2019/1/16 18:30
 * @Description:
 */
public interface CommonError {

    int getErrCode();
    String getErrMsg();
    void setErrMsg(String errMsg);
}
