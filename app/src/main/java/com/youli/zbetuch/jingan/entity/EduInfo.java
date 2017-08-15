package com.youli.zbetuch.jingan.entity;

/**
 * Created by liutao on 2017/8/15.
 */

public class EduInfo {

    private String name;
    private String edu;
    private String major;
    private String startTime;
    private String endTime;

    public EduInfo(String name,String edu, String major,  String startTime, String endTime) {
        this.edu = edu;
        this.endTime = endTime;
        this.major = major;
        this.name = name;
        this.startTime = startTime;
    }

    public String getEdu() {
        return edu;
    }

    public void setEdu(String edu) {
        this.edu = edu;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
