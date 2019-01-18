package com.mojito.mojitoboot.common.error;
/**
 * @Auther: Mojito
 * @Date: 2019/1/16 18:48
 * @Description:
 */
public class BusinessException extends Exception implements CommonError {

    private CommonError commonError;

    public BusinessException(CommonError commonError) {
        this.commonError = commonError;
    }

    public BusinessException(CommonError commonError, String errMsg) {
        this.commonError = commonError;
        this.commonError.setErrMsg(errMsg);
    }

    @Override
    public int getErrCode() {
        return this.commonError.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return this.commonError.getErrMsg();
    }

    @Override
    public void setErrMsg(String errMsg) {
        this.commonError.setErrMsg(errMsg);
    }
}
