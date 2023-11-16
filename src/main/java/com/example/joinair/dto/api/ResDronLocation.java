package com.example.joinair.dto.api;

public class ResDronLocation {

    private double dronLat;
    private double dronLon;

    private boolean isDest; // flase : 아직 도착 전.  true : 도착 함.

    private String eta; // 도착 예정시간.

    public ResDronLocation() {
        super();
    }


    public ResDronLocation(double dronLat, double dronLon, boolean isDest, String eta) {
        this.dronLat = dronLat;
        this.dronLon = dronLon;
        this.isDest = isDest;
        this.eta = eta;
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

    public boolean isDest() {
        return isDest;
    }

    public void setDest(boolean dest) {
        isDest = dest;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }
}
