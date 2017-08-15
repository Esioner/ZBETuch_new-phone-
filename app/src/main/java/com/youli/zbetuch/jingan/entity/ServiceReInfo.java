package com.youli.zbetuch.jingan.entity;

/**
 * Created by liutao on 2017/8/14.
 */

public class ServiceReInfo {

    private String name;
    private String date;
    private String content;
    private String remarks;

    public ServiceReInfo(String name, String date, String content, String remarks) {
        this.name = name;
        this.date = date;
        this.content = content;
        this.remarks = remarks;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


}
