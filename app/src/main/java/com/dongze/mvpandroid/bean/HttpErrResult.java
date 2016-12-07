package com.dongze.mvpandroid.bean;

public class HttpErrResult extends BaseBean {

    private int errNum;
    private String errMsg;
    private String retMsg;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public int getErrNum() {
        return errNum;
    }

    public void setErrNum(int errNum) {
        this.errNum = errNum;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    @Override
    public String toString() {
        return "HttpErrResult{" +
                "errNum=" + errNum +
                ", errMsg='" + errMsg + '\'' +
                ", retMsg='" + retMsg + '\'' +
                '}';
    }
}
