package com.dongze.mvpandroid.bean;

public class HttpResult<T> extends BaseBean {

    private T retData;
    private int errNum;
    private String retMsg;
    private String errMsg;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public T getRetData() {
        return retData;
    }

    public void setRetData(T retData) {
        this.retData = retData;
    }

    public int getErrNum() {
        return errNum;
    }

    public void setErrNum(int errNum) {
        this.errNum = errNum;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "retData=" + retData +
                ", errNum=" + errNum +
                ", retMsg='" + retMsg + '\'' +
                '}';
    }
}
