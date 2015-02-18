package com.disp.disp.control.loadExcell;

/**
 * Created by disp.chimc on 21.10.14.
 */
public class Info {

    private double length_harvesters;
    private String driver;
    private String phone;
    private String gos;

    public Info() {
        this.length_harvesters = 9;
        this.driver = "-";
        this.phone = "-";
        this.gos = "-";
    }

    public Info(double length_harvesters, String driver, String phone, String gos) {
        this.length_harvesters = length_harvesters;
        this.driver = driver;
        this.phone = phone;
        this.gos = gos;
    }

    public double getLength_harvesters() {
        return length_harvesters;
    }

    public void setLength_harvesters(double length_harvesters) {
        this.length_harvesters = length_harvesters;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGos() {
        return this.gos;
    }

    public void setGos(String gos) {
        this.gos = gos;
    }

    @Override
    public String toString() {
        return "Info{" +
                "length_harvesters=" + length_harvesters +
                ", driver='" + driver + '\'' +
                ", phone='" + phone + '\'' +
                ", gos='" + gos + '\'' +
                '}';
    }
}
