package com.example.joinair.dto.api;

public class ResShippingDoc {

    double startLat;
    double startLon;
    double endLat;
    double endLon;

    public ResShippingDoc() {
        super();
    }

    public ResShippingDoc(double startLat, double startLon, double endLat, double endLon) {
        this.startLat = startLat;
        this.startLon = startLon;
        this.endLat = endLat;
        this.endLon = endLon;
    }

    public double getStartLat() {
        return startLat;
    }

    public void setStartLat(double startLat) {
        this.startLat = startLat;
    }

    public double getStartLon() {
        return startLon;
    }

    public void setStartLon(double startLon) {
        this.startLon = startLon;
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
