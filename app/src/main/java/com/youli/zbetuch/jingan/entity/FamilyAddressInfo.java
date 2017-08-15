package com.youli.zbetuch.jingan.entity;

import java.util.List;

/**
 * Created by liutao on 2017/8/14.
 */

public class FamilyAddressInfo {

    private String title;

    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


   private List<FamilyAddressInfoList> list;

    public List<FamilyAddressInfoList> getList() {
        return list;
    }

    public void setList(List<FamilyAddressInfoList> list) {
        this.list = list;
    }

    public FamilyAddressInfo(boolean isChecked, String title, List<FamilyAddressInfoList> list) {
        this.isChecked = isChecked;
        this.title = title;
        this.list = list;
    }

    public static class FamilyAddressInfoList{

        private String name;
        private String sex;
        private String birthday;
        private String idcard;

        public FamilyAddressInfoList(String name,String sex, String birthday, String idcard) {
            this.birthday = birthday;
            this.sex = sex;
            this.name = name;
            this.idcard = idcard;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }
    }
}
