package com.config;

/**
 * Created by disp.chimc on 26.11.14.
 */
public class Config {
    private String tracker;
    private String mark;
    private String gos;
    private String name;
    private String phone;
    private String type_work;
    private String agregat;
    private String num_agreg;

    public Config(String tracker, String mark, String gos, String name, String phone, String type_work, String agregat, String num_agreg) {
        this.tracker = tracker;
        this.mark = mark;
        this.gos = gos;
        this.name = name;
        this.phone = phone;
        this.type_work = type_work;
        this.agregat = agregat;
        this.num_agreg = num_agreg;
    }

    public Config(String tracker, String mark, String gos, String name, String phone, String type_work) {
        this.tracker = tracker;
        this.mark = mark;
        this.gos = gos;
        this.name = name;
        this.phone = phone;
        this.type_work = type_work;
        this.agregat="";
        this.num_agreg="";
    }

    public String getTracker() {
        return tracker;
    }

    public void setTracker(String tracker) {
        this.tracker = tracker;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getGos() {
        return gos;
    }

    public void setGos(String gos) {
        this.gos = gos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType_work() {
        return type_work;
    }

    public void setType_work(String type_work) {
        this.type_work = type_work;
    }

    public String getAgregat() {
        return agregat;
    }

    public void setAgregat(String agregat) {
        this.agregat = agregat;
    }

    public String getNum_agreg() {
        return num_agreg;
    }

    public void setNum_agreg(String num_agreg) {
        this.num_agreg = num_agreg;
    }

    @Override
    public String toString() {
        return "Config{" +
                "tracker=" + tracker +
                ", mark='" + mark + '\'' +
                ", gos='" + gos + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", type_work='" + type_work + '\'' +
                ", agregat='" + agregat + '\'' +
                ", num_agreg='" + num_agreg + '\'' +
                '}';
    }
}
