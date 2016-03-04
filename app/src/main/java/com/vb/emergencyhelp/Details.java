package com.vb.emergencyhelp;

/**
 * Created by Vaibhav on 12/11/15.
 */
public class Details {
    String name, address, phone, blood, ename1, ename2, eno1, eno2;

    public Details() {
        name = address = phone = blood = ename1 = ename2 = eno1 = eno2 = "";
    }

    public Details(String name, String address, String phone, String blood, String ename, String eno1, String ename2, String eno2) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.blood = blood;
        this.ename1 = ename;
        this.eno1 = eno1;
        this.ename2 = ename2;
        this.eno2 = eno2;
    }

    public void setName(String s) {
        this.name = s;
    }

    public String getName() {
        return this.name;
    }

    public void setAddress(String s) {
        this.address = s;
    }

    public String getAddress() {
        return this.address;
    }

    public void setPhone(String s) {
        this.phone = s;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setBlood(String s) {
        this.blood = s;
    }

    public String getBlood() {
        return this.blood;
    }

    public void setEname1(String s) {
        this.ename1 = s;
    }

    public String getEname1() {
        return this.ename1;
    }

    public void setEname2(String s) {
        this.ename2 = s;
    }

    public String getEname2() {
        return this.ename2;
    }

    public void setEno1(String s) {
        this.eno1 = s;
    }

    public String getEno1() {
        return this.eno1;
    }

    public void setEno2(String s) {
        this.eno2 = s;
    }

    public String getEno2() {
        return this.eno2;
    }
}
