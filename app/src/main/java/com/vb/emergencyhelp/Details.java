package com.vb.emergencyhelp;

/**
 * Created by Vaibhav on 12/11/15.
 */
public class Details {
    String name, addr, phone, blood, ename1, ename2, eno1, eno2;

    public Details() {
        name = addr = phone = blood = ename1 = ename2 = eno1 = eno2 = "";
    }

    public Details(String name, String addr, String phone, String blood, String ename, String eno1, String ename2, String eno2) {
        this.name = name;
        this.addr = addr;
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

    public void setAddr(String s) {
        this.addr = s;
    }

    public String getAddr() {
        return this.addr;
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
