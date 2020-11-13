package com.mstech.gamesnatcherz.model;

/** HARISH GADDAM */

public class TextQuizQestionListModel {
    private String strQuizId;
    private String strQuizName;
    private String strQuizQuestionId;
    private String strQuizAnswerId;
    private String strQuizCode;
    private String strStartDateString;
    private String strEndDateString;
    private String strQuizImagePath;
    private String strIsFinished;
    private String isEndTime;

    public String getIsEndTime() {
        return isEndTime;
    }

    public void setIsEndTime(String isEndTime) {
        this.isEndTime = isEndTime;
    }

    public TextQuizQestionListModel(String strQuizId, String strQuizName, String strQuizQuestionId, String strQuizAnswerId, String strQuizCode, String strStartDateString, String strEndDateString, String strQuizImagePath, String strIsFinished, String isEndTime) {
        this.strQuizId = strQuizId;
        this.strQuizName = strQuizName;
        this.strQuizQuestionId = strQuizQuestionId;
        this.strQuizAnswerId = strQuizAnswerId;
        this.strQuizCode = strQuizCode;
        this.strStartDateString = strStartDateString;
        this.strEndDateString = strEndDateString;
        this.strQuizImagePath = strQuizImagePath;
        this.strIsFinished = strIsFinished;
        this.isEndTime = isEndTime;
    }

    public String getStrQuizId() {
        return strQuizId;
    }

    public void setStrQuizId(String strQuizId) {
        this.strQuizId = strQuizId;
    }

    public String getStrQuizName() {
        return strQuizName;
    }

    public void setStrQuizName(String strQuizName) {
        this.strQuizName = strQuizName;
    }

    public String getStrQuizQuestionId() {
        return strQuizQuestionId;
    }

    public void setStrQuizQuestionId(String strQuizQuestionId) {
        this.strQuizQuestionId = strQuizQuestionId;
    }

    public String getStrQuizAnswerId() {
        return strQuizAnswerId;
    }

    public void setStrQuizAnswerId(String strQuizAnswerId) {
        this.strQuizAnswerId = strQuizAnswerId;
    }

    public String getStrQuizCode() {
        return strQuizCode;
    }

    public void setStrQuizCode(String strQuizCode) {
        this.strQuizCode = strQuizCode;
    }

    public String getStrStartDateString() {
        return strStartDateString;
    }

    public void setStrStartDateString(String strStartDateString) {
        this.strStartDateString = strStartDateString;
    }

    public String getStrEndDateString() {
        return strEndDateString;
    }

    public void setStrEndDateString(String strEndDateString) {
        this.strEndDateString = strEndDateString;
    }

    public String getStrQuizImagePath() {
        return strQuizImagePath;
    }

    public void setStrQuizImagePath(String strQuizImagePath) {
        this.strQuizImagePath = strQuizImagePath;
    }

    public String getStrIsFinished() {
        return strIsFinished;
    }

    public void setStrIsFinished(String strIsFinished) {
        this.strIsFinished = strIsFinished;
    }
}
