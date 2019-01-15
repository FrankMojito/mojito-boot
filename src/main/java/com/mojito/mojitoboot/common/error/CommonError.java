package com.mojito.mojitoboot.common.error;

public interface CommonError {

    public int getErrCode();
    public String getErrMsg();
    public void setErrMsg(String errMsg);
}
