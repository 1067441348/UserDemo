package com.example.syl.myapplication_test.bean;

import java.util.List;

/**
 * Created by SYL on 2017/11/7.
 */

public class Translation1 {
    private String type;
    private int errorCode;
    private int elapsedTime;
    private List<List<TranslateResult>> translateResult;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(int elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public List<List<TranslateResult>> getTranslateResult() {
        return translateResult;
    }

    public void setTranslateResult(List<List<TranslateResult>> translateResult) {
        this.translateResult = translateResult;
    }
}
