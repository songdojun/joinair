package com.example.joinair.dto.api;

public class ReqDronLocation {

    double dronLat;
    double dronLon;
    double endLat;
    double endLon;

    public ReqDronLocation() {
        super();
    }


    public ReqDronLocation(double dronLat, double dronLon, double endLat, double endLon) {
        this.dronLat = dronLat;
        this.dronLon = dronLon;
        this.endLat = endLat;
        this.endLon = endLon;
    }

    public double getDronLat() {
        return dronLat;
    }

    public void setDronLat(double dronLat) {
        this.dronLat = dronLat;
    }

    public double getDronLon() {
        return dronLon;
    }

    public void setDronLon(double dronLon) {
        this.dronLon = dronLon;
    }

    public double getEndLat() {
        return endLat;
    }

    public void setEndLat(double endLat) {
        this.endLat = endLat;
    }

    public double getEndLon() {
        return endLon;
    }

    public void setEndLon(double endLon) {
        this.endLon = endLon;
    }
}
